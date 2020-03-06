package com.mgw.part1.chapter3;

import org.openjdk.jol.info.ClassLayout;

class L {
//    byte a=0;
    int b = 100;
}
/**
 * 查看类的布局
 * */
public class ClassLayoutTest {

    public static void main(String[] args) {
        System.out.println(ClassLayout.parseInstance(new L()).toPrintable());
    }
}
