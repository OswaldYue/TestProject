package com.mgw.three.utils.locks;

import java.util.Optional;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.IntStream;

/**
 * ReentrantLock可重入锁 属于悲观锁
 * */
public class ReentrantLockExample {

    private static final ReentrantLock  lock = new ReentrantLock();

    public static void main(String[] args) throws InterruptedException {

        /*IntStream.range(0,2).forEach(i -> new Thread(String.valueOf(i)){
            @Override
            public void run() {
//                needLock();
                needLockBySync();
            }
        }.start());*/

        /*IntStream.range(0,2).forEach(i -> new Thread(String.valueOf(i)){
            @Override
            public void run() {
                testTryLock();
            }
        }.start());*/

        /*Thread t1 = new Thread(()-> testUnInterruptibly());
        t1.start();
        TimeUnit.SECONDS.sleep(1);
        Thread t2 = new Thread(()-> testUnInterruptibly());
        t2.start();
        TimeUnit.SECONDS.sleep(1);
        t2.interrupt();
        System.out.println("=========");*/

        Thread t1 = new Thread(()-> testUnInterruptibly());
        t1.start();
        TimeUnit.SECONDS.sleep(1);
        Thread t2 = new Thread(()-> testUnInterruptibly());
        t2.start();
        TimeUnit.SECONDS.sleep(1);
        // 在waiting queue中的线程
        Optional.of("1=>" + lock.getQueueLength()).ifPresent(System.out::println);
        // waiting queue中是否有线程
        Optional.of("2==>" + lock.hasQueuedThreads()).ifPresent(System.out::println);
        // 判断某个线程是否在waiting queue中
        Optional.of("3===>" + lock.hasQueuedThread(t1)).ifPresent(System.out::println);
        Optional.of("3===>" + lock.hasQueuedThread(t2)).ifPresent(System.out::println);

        // Queries if this lock is held by any thread 判断锁是否被其他线程拿到了
        Optional.of("4====>" + lock.isLocked()).ifPresent(System.out::println);

    }

    // 测试锁的过程中可以被打断
    public static void testUnInterruptibly() {
        try {
            try {

                // 锁的过程中可以被打断
                lock.lockInterruptibly();
                // 当前线程是否拿到了锁 如果返回值时0表示没有拿到
                Optional.of(Thread.currentThread().getName() + " hold count " +lock.getHoldCount()).ifPresent(System.out::println);
                Optional.of("The thread-"+Thread.currentThread().getName() + " get lock and will do working.").ifPresent(System.out :: println);
                while (true) {

                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        } finally {
            lock.unlock();
        }
    }

    public static void testTryLock() {

        // 尝试拿锁
        if (lock.tryLock()) {
            try {
                Optional.of("The thread-"+Thread.currentThread().getName() + " get lock and will do working.").ifPresent(System.out :: println);
                while (true) {

                }
            }finally {
                lock.unlock();
            }
        }else {
            Optional.of("The thread-"+Thread.currentThread().getName() + " not get lock.").ifPresent(System.out :: println);
        }
    }

    public static void needLock() {

        try {
            lock.lock();
            Optional.of("The thread-"+Thread.currentThread().getName() + " get lock and will do working.").ifPresent(System.out :: println);
            // do some thing
            TimeUnit.SECONDS.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public static void needLockBySync() {
        synchronized (ReentrantLockExample.class) {
            try {
                // do some thing
                Optional.of("The thread-"+Thread.currentThread().getName() + " get lock and will do working.").ifPresent(System.out :: println);
                TimeUnit.SECONDS.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
