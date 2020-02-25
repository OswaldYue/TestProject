package com.mgw.three.atomic;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

/**
 * AtomicXXXFieldUpdater 如果是当前类也可以是private，protected
 *
 * */
public class AIFUTest {

    private volatile int i;

    private AtomicIntegerFieldUpdater<AIFUTest> updater = AtomicIntegerFieldUpdater.newUpdater(AIFUTest.class,"i");

    public void update(int newValue) {
        updater.compareAndSet(this,i,newValue);
    }

    public int get() {
        return i;
    }

    public static void main(String[] args) {
        AIFUTest aifuTest = new AIFUTest();
        aifuTest.update(10);
        System.out.println(aifuTest.get());
    }

}
