package com.mgw.three.utils.semaphore;

import java.sql.Time;
import java.util.Collection;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class SemaphoreExample4 {

    public static void main(String[] args) throws InterruptedException {

        final MySemaphore semaphore = new MySemaphore(5);
        Thread t1 = new Thread(() -> {
            try {
                semaphore.drainPermits();
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
                final boolean tryAcquire = semaphore.tryAcquire();
                System.out.println(tryAcquire ? "Successful":"Failure");
//                TimeUnit.SECONDS.sleep(5);
            } finally {
                semaphore.release();
            }
            System.out.println("T2 finish");
        });
        t2.start();

        TimeUnit.SECONDS.sleep(1);
        System.out.println("===>" + semaphore.hasQueuedThreads());
        final Collection<Thread> waitingThreads = semaphore.getWaitingThreads();
        for (Thread waitingThread : waitingThreads) {
            System.out.println("======>" + waitingThread.getName());
        }


    }

    static class MySemaphore extends Semaphore {

        public MySemaphore(int permits) {
            super(permits);
        }

        public MySemaphore(int permits,boolean fair) {
            super(permits,fair);
        }

        public Collection<Thread> getWaitingThreads() {
            return super.getQueuedThreads();
        }
    }
}
