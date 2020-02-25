package com.mgw.one.chapter11;

import java.util.Optional;

public class ThreadException {


    public static void main(String[] args) {


        Thread t1 = new Thread(() -> {

            try {
                Thread.sleep(1_000);

                int i = 10 /0 ;

            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        },"T1");

        t1.setUncaughtExceptionHandler((thread,e) -> {

            Optional.of("" + e).ifPresent(System.out :: println);
            Optional.of("" + thread).ifPresent(System.out :: println);
        });
        t1.start();
    }


}
