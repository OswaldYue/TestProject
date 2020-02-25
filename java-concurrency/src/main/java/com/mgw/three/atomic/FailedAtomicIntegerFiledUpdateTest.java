package com.mgw.three.atomic;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;

public class FailedAtomicIntegerFiledUpdateTest {

    public static void main(String[] args) {
//        test1();
//        test2();
//        test3();
//        test4();
        test5();
    }


    public static void test1() {
        // 字段不能为private私有
        // Exception in thread "main" java.lang.RuntimeException: java.lang.IllegalAccessException: Class com.mgw.three.atomic.FailedAtomicIntegerFiledUpdateTest
        // can not access a member of class com.mgw.three.atomic.TestMe with modifiers "private volatile"
        AtomicIntegerFieldUpdater<TestMe> updater = AtomicIntegerFieldUpdater.newUpdater(TestMe.class, "i");
        TestMe testMe = new TestMe();
        updater.compareAndSet(testMe,0,1);


    }

    public static void test2() {

        // 不能为null 转化不了
        // Exception in thread "main" java.lang.ClassCastException
        AtomicIntegerFieldUpdater<TestMe> updater = AtomicIntegerFieldUpdater.newUpdater(TestMe.class, "i");
//        TestMe testMe = new TestMe();
        updater.compareAndSet(null,0,1);
    }

    public static void test3() {

        // 字段必须要有
        // Exception in thread "main" java.lang.RuntimeException: java.lang.NoSuchFieldException: iiException in thread "main" java.lang.RuntimeException: java.lang.NoSuchFieldException: ii
        AtomicIntegerFieldUpdater<TestMe> updater = AtomicIntegerFieldUpdater.newUpdater(TestMe.class, "ii");
        TestMe testMe = new TestMe();
        updater.compareAndSet(testMe,0,1);
    }

    public static void test4() {

        // TestMe2类中的i为Integer  但是你指定的却是Long  类型不匹配
        // Exception in thread "main" java.lang.ClassCastException
        AtomicReferenceFieldUpdater<TestMe2,Long> updater = AtomicReferenceFieldUpdater.newUpdater(TestMe2.class,Long.class,"i");
        TestMe2 testMe = new TestMe2();
        updater.compareAndSet(testMe,null,1L);
    }

    public static void test5() {

        // 字段必须为volatile
        // Exception in thread "main" java.lang.IllegalArgumentException: Must be volatile type
        AtomicReferenceFieldUpdater<TestMe2,Integer> updater = AtomicReferenceFieldUpdater.newUpdater(TestMe2.class,Integer.class,"i");
        TestMe2 testMe = new TestMe2();
        updater.compareAndSet(testMe,null,1);
    }


    static  class TestMe2 {

        Integer i;
    }

}
