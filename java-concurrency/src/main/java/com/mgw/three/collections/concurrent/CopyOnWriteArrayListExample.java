package com.mgw.three.collections.concurrent;

import java.util.concurrent.CopyOnWriteArrayList;

/**
 * 1.这个类算是ArrayList的线程安全的实现
 * 2.这个类特别适合少写多读取的场景
 * 3.这种设计的思想其实是，写的时候新建一个容器然后加锁，而后再将容器引用指向新容器，
 * 但是在此期间可能有读的线程，但是它可以接受这样
 * 4.如果是写过多的场景，建议不要使用这个
 * */
public class CopyOnWriteArrayListExample {

    public static void main(String[] args) {
        final CopyOnWriteArrayList<String> list = new CopyOnWriteArrayList();


    }
}
