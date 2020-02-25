package com.mgw.two.chapter2;

import java.util.Optional;
import java.util.stream.IntStream;

public class WaitSet2 {

    private static final Object LOCK = new Object();



    private static void work() {

        synchronized (LOCK) {
            System.out.println("Begin...");
            try {
                System.out.println("Thread will coming.");
                LOCK.wait();
                System.out.println("Thread will out.");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }

    public static void main(String[] args) {

        new Thread() {
            @Override
            public void run() {
                work();
            }
        }.start();

        try {
            Thread.sleep(1_000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // 唤醒
        synchronized (LOCK) {
            LOCK.notify();
        }

    }

}
