package com.mgw.one.chapter5;

import java.util.Optional;
import java.util.stream.IntStream;

public class ThreadJoin2 {

    public static void main(String[] args) throws  Exception {

        Thread t1 = new Thread(() -> {

            try {
                System.out.println(Thread.currentThread().getName() + " is running");
                Thread.sleep(10_000);
                System.out.println(Thread.currentThread().getName() + " is done");
            }catch (Exception e) {

            }
        });

        t1.start();
        t1.join(100);

        Optional.of("All of task finish done.").ifPresent(System.out :: println);

        IntStream.range(1,1000).forEach(i -> {

            System.out.println(Thread.currentThread().getName() + "->" + i);
        });
    }
}
