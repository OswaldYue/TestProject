package com.mgw.three.utils.semaphore;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class SemaphoreExample2 {

    public static void main(String[] args) throws InterruptedException {

        Semaphore semaphore = new Semaphore(2);

        for (int i = 0 ; i < 3; i++) {
            new Thread() {
                @Override
                public void run() {
                    System.out.println(Thread.currentThread().getName() + " in");
                    try {
                        semaphore.acquire(1);
                        System.out.println(Thread.currentThread().getName() + " get the semaphore");
                        TimeUnit.SECONDS.sleep(5);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }finally {
                        semaphore.release(1);
                    }
                    System.out.println(Thread.currentThread().getName() + " out");
                }
            }.start();
        }

        while (true) {
            System.out.println("AP -> "+ semaphore.availablePermits());
            System.out.println("QL -> "+ semaphore.getQueueLength());
            System.out.println("===========================");
            TimeUnit.SECONDS.sleep(1);
        }
    }
}
