package com.mgw.part1.chapter3;

public class StackFrameTest {

    public static void main(String[] args) {

    }

    private static void method1() {
        System.out.println("method1 开始执行");
        method2();
        System.out.println("method1 执行结束");
    }

    private static int method2() {

        System.out.println("method2 开始执行");
        double i = method3();
        System.out.println("method2 即将执行结束");

        return 100;
    }

    private static double method3() {
        System.out.println("method3 开始执行");
        System.out.println("method3 即将执行结束");

        return 10.0;
    }
}
