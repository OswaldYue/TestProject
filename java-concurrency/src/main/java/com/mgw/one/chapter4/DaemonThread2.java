package com.mgw.one.chapter4;

public class DaemonThread2 {

    public static void main(String[] args) {

        Thread t = new Thread(() -> {

            Thread innerThread = new Thread(() -> {

                try {
                    while(true) {
                        System.out.println("Do some thing for health check.");
                        Thread.sleep(1_000);
                    }

                }catch (Exception e) {

                }

            });

            innerThread.setDaemon(true);
            innerThread.start();

            try {
                Thread.sleep(10_000);
                System.out.println(Thread.currentThread().getName() + " thread finish done.");
            }catch (Exception e) {

            }

        });

        // 父线程结束，且main线程也结束，那么守护线程也随之结束，若main线程没有结束，那么守护线程则不会结束
        t.start();

        try {
            Thread.sleep(15_000);
            System.out.println(Thread.currentThread().getName() + " thread finish done.");
        }catch (Exception e) {

        }
    }


}
