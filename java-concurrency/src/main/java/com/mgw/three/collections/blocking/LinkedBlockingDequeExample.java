package com.mgw.three.collections.blocking;

import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * 与{@link LinkedBlockingQueue}差不多，只是LinkedBlockingQueue是单向的，而LinkedBlockingDeque是双向的
 *
 * */
public class LinkedBlockingDequeExample {

    /**
     * 1.双向的链表，有first与last两个指针
     * 2.An optionally-bounded  blocking deque based on
     * linked nodes.可选边界
     *
     * */
    public static <T> LinkedBlockingDeque<T> create() {
        return new LinkedBlockingDeque();
    }


    /**
     * {@link LinkedBlockingDeque#add(Object)}
     * 使用addLast方法，从后插入
     * {@link LinkedBlockingDeque#offer(Object)}
     * 使用offerLast方法，从后面开始插入
     * {@link LinkedBlockingDeque#put(Object)}
     * 使用putLast方法，从后面开始插入
     *
     * 无边界，此三个方法都不会阻塞
     *
     * */
    public static void testInsert() {

        LinkedBlockingDeque<String> deque = create();
        deque.add("A");
        deque.offer("B");
        deque.push("C");
        System.out.println("queue size is : " + deque.size());
    }

    /**
     * {@link LinkedBlockingDeque#poll()} 取数据，从头开始取，如果空了就返回null
     * {@link LinkedBlockingDeque#take()} 取数据，从头开始取，如果queue是空的，会wait
     * {@link LinkedBlockingDeque#peek()} 取数据，每次只是拿头的数据，但是不删除数据，所以queue的长度不变，如果为空，则返回null
     * {@link LinkedBlockingDeque#remove()} 取数据，从头开始取，空了再remove就抛异常NoSuchElementException
     * {@link LinkedBlockingDeque#remove(java.lang.Object)} 取数据，如果remove的没有这个元素，就返回false
     * {@link LinkedBlockingDeque#element()} 与peek方法一样，但是如果queue中为空，就抛异常NoSuchElementException
     *
     * */
    public static void testRemove() {
        LinkedBlockingDeque<String> deque = create();
        deque.add("A");
        deque.add("B");

    }

    public static void main(String[] args) {
        testInsert();
    }
}
