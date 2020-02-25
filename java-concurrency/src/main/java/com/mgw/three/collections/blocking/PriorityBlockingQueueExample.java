package com.mgw.three.collections.blocking;


import java.util.AbstractQueue;
import java.util.concurrent.PriorityBlockingQueue;

/***/
public class PriorityBlockingQueueExample {

    /**
     * 1.容量大小可以大到撑爆JVM，也可以自定义大小,也就是无边界的queue
     * 2.是由数组构成的
     * 3.这个queue是具有优先级的，依赖于Comparable
     * 4.拿的时候可能会block，因为queue会空
     * */
    public static  <T> PriorityBlockingQueue<T> create(int size) {
        return new PriorityBlockingQueue<>(size);
    }

    /**
     * {@link PriorityBlockingQueue#add(java.lang.Object)}
     * {@link PriorityBlockingQueue#put(java.lang.Object)}
     * {@link PriorityBlockingQueue#offer(java.lang.Object)}
     * Inserts the specified element into this priority queue.
     * As the queue is unbounded, this method will never block.
     *
     * 1.如果插入的数据是null,抛出NullPointerException
     * 2.if the specified element cannot be compared
     * with elements currently in the priority queue according to the
     * priority queue's ordering,抛出ClassCastException
     * 3.可以增加超过自定义容量，因为可以自动扩容
     *
     * add与put底层全部调用的是offer
     * 增加数据
     * */
    public static void testAddNewElement() {

        PriorityBlockingQueue<String> queue = create(5);

        System.out.println(queue.add("C"));
        System.out.println(queue.add("D"));
        System.out.println(queue.add("F"));
        System.out.println(queue.add("B"));
        System.out.println(queue.add("A"));
        System.out.println(queue.add("E"));

        System.out.println("queue size is : " + queue.size());
    }

    /**
     * {@link PriorityBlockingQueue#poll()} 取数据，如果空了就返回null
     * {@link PriorityBlockingQueue#take()} 如果queue是空的，会wait
     * {@link PriorityBlockingQueue#peek()} 取数据，每次只是拿头的数据，但是不删除数据，所以queue的长度不变,queue为空，则返回null
     * {@link AbstractQueue#remove()} 取数据，空了再remove就抛异常
     * {@link PriorityBlockingQueue#remove(java.lang.Object)} 取数据，如果remove的没有这个元素，就返回false
     * {@link AbstractQueue#element()} 与peek方法一样，但是如果queue中为空，就抛异常NoSuchElementException
     *
     * */
    public static void testGetElement() {

        PriorityBlockingQueue<String> queue = create(5);

        System.out.println(queue.add("C"));
        System.out.println(queue.add("D"));
        System.out.println(queue.add("F"));
        System.out.println(queue.add("B"));
        System.out.println(queue.add("A"));
        System.out.println(queue.add("E"));
        System.out.println("queue size is : " + queue.size());

        System.out.println(queue.poll());
        System.out.println("queue size is : " + queue.size());
    }


    public static void main(String[] args) {
//        testAddNewElement();
        testGetElement();
    }

}
