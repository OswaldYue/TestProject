package com.mgw.three.collections.custom;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.IntStream;

public class LockFreeQueue<E> {

    private AtomicReference<Node<E>> head,tail;

    private AtomicInteger size = new AtomicInteger();

    public LockFreeQueue() {
        Node<E> node = new Node<E>(null);
        this.head = new AtomicReference<>(node);
        this.tail = new AtomicReference<>(node);
    }

    public void addLast(E e) {
        if (e == null) {
            throw new NullPointerException("The null element not allow");
        }
        Node<E> newNode = new Node<>(e);
        Node<E> previousNode = tail.getAndSet(newNode);
        previousNode.next=newNode;
        size.incrementAndGet();
    }

    public E removeFirst() {

        Node<E> headNode,valueNode;
        do {
            headNode = head.get();
            valueNode = headNode.next;

        }while (valueNode != null && !head.compareAndSet(headNode,valueNode));

        E value = (valueNode!=null ?valueNode.element : null);
        if (valueNode != null) {
            valueNode.element = null;
        }
        size.decrementAndGet();
        return value;
    }

    public boolean isEmpty() {
        synchronized (this) {
            return size.get() == 0;
        }
    }

    private static class Node<E> {

        E element;
        volatile Node<E> next;

        public Node(E element) {
            this.element = element;
        }

        @Override
        public String toString() {
            return element==null ? "":element.toString();
        }
    }


    public static void main(String[] args) throws InterruptedException {

        final LockFreeQueue<Long> queue = new LockFreeQueue<>();

        final ExecutorService executorService = Executors.newFixedThreadPool(10);

        AtomicInteger counter = new AtomicInteger();
        IntStream.range(0,5).boxed().map(i-> {
            return (Runnable)() -> {
                while (counter.getAndIncrement()<=10000) {
                    try {
                        TimeUnit.MILLISECONDS.sleep(5);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    queue.addLast(System.nanoTime());
                }
            };
        }).forEach(executorService::submit);

        IntStream.range(0,5).boxed().map(i-> {
            return (Runnable)() -> {
                while (counter.getAndDecrement() > 0) {
                    try {
                        TimeUnit.MILLISECONDS.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(queue.removeFirst());
                }
            };
        }).forEach(executorService::submit);

        executorService.shutdown();
        executorService.awaitTermination(1,TimeUnit.HOURS);
    }

}
