package com.mgw.part1.chapter3;

public class StackTest {

    public void methodA() {
        int i = 10;
        int j = 20;

        int k = i+j;

        String s = "BBB";
        System.out.println(k);
        System.out.println(s);

        methodB();
    }


    public void methodB() {
        int i = 100;
        int j = 200;
    }

    public static void main(String[] args) {
        final StackTest stackTest = new StackTest();
        stackTest.methodA();
    }
}
