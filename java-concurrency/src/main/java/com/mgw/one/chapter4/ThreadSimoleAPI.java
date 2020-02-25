package com.mgw.one.chapter4;

import java.util.Optional;

public class ThreadSimoleAPI {

    public static void main(String[] args) {

        Thread t = new Thread(() -> {

            Optional.of("Hello").ifPresent(System.out :: println);
            try{
                Thread.sleep(100_000);
            }catch (Exception e) {

            }

        }, "t1");

        t.start();

        Optional.of(t.getName()).ifPresent(System.out :: println);
        Optional.of(t.getId()).ifPresent(System.out :: println);
        Optional.of(t.getPriority()).ifPresent(System.out :: println);
    }
}
