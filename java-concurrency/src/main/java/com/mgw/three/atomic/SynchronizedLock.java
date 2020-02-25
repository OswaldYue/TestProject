package com.mgw.three.atomic;

/**
 * 使用无锁自定义锁
 * 这是个普通的synchronized锁
 * */
public class SynchronizedLock {

    public static void main(String[] args) {

        for (int i = 0 ;i < 2 ;i++) {

            new Thread(()->{
                try {
                    doSomething();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
        }

    }

    private static void doSomething() throws InterruptedException {

        synchronized (SynchronizedLock.class) {
            System.out.println(Thread.currentThread().getName() + " get the lock.");
            Thread.sleep(100_000);
        }
    }

}
