package com.mgw.three.collections.blocking;

import java.util.AbstractQueue;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ArrayBlockingQueueExample {

    /**
     * 1.This queue orders elements FIFO (first-in-first-out)
     * 2.Once created, the capacity cannot be changed
     * 3.Attempts to {@code put} an element into a full queue  will result in the operation blocking;
     * attempts to {@code take} an element from an empty queue will similarly block.
     * */
    public static  <T> ArrayBlockingQueue<T> create(int size) {

        return new ArrayBlockingQueue<T>(size);
    }


    /**
     * {@link ArrayBlockingQueue#add(java.lang.Object)}
     * Inserts the specified element at the tail of this queue if it is
     * possible to do so immediately without exceeding the queue's capacity,
     * returning {@code true} upon success and throwing an
     * {@code IllegalStateException} if this queue is full.
     * 增加数据，增加时，如果没有超过容量时，会立即加入进去
     *
     * */
    public static void testAdd() {

        ArrayBlockingQueue<String> queue = create(5);
        // 容量未满
        System.out.println(queue.add("A"));
        System.out.println(queue.add("B"));
        System.out.println(queue.add("C"));
        System.out.println(queue.add("D"));
        System.out.println(queue.add("E"));
        System.out.println("queue size = " + queue.size());
        // 容量已满 抛异常
        // java.lang.IllegalStateException: Queue full
        System.out.println(queue.add("F"));
        System.out.println("queue size = " + queue.size());

    }

    /**
     * {@link {@link ArrayBlockingQueue#offer(Object)}
     *
     * Inserts the specified element at the tail of this queue if it is
     * possible to do so immediately without exceeding the queue's capacity,
     * returning {@code true} upon success and {@code false} if this queue
     * is full.  This method is generally preferable to method {@link #add},
     * which can fail to insert an element only by throwing an exception.
     *
     * @throws NullPointerException if the specified element is null
     *
     * 增加数据，与add方法类似，只是满了时不会抛异常，而是返回false
     */
    public static void testOffer() {
        ArrayBlockingQueue<String> queue = create(5);
        System.out.println(queue.offer("A"));
        System.out.println(queue.offer("B"));
        System.out.println(queue.offer("C"));
        System.out.println(queue.offer("D"));
        System.out.println(queue.offer("E"));
        System.out.println("queue size = " + queue.size());
        // 满了就返回false
        System.out.println(queue.offer("F"));
        System.out.println("queue size = " + queue.size());
    }

    /**
     * {@link ArrayBlockingQueue#put(java.lang.Object)}
     * Inserts the specified element at the tail of this queue, waiting
     * for space to become available if the queue is full.
     *
     * @throws InterruptedException {@inheritDoc}
     * @throws NullPointerException {@inheritDoc}
     *
     * 增加数据，如果满了就等待
     */
    public static void testPut() throws InterruptedException {

        ArrayBlockingQueue<String> queue = create(5);
        final ScheduledExecutorService service = Executors.newSingleThreadScheduledExecutor();
        service.schedule(() -> {
            try {
                System.out.println("take is :" + queue.take());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },5, TimeUnit.SECONDS);
        queue.put("A");
        queue.put("B");
        queue.put("C");
        queue.put("D");
        queue.put("E");
        System.out.println("queue size = " + queue.size());
        // 满了就等待
        queue.put("F");
        System.out.println("queue size = " + queue.size());

        // 关闭线程池
        service.shutdown();
    }

    /**
     * {@link ArrayBlockingQueue#poll()}
     *
     * Retrieves and removes the head of this queue,
     * or returns {@code null} if this queue is empty.
     *
     * @return the head of this queue, or {@code null} if this queue is empty
     *
     * 取数据，如果空了就返回null
     */
    public static void testPoll() throws InterruptedException {
        ArrayBlockingQueue<String> queue = create(5);
        System.out.println(queue.offer("A"));
        System.out.println(queue.offer("B"));
        System.out.println(queue.offer("C"));
        System.out.println("queue size = " + queue.size());

        System.out.println(queue.poll());
        System.out.println(queue.poll());
        System.out.println(queue.poll());
        System.out.println("queue size = " + queue.size());

        // 如果queue已经空了，就返回null
        System.out.println(queue.poll());
        System.out.println("queue size = " + queue.size());
    }


    /**
     * {@link ArrayBlockingQueue#remove()}
     *
     * Retrieves and removes the head of this queue.  This method differs
     * from  poll() only in that it throws an exception if this
     * queue is empty.
     *
     * <p>This implementation returns the result of <tt>poll</tt>
     * unless the queue is empty.
     *
     * @return the head of this queue
     * @throws NoSuchElementException if this queue is empty
     *
     * 取数据，空了再remove就抛异常
     */
    public static void testRemove() throws InterruptedException {
        ArrayBlockingQueue<String> queue = create(5);
        System.out.println(queue.offer("A"));
        System.out.println(queue.offer("B"));
        System.out.println("queue size = " + queue.size());

        System.out.println(queue.remove());
        System.out.println(queue.remove());
        System.out.println("queue size = " + queue.size());

        System.out.println(queue.remove());
        System.out.println("queue size = " + queue.size());
    }

    /**
     * {@link ArrayBlockingQueue#remove(Object)}
     * 取数据，如果remove的没有这个元素，就返回false
     * */
    public static void testRemoveObject() throws InterruptedException {
        ArrayBlockingQueue<String> queue = create(5);
        System.out.println(queue.offer("A"));
        System.out.println(queue.offer("B"));
        System.out.println(queue.offer("A"));
        System.out.println("queue size = " + queue.size());

        System.out.println(queue.remove("A"));
        System.out.println(queue.remove("B"));
        System.out.println("queue size = " + queue.size());

        System.out.println(queue.remove("C"));
        System.out.println("queue size = " + queue.size());
    }

    /**
     * {@link ArrayBlockingQueue#peek()}
     *
     * Retrieves, but does not remove, the head of this queue,
     * or returns {@code null} if this queue is empty.
     *
     * @return the head of this queue, or {@code null} if this queue is empty
     *
     * 取数据，每次只是拿头的数据，但是不删除数据，所以queue的长度不变
     */
    public static void testPeek() throws InterruptedException {
        ArrayBlockingQueue<String> queue = create(5);
        System.out.println(queue.offer("A"));
        System.out.println(queue.offer("B"));
        System.out.println("queue size = " + queue.size());

        System.out.println(queue.peek());
        System.out.println(queue.peek());
        System.out.println("queue size = " + queue.size());
    }

    /**
     * {@link ArrayBlockingQueue#take()}
     *
     * Retrieves and removes the head of this queue, waiting if necessary
     * until an element becomes available.
     *
     * @return the head of this queue
     * @throws InterruptedException if interrupted while waiting
     *
     * 取数据，如果queue空了就等待
     */
    public static void testTake() throws InterruptedException {
        ArrayBlockingQueue<String> queue = create(5);
        System.out.println(queue.offer("A"));
        System.out.println(queue.offer("B"));
        System.out.println("queue size = " + queue.size());

        System.out.println(queue.take());
        System.out.println(queue.take());
        System.out.println("queue size = " + queue.size());

        System.out.println(queue.take());
        System.out.println("queue size = " + queue.size());
    }

    /**
     * {@link AbstractQueue#element()}
     *
     * 与{@link ArrayBlockingQueue#peek()}方法一样
     * 但是如果queue中为空，就抛异常NoSuchElementException
     * */
    public static void testElement() throws InterruptedException {
        ArrayBlockingQueue<String> queue = create(5);
        System.out.println(queue.offer("A"));
        System.out.println(queue.offer("B"));
        System.out.println("queue size = " + queue.size());

        System.out.println(queue.element());
        System.out.println(queue.element());
        System.out.println("queue size = " + queue.size());

        System.out.println(queue.element());
        System.out.println("queue size = " + queue.size());
    }

    /**
     * {@link ArrayBlockingQueue#drainTo(java.util.Collection)
     *
     * 排干，就是将queue中的数据排导入到另一个容器中
     *
     * */
    public static void testDrainTo() {
        ArrayBlockingQueue<String> queue = create(5);
        System.out.println(queue.offer("A"));
        System.out.println(queue.offer("B"));
        System.out.println(queue.offer("C"));
        System.out.println("queue size = " + queue.size());

        List<String> list = new ArrayList<>();

        // 排干到lsit这个容器中
        queue.drainTo(list);
        System.out.println("queue size = " + queue.size());
    }

    public static void main(String[] args) throws InterruptedException {
//        testAdd();
//        testOffer();
//        testPut();
//        testPoll();
//        testRemove();
//        testRemoveObject();
//        testPeek();
//        testTake();
//        testElement();
        testDrainTo();
    }

}
