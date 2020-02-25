package com.mgw.one.chapter7;

public class SynchronizedTest {

    private final static Object LOCK = new Object();

    public static void main(String[] args) {

        Runnable runnable = () -> {

            // 1
            synchronized (LOCK) {

                try {
                    Thread.sleep(200_000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            // 2
            // 1 - 2处的过程，其实是串行化处理
        };

        Thread t1 = new Thread(runnable);
        Thread t2 = new Thread(runnable);
        Thread t3 = new Thread(runnable);

        t1.start();
        t2.start();
        t3.start();

    }
}
