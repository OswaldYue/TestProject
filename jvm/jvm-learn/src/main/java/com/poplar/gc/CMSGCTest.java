package com.poplar.gc;

/**
 *-verbose:gc
 * -Xms20M
 * -Xmx20M
 * -Xmn10M
 * -XX:+PrintGCDetails
 * -XX:SurvivorRatio=8
 * -XX:+UseConcMarkSweepGC
 *
 */
public class CMSGCTest {
    public static void main(String[] args) {
        int size = 1024 * 1024;
        byte[] bytes1 = new byte[4 * size];
        System.out.println("111111");

        byte[] bytes2 = new byte[4 * size];
        System.out.println("2222222");

        byte[] bytes3 = new byte[4 * size];
        System.out.println("33333333");

        byte[] bytes4 = new byte[2 * size];
        System.out.println("4444444");
    }
}
