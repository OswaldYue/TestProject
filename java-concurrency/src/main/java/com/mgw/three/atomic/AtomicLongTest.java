package com.mgw.three.atomic;

import java.util.concurrent.atomic.AtomicLong;

public class AtomicLongTest {

    public static void main(String[] args) {
        test1();
    }

    public static void test1() {

        AtomicLong aLong = new AtomicLong();
        /*
        * AtomicLong源码中的VM_SUPPORTS_LONG_CAS的作用:
        * Long类型是64位的  但是如果在32位机上 会分为高32位 低32位
        * VM_SUPPORTS_LONG_CAS保证如果机器不支持64位Long类型一次取到，则会在1总线上加锁保证
        * */
        System.out.println(aLong.get());

    }

}
