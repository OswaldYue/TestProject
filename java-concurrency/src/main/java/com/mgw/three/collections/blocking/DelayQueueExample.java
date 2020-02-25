package com.mgw.three.collections.blocking;

import java.util.Iterator;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;


/**
 * 1.无边界的queueu
 * 2.element必须是继承Delayed接口
 * 3.插入时排序由compareTo方法决定，取出时延时由getDelay方法决定
 * */
public class DelayQueueExample {


    /**
     * 1.DelayQueue是无边界的，因为底层使用的是PriorityBlockingQueue这个queue
     * 2.element必须是继承了Delayed接口的
     * */
    public static <T extends Delayed> DelayQueue<T> create() {
        return new DelayQueue();
    }

    /**
     * {@link DelayQueue#add(java.util.concurrent.Delayed)}
     * {@link DelayQueue#offer(java.util.concurrent.Delayed)}
     * {@link DelayQueue#put(java.util.concurrent.Delayed)}
     *
     * add与put都是使用的offer,不存在插入时阻塞这一说
     *
     * */
    public static void testAdd() throws InterruptedException {

        final DelayQueue<DelayElement<String>> queue = create();
        System.out.println(queue.add(DelayElement.of("A", 1000)));
        System.out.println("queue size is : " + queue.size());
        long start = System.currentTimeMillis();
        System.out.println(queue.peek());
        long end = System.currentTimeMillis();
        System.out.println("Time is : " + (end-start));
    }

    /**
     * {@link DelayQueue#peek()} 取数据，每次只是拿头的数据，但是不删除数据，所以queue的长度不变,也是直接从queue中取，不存在延时
     * {@link DelayQueue#element()} 与peek方法一样，但是如果queue中为空，就抛异常NoSuchElementException
     * */
    public static void testPeek() throws InterruptedException {

        final DelayQueue<DelayElement<String>> queue = create();
        System.out.println(queue.add(DelayElement.of("A", 2000)));
        System.out.println(queue.add(DelayElement.of("B", 1000)));
        System.out.println(queue.add(DelayElement.of("C", 3000)));
        System.out.println("queue size is : " + queue.size());
        long start = System.currentTimeMillis();
        System.out.println(queue.peek().getData());
        long end = System.currentTimeMillis();
        System.out.println("Time is : " + (end-start));
    }

    /**
     * {@link DelayQueue#take()} 如果queue是空的，会wait，如果不是空的，则会延时相应的时间
     * */
    public static void testTake() throws InterruptedException {

        final DelayQueue<DelayElement<String>> queue = create();
        System.out.println(queue.add(DelayElement.of("A", 4000)));
        System.out.println(queue.add(DelayElement.of("B", 1000)));
        System.out.println(queue.add(DelayElement.of("C", 3000)));
        System.out.println("queue size is : " + queue.size());
        long start = System.currentTimeMillis();
        System.out.println(queue.take().getData());
        long end = System.currentTimeMillis();
        System.out.println("Time is : " + (end-start));
    }

    /**
     * {@link DelayQueue#poll()} 取数据，如果空了或者没有过期的element返回null
     * Retrieves and removes the head of this queue, or returns null
     * if this queue has no elements with an expired delay.
     * 这个方法不会wait,如果返回的是null,不代表queue中没有元素，可能只是里面没有过期的元素
     * {@link DelayQueue#remove()} 取数据，依赖于poll,如果poll返回是null，remove就抛异常NoSuchElementException
     *
     * */
    public static void testPoll() throws InterruptedException {
        final DelayQueue<DelayElement<String>> queue = create();
        queue.add(DelayElement.of("A", 4000));
        queue.add(DelayElement.of("D", 1000));
        queue.add(DelayElement.of("B", 6000));
        queue.add(DelayElement.of("F", 3000));
        queue.add(DelayElement.of("C", 4000));

        long start = System.currentTimeMillis();
        System.out.println(queue.poll().getData());
        long end = System.currentTimeMillis();
        System.out.println("Time is : " + (end-start));
    }

    /**
     * {@link DelayQueue#remove(java.lang.Object)} 取数据，如果remove的没有这个元素，就返回false,不存在过期时间的问题，立即返回
     * */
    public static void testRemoveObject() throws InterruptedException {
        final DelayQueue<DelayElement<String>> queue = create();
        final DelayElement<String> delayElement1 = DelayElement.of("A", 4000);
        final DelayElement<String> delayElement2 = DelayElement.of("D", 1000);
        queue.add(delayElement1);
        queue.add(delayElement2);

        long start = System.currentTimeMillis();
        System.out.println(queue.remove(delayElement2));
        long end = System.currentTimeMillis();
        System.out.println("Time is : " + (end-start));

    }

    /**
     * {@link DelayQueue#iterator()}
     * 使用迭代器取出的值，没有延时
     * */
    public static void testIterator() {
        final DelayQueue<DelayElement<String>> queue = create();
        queue.add(DelayElement.of("A", 4000));
        queue.add(DelayElement.of("D", 1000));
        queue.add(DelayElement.of("B", 6000));
        queue.add(DelayElement.of("F", 3000));
        queue.add(DelayElement.of("C", 4000));

        Iterator<DelayElement<String>> iterator = queue.iterator();
        while (iterator.hasNext()) {
            DelayElement<String> element = iterator.next();
            System.out.println(element.getData());
        }

    }

    public static void main(String[] args) throws InterruptedException {

//        testIterator();
//        testAdd();
//        testPeek();
//        testTake();
//        testPoll();
        testRemoveObject();
    }

    /**
     * 自定义Delayed类
     * */
    static class DelayElement<E> implements Delayed {

        private final E e;

        // 失效时间
        private final long expireTime;

        DelayElement(E e, long delay) {
            this.e = e;
            this.expireTime = System.currentTimeMillis() + delay;
        }

        static <E> DelayElement<E> of(E e,long delay) {
            return new DelayElement<>(e,delay);
        }

        @Override
        public long getDelay(TimeUnit unit) {
            long diff = this.expireTime - System.currentTimeMillis();
            return unit.convert(diff,TimeUnit.MILLISECONDS);
        }

        @Override
        public int compareTo(Delayed delayedObject) {
            DelayElement that = (DelayElement) delayedObject;
            if (this.expireTime<that.getExpireTime()) {
                return -1;
            }else if (this.expireTime>that.getExpireTime()) {
                return 1;
            } else {
                return 0;
            }
        }

        public E getData() {
            return e;
        }

        public long getExpireTime() {
            return expireTime;
        }
    }


}
