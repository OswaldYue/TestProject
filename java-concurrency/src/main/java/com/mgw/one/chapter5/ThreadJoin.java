package com.mgw.one.chapter5;

import java.util.Optional;
import java.util.stream.IntStream;

public class ThreadJoin {

    public static void main(String[] args) throws Exception{


        Thread t1 = new Thread(() -> {

            IntStream.range(1,1000).forEach(i -> {

                System.out.println(Thread.currentThread().getName() + "->" + i);
            });

        });

        Thread t2 = new Thread(() -> {

            IntStream.range(1,1000).forEach(i -> {

                System.out.println(Thread.currentThread().getName() + "->" + i);
            });

        });


        t1.start();
        t1.join();

        t2.start();
        t2.join();

        Optional.of("All of task finish done.").ifPresent(System.out :: println);

        IntStream.range(1,1000).forEach(i -> {

            System.out.println(Thread.currentThread().getName() + "->" + i);
        });

    }

}
