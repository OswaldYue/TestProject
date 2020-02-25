package com.mgw.three.utils.locks;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.StampedLock;
import java.util.stream.Collectors;

/**
 * StampedLock锁出现解决了什么问题?
 * 当有100个读写线程时，99个读操作，1个写操作
 * 此时那1个写操作可能一直抢不到锁 此时就会出现饥饿现象
 *
 *
 *
 * */
public class StampedLockExample1 {

    private static final StampedLock lock = new StampedLock();

    private static final List<Long> DATA = new ArrayList<>();

    // 悲观式的读
    private static void read() {

        long stamp = -1;

        try {
            stamp = lock.readLock();
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
                read();
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
