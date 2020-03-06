package com.mgw.part1.chapter3;

/**
 * javap -v DynamicLinkTest.class
 * */
public class DynamicLinkTest {

    private int num = 10;

    public void methodA() {
        System.out.println("==========methodA()==========");
    }

    public void methodB() {
        System.out.println("==========methodB()==========");
        methodA();
        this.num++;
    }
}
