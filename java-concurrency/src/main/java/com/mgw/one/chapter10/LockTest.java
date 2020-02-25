package com.mgw.one.chapter10;

import java.util.Optional;
import java.util.stream.Stream;

public class LockTest {

    public static void main(String[] args) throws InterruptedException {

        BooleanLock booleanLock = new BooleanLock();

        Stream.of("T1","T2","T3","T4").forEach(name -> new Thread(() -> {

            try {
                // 想要实现锁，则必须将其他线程阻拦在booleanLock.lock()代码中
//                booleanLock.lock();
                //测试超时锁
                booleanLock.lock(11_000);
                Optional.of(Thread.currentThread().getName() + " have the lock Monitor")
                        .ifPresent(System.out :: println);
                // 开始工作
                work();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (Lock.TimeOutException e) {
                Optional.of(Thread.currentThread().getName() + " time out")
                        .ifPresent(System.out :: println);
            } finally {
                booleanLock.unlock();
            }


        },name).start());

        // 非法操作 未满足谁锁谁释放
//        Thread.sleep(100);
//        booleanLock.unlock();

    }

    private static void work() throws  InterruptedException {

        Optional.of(Thread.currentThread().getName() + " is working....")
                .ifPresent(System.out :: println);

        Thread.sleep(10_000);
    }
}
