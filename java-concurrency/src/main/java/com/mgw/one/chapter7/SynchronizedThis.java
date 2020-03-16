package com.mgw.one.chapter7;

public class SynchronizedThis {


    public static void main(String[] args) {

        ThisLock thisLock = new ThisLock();

        Thread t1 = new Thread("T1") {
            @Override
            public void run() {
                thisLock.m1();
            }
        };

        Thread t2 = new Thread("T2") {
            @Override
            public void run() {
                thisLock.m2();
            }
        };

        Thread t3 = new Thread("T3") {
            @Override
            public void run() {
                thisLock.m3();
            }
        };

        Thread t4 = new Thread("T4") {
            @Override
            public void run() {
                thisLock.m4();
            }
        };

//        t1.start();
//        t2.start();
        t3.start();
        t4.start();

    }



}

class ThisLock {

    private final Object LOCK = new Object();

    public synchronized void m1() {

        try {
            System.out.println(Thread.currentThread().getName());
            Thread.sleep(10_000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public synchronized void m2() {

        try {
            System.out.println(Thread.currentThread().getName());
            Thread.sleep(10_000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public  void m3() {

        synchronized (LOCK) {
            try {
                System.out.println(Thread.currentThread().getName());
                Thread.sleep(10_000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public  void m4() {

        synchronized (LOCK) {
            try {
                System.out.println(Thread.currentThread().getName());
                Thread.sleep(10_000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


}