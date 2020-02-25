package com.mgw.three.utils.locks;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.StampedLock;
import java.util.stream.Collectors;

public class StampedLockExample2 {

    private static final StampedLock lock = new StampedLock();

    private static final List<Long> DATA = new ArrayList<>();

    // 乐观式的读
    private static void readOptimistic() {

        long stamp = lock.tryOptimisticRead();
        // 检查是否对乐观锁进行了修改
        if (lock.validate(stamp)) {

            try {
                stamp = lock.readLock();
                System.err.println("stamp : " + stamp);
                Optional.of(
                        DATA.stream().map(String :: valueOf).collect(Collectors.joining("#","R-",""))
                ).ifPresent(System.out::println);
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlockRead(stamp);
            }
        }
    }



    // 写锁只有悲观的 没有乐观的
    private static void write() {

        long stamp = -1;
        try {
            stamp = lock.writeLock();
            DATA.add(System.currentTimeMillis());
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlockWrite(stamp);
        }

    }

    public static void main(String[] args) {

        final ExecutorService threadPool = Executors.newFixedThreadPool(10);

        Runnable readTask = () -> {
            for (;;) {
                readOptimistic();
            }
        };
        Runnable writeTask = () -> {
            for (;;) {
                write();
            }
        };

        threadPool.submit(readTask);
        threadPool.submit(readTask);
        threadPool.submit(readTask);
        threadPool.submit(readTask);
        threadPool.submit(readTask);
        threadPool.submit(readTask);
        threadPool.submit(readTask);
        threadPool.submit(readTask);
        threadPool.submit(readTask);
        threadPool.submit(writeTask);
    }



}
