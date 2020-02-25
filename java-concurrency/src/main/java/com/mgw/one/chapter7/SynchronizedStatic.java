package com.mgw.one.chapter7;

public class SynchronizedStatic {

    static {
        synchronized (SynchronizedStatic.class) {
            System.out.println("static " + Thread.currentThread().getName());
            try {
                Thread.sleep(10_000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {

        new Thread("T1") {

            @Override
            public void run() {
                SynchronizedStatic.m1();
            }
        }.start();

        new Thread("T2") {
            @Override
            public void run() {
                SynchronizedStatic.m2();
            }
        }.start();

        new Thread("T3") {
            @Override
            public void run() {
                SynchronizedStatic.m3();
            }
        }.start();
    }

    public synchronized static void m1() {

        System.out.println("m1 " + Thread.currentThread().getName());
        try {
            Thread.sleep(10_000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public synchronized static void m2() {

        System.out.println("m2 " + Thread.currentThread().getName());
        try {
            Thread.sleep(10_000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public  static void m3() {

        System.out.println("m3 " + Thread.currentThread().getName());
        try {
            Thread.sleep(10_000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
