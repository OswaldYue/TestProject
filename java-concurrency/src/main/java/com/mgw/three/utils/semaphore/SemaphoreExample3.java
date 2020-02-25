package com.mgw.three.utils.semaphore;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class SemaphoreExample3 {

    public static void main(String[] args) throws InterruptedException {

        final Semaphore semaphore = new Semaphore(1);

        Thread t1 = new Thread(() -> {
            try {
                semaphore.acquire();
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
                semaphore.release();
            }
            System.out.println("T1 finish");
        });

        t1.start();

        TimeUnit.SECONDS.sleep(1);

        Thread t2 = new Thread(() -> {
            try {
                semaphore.acquireUninterruptibly();
//                TimeUnit.SECONDS.sleep(5);
            } finally {
                semaphore.release();
            }
            System.out.println("T2 finish");
        });

        t2.start();

        TimeUnit.SECONDS.sleep(1);

        t2.interrupt();


    }
}
