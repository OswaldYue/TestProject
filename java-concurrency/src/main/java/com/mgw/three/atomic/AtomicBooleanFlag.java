package com.mgw.three.atomic;

import java.util.concurrent.atomic.AtomicBoolean;

public class AtomicBooleanFlag {

    private final static AtomicBoolean flag = new AtomicBoolean(true);

    public static void main(String[] args) throws InterruptedException {

        new Thread("Thread-0") {
            @Override
            public void run() {
                while (flag.get()) {
                    try {
                        Thread.sleep(1_000);
                        System.out.println("I am working..");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("I am finished..");
            }
        }.start();

        Thread.sleep(5_000);

        // 结束掉Thread-0线程
        flag.set(false);
    }

}
