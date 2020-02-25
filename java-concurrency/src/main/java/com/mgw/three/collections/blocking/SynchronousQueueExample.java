package com.mgw.three.collections.blocking;

import java.util.AbstractQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;

public class SynchronousQueueExample {

    /**
     * 1.A blocking queue in which each insert
     * operation must wait for a corresponding remove operation by another
     * thread, and vice versa.只能放一个数据，每个被插入的数据，必须等待queue的数据被拿走，反之亦然
     * 2.A synchronous queue does not have any
     * internal capacity, not even a capacity of one 没有任何容量，甚至一个都没，相当于交换器
     * 3.You cannot peek() at a synchronous queue because an element is only
     * present when you try to remove it; you cannot insert an element
     * (using any method) unless another thread is trying to remove it;不能使用peek,不能插入数据
     * 4.you cannot iterate as there is nothing to iterate 不能迭代它
     * */
    public static <T> SynchronousQueue<T> create() {

        return new SynchronousQueue();
    }

    /**
     * {@link AbstractQueue#add(java.lang.Object)}
     * 不能插入数据，只能一个线程取出数据，一个线程插入数据才行
     * */
    public static void testAdd() throws InterruptedException {

        SynchronousQueue<String> queue = create();
        // java.lang.IllegalStateException: Queue full
//        System.out.println(queue.add("A"));

        // 下面用法才可以
        Executors.newSingleThreadExecutor().submit(() -> {
            try {
                queue.take();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        TimeUnit.SECONDS.sleep(1);
        System.out.println(queue.add("B"));

    }

    /**
     * {@link SynchronousQueue#offer(java.lang.Object)}
     * 不能插入
     * */
    public static void testOffer() throws InterruptedException {
        SynchronousQueue<String> queue = create();
        // false
//        System.out.println(queue.offer("A"));

        // 下面用法才可以
        Executors.newSingleThreadExecutor().submit(() -> {
            try {
                queue.take();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        TimeUnit.SECONDS.sleep(1);
        System.out.println(queue.offer("B"));
    }
    /**
     * {@link SynchronousQueue#put(Object)}
     * */
    public static void testPut() throws InterruptedException {
        SynchronousQueue<String> queue = create();
        // 直接waiting
//        queue.put("A");

        // 下面用法才可以
        Executors.newSingleThreadExecutor().submit(() -> {
            try {
                queue.take();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        TimeUnit.SECONDS.sleep(1);
        System.out.println(queue.offer("B"));
    }

    /**
     * {@link SynchronousQueue#poll()} 取数据，如果空了就返回null
     * {@link SynchronousQueue#take()} 如果queue是空的，会wait
     * {@link SynchronousQueue#peek()} 取数据，直接返回null
     * {@link SynchronousQueue#remove()} 取数据，依赖poll方法，空了再remove就抛异常NoSuchElementException
     * {@link SynchronousQueue#remove(java.lang.Object)} 取数据，直接返回false
     * {@link SynchronousQueue#element()} 调用peek方法，抛出异常NoSuchElementException
     *
     * */
    public static void testGet() throws InterruptedException {

    }

    public static void main(String[] args) throws InterruptedException {
//        testAdd();
//        testOffer();
//        testPut();
    }

}
