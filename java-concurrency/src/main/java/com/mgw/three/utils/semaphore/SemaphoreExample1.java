package com.mgw.three.utils.semaphore;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class SemaphoreExample1 {

    public static void main(String[] args) {

        final SemaphoreLock lock = new SemaphoreLock();

        new Thread() {
            @Override
            public void run() {
                try {
                    System.out.println(Thread.currentThread().getName() + " is running");
                    lock.lock();
                    System.out.println(Thread.currentThread().getName() + " get the #SemaphoreLock");
                    TimeUnit.SECONDS.sleep(5);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    lock.unlock();
                }
                System.out.println(Thread.currentThread().getName() + " release the #SemaphoreLock");
            }
        }.start();

        new Thread() {
            @Override
            public void run() {
                try {
                    System.out.println(Thread.currentThread().getName() + " is running");
                    lock.lock();
                    System.out.println(Thread.currentThread().getName() + " get the #SemaphoreLock");
                    TimeUnit.SECONDS.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    lock.unlock();
                }
                System.out.println(Thread.currentThread().getName() + " release the #SemaphoreLock");
            }
        }.start();

    }

    /**
     * 创建自定义锁
     * */
    static class SemaphoreLock {

        private final Semaphore semaphore = new Semaphore(1);

        public void lock() throws InterruptedException {
            semaphore.acquire();
        }

        public void unlock() {
            semaphore.release();
        }
    }
}
