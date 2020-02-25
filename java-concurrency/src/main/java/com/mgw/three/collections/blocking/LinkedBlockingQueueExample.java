package com.mgw.three.collections.blocking;

import java.util.concurrent.LinkedBlockingQueue;

public class LinkedBlockingQueueExample {

    /**
     * 1.An optionally-bounded blockingQueue based on
     * linked nodes.可选边界
     * 2.New elements are inserted at the tail of the queue,
     * and the queue retrieval  operations obtain elements at the head of the queue.从尾部插入，头部取出
     * 3.Linked queues typically have higher throughput than array-based queues but
     * less predictable performance in most concurrent applications.比array型的queue要高吞吐量
     * */
    public static <T> LinkedBlockingQueue<T> create() {

        return new LinkedBlockingQueue();

    }

    public static <T> LinkedBlockingQueue<T> create(int size) {
        return new LinkedBlockingQueue(size);
    }

    /**
     * {@link LinkedBlockingQueue#offer(java.lang.Object)} 从尾部添加，如果是固定边界的容器，满了就会返回false,成功就返回true
     * {@link LinkedBlockingQueue#add(Object)} 依赖于offer,如果是固定边界的容器，满了就会抛IllegalStateException("Queue full")的异常,成功就返回true
     * {@link LinkedBlockingQueue#put(Object)} 从尾部添加，如果queue满了，则waiting
     * */
    public static void testInsert() throws InterruptedException {

        LinkedBlockingQueue<String> queue = create(5);

        System.out.println(queue.add("A"));
        System.out.println(queue.add("B"));
        System.out.println(queue.add("C"));
        System.out.println(queue.add("D"));
        System.out.println(queue.add("E"));
        System.out.println("queue size is : " + queue.size());

//        System.out.println(queue.add("F"));
//        System.out.println("queue size is : " + queue.size());

        queue.put("F");
        System.out.println("queue size is : " + queue.size());
    }

    /**
     * {@link LinkedBlockingQueue#poll()} 取数据，如果空了就返回null
     * {@link LinkedBlockingQueue#take()} 如果queue是空的，会wait
     * {@link LinkedBlockingQueue#peek()} 取数据，每次只是拿头的数据，但是不删除数据，所以queue的长度不变
     * {@link LinkedBlockingQueue#remove()} 取数据，空了再remove就抛异常NoSuchElementException
     * {@link LinkedBlockingQueue#remove(java.lang.Object)} 取数据，如果remove的没有这个元素，就返回false
     * {@link LinkedBlockingQueue#element()} 与peek方法一样，但是如果queue中为空，就抛异常NoSuchElementException
     *
     * */
    public static void testRemove() throws InterruptedException {
        LinkedBlockingQueue<String> queue = create(5);

        System.out.println(queue.add("A"));
        System.out.println(queue.add("B"));


    }

    public static void main(String[] args) throws InterruptedException {
        testInsert();
    }

}
