package com.mgw.three.utils.locks;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockExample2 {

    private static final ReentrantLock lock = new ReentrantLock();


    public static void main(String[] args) {

        Runnable runnable = () -> {testUnInterruptibly();};

        new Thread(runnable,"t1").start();

        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        final Thread t2 = new Thread(runnable, "t2");
        t2.start();
        // 这里休眠2秒，让t2线程进入到队列中park,那为啥还是会被打断呢？
        // 原因:Some other thread {@linkplain Thread#interrupt interrupts} the current thread park {@linkplain java.util.concurrent.locks.LockSupport.park(java.lang.Object)}可以被中断
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        t2.interrupt();


    }

    public static void testUnInterruptibly() {

        try {
            lock.lockInterruptibly();
            System.out.println(Thread.currentThread().getName());
            while (true) {

            }

        } catch (InterruptedException e) {
            System.out.println("XXXXXXXXXXXXXXXXXX");
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}
