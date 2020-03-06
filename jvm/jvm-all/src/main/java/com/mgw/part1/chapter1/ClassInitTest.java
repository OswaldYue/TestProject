package com.mgw.part1.chapter1;

/**
 * 使用jclasslib查看类的clinit方法
 * */
public class ClassInitTest {

    private static int i = 10;

    static {
        i = 100;
        j = 200;
    }

    private static int j = 20;// link之prepare j=> 0 --> clinit 200 ---> 20

    public static void main(String[] args) {
        System.out.println(i);
    }

}
