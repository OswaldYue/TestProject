package com.mgw.one.chapter9;

import java.util.stream.Stream;

public class DifferenceOfWaitAndSleep {

    private final static Object LOCK = new Object();

    public static void main(String[] args) {

//        m1();
//        m2();
        Stream.of("T1","T2").forEach((name) -> {
            new Thread(name) {

                @Override
                public void run() {
//                    m1();
                    m3();
                }

            }.start();
        });

    }


    public static void m1() {

        synchronized (LOCK) {
            try {
                System.out.println("The thread " + Thread.currentThread().getName() + " enter.");
                Thread.sleep(2_000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void m2() {

        synchronized (LOCK) {
            try {
                LOCK.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void m3() {

        synchronized (LOCK) {
            try {
                System.out.println("The thread " + Thread.currentThread().getName() + " enter.");
                LOCK.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
