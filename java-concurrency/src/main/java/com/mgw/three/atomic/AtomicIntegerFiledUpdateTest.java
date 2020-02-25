package com.mgw.three.atomic;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

public class AtomicIntegerFiledUpdateTest {

    public static void main(String[] args) {
        test1();
    }


    public static void test1() {

        AtomicIntegerFieldUpdater<TestMe> updater = AtomicIntegerFieldUpdater.newUpdater(TestMe.class, "ii");
        TestMe testMe = new TestMe();

        for (int i = 0; i<2; i++) {
            new Thread() {

                @Override
                public void run() {
                    final int MAX = 20;
                    for(int j = 0 ; j < MAX ;j++) {
                        int v = updater.getAndIncrement(testMe);
                        System.out.println(Thread.currentThread().getName() + "=> " + v);
                    }
                }
            }.start();
        }


    }

    static class TestMe {

        volatile int ii;
    }
}
