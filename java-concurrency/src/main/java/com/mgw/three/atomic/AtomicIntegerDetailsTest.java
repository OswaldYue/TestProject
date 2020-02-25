package com.mgw.three.atomic;

import java.util.concurrent.atomic.AtomicInteger;

public class AtomicIntegerDetailsTest {



    public static void main(String[] args) throws InterruptedException {

//        test1();
        test2();
    }

    public static void test2() {
        final AtomicInteger i = new AtomicInteger(10);
        final boolean flag = i.compareAndSet(12, 20);

        System.out.println(flag);
        System.out.println(i.get());

    }

    public static void test1() {

        // create
        AtomicInteger i = new AtomicInteger();
        System.out.println(i.get());
        i = new AtomicInteger(10);
        System.out.println(i.get());

        // set
        i.set(12);
        System.out.println(i.get());
        i.lazySet(15);
        System.out.println(i.get());

        // getAndSet
        AtomicInteger integer = new AtomicInteger(10);
        int result = integer.getAndAdd(10);
        System.out.println(result);
        System.out.println(integer.get());

        System.out.println("---------------------------------------");

        final AtomicInteger atomicInteger = new AtomicInteger();

        new Thread(() -> {
            for (int j = 0 ;j < 10 ;j++) {
                final int v = atomicInteger.addAndGet(1);
                System.out.println(Thread.currentThread().getName() + " value is " + v);
            }
        }).start();

        new Thread(() -> {
            for (int j = 0 ;j < 10 ;j++) {
                final int v = atomicInteger.addAndGet(1);
                System.out.println(Thread.currentThread().getName() + " value is " + v);
            }
        }).start();



    }
}
