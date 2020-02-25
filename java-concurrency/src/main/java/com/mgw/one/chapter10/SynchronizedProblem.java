package com.mgw.one.chapter10;

public class SynchronizedProblem {


    public static void main(String[] args) throws InterruptedException {


        new Thread("T1") {
            @Override
            public void run() {

                SynchronizedProblem.run();
            }
        }.start();


        Thread t2 = new Thread("T2") {

            @Override
            public void run() {
                SynchronizedProblem.run();
            }
        };

        t2.start();
        Thread.sleep(2_000);

        t2.interrupt();
        System.out.println(t2.isInterrupted());

    }


    private synchronized static void run() {

        System.out.println(Thread.currentThread().getName());

        while (true) {

        }

    }
}
