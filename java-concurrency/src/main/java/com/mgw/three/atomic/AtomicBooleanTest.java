package com.mgw.three.atomic;

import java.util.concurrent.atomic.AtomicBoolean;

public class AtomicBooleanTest {

    public static void main(String[] args) {
//        test1();
//        test2();
        test3();
    }


    public static void test3() {

        AtomicBoolean bool = new AtomicBoolean(true);
        boolean result = bool.compareAndSet(false, true);
        System.out.println(result); // false
        System.out.println(bool.get()); // true
    }


    public static void test2() {

        AtomicBoolean bool = new AtomicBoolean(true);
        boolean result = bool.compareAndSet(true, false);
        System.out.println(result); // true
        System.out.println(bool.get()); // false
    }

    public static void test1() {

        AtomicBoolean bool = new AtomicBoolean();
        System.out.println(bool.get());

        boolean result = bool.getAndSet(true);
        System.out.println(result);
        System.out.println(bool.get());
    }

}
