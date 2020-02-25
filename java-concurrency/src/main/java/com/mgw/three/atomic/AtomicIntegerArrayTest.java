package com.mgw.three.atomic;

import java.util.concurrent.atomic.AtomicIntegerArray;
import java.util.concurrent.atomic.AtomicReferenceArray;

public class AtomicIntegerArrayTest {

    public static void main(String[] args) {

        AtomicIntegerArray atomicIntegerArray = new AtomicIntegerArray(10);
//        System.out.println(atomicIntegerArray.length()); // 10
        System.out.println(atomicIntegerArray.get(5)); // 0
        atomicIntegerArray.set(5,100);
        System.out.println(atomicIntegerArray.get(5)); // 100


        new AtomicReferenceArray<String>(6);


    }
}
