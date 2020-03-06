package com.mgw.part1.chapter1;

/**
 * 监测JVM是基于栈设计的
 *
 * 使用javap -v StackStruTest.class 反编译后查看main方法
 * */
public class StackStruTest {

    public static void main(String[] args) {

//        int i = 2 + 3;
        int i= 2;
        int j = 3;
        int k = i+j;
    }
}
