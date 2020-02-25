package com.mgw.three.utils.condition;

import java.util.Optional;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;
/**
 * 问题1:不使用Condition而只使用公平锁？不行，当去掉ReentrantLock(true)时就不能达到生成者消费者且公平锁也只能尽量保证公平
 * 问题2:只使用锁只使用Condition? 不行，抛IllegalMonitorStateException异常，与wait()和notify()使用时异曲同工
 * 问题3:生产者拿到锁并且执行了await方法且没有跳出代码块去释放锁，为什么消费者可以拿到锁？ 因为生产者放弃了cpu执行权和wait()和notify()差不多
 *
 * */
public class ConditionExample2 {

    private final static ReentrantLock lock = new ReentrantLock();

    private final static Condition condition = lock.newCondition();

    private static int data = 0;

    private static volatile boolean noUse = true;

    private static void buildData() {

        try {
            lock.lock();
            while (noUse) {
                condition.await();
            }
            data++;
            Optional.of("P-" +Thread.currentThread().getName() +":"+ data).ifPresent(System.out::println);
            TimeUnit.SECONDS.sleep(1);
            noUse = true;
            condition.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    private static void useData() {

        try {
            lock.lock();
            while (!noUse) {
                condition.await();
            }
            TimeUnit.SECONDS.sleep(1);
            Optional.of("C-" +Thread.currentThread().getName() +":"+ data).ifPresent(System.out::println);
            noUse = false;
            condition.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {

        new Thread(() -> {
            for (;;) {
                buildData();
            }
        }).start();


        new Thread(() -> {
            for (; ; ) {
                useData();
            }
        }).start();

    }


}
