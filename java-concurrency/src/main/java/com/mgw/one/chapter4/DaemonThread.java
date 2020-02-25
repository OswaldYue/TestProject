package com.mgw.one.chapter4;

public class DaemonThread {

    public static void main(String[] args) {

        Thread thread = new Thread() {

            @Override
            public void run() {

                try {

                    System.out.println(Thread.currentThread().getName() + " running");
                    Thread.sleep(100_000);
                    System.out.println(Thread.currentThread().getName() + " done");
                }catch (Exception e) {

                }
            }
        };  // new 状态

        // runable状态 -> running -> dead -> block
        thread.setDaemon(true);
        thread.start();

        try {

            Thread.sleep(50_000);
        }catch (Exception e) {

        }
        System.out.println(Thread.currentThread().getName());

    }

}
