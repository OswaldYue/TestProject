package com.mgw.three.atomic;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

public class AtomicIntegerTest2 {

    private static volatile int value = 0;

    private static Set<Integer> set = Collections.synchronizedSet(new HashSet<Integer>());

    public static void main(String[] args) throws InterruptedException {

        final AtomicInteger value = new AtomicInteger();

        final Thread t1 = new Thread() {
            @Override
            public void run() {
                int x = 0;
                while (x < 500) {
                    int tem = value.get();
                    set.add(tem);
                    System.out.println(Thread.currentThread().getName() + " :" + tem);
                    value.getAndIncrement();
                    x++;
                }

            }
        };

        final Thread t2 = new Thread() {
            @Override
            public void run() {
                int x = 0;
                while (x < 500) {
                    int tem = value.get();
                    set.add(tem);
                    System.out.println(Thread.currentThread().getName() + " :" + tem);
                    value.getAndIncrement();
                    x++;
                }

            }
        };

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        System.out.println("Finish...size = " + set.size());;
    }

}
