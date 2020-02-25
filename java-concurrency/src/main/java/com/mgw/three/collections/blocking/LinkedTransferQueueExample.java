package com.mgw.three.collections.blocking;

import java.util.List;
import java.util.concurrent.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class LinkedTransferQueueExample {

    /**
     * 1.无边界的queue
     *
     *
     * 使用场景：TransferQueue的使用场景是它必须担保数据被consumer使用了，也就是说我递出去的东西必须被消费
     * */
    public static <T> LinkedTransferQueue<T> create() {
        return new LinkedTransferQueue();

    }

    /**
     * {@link TransferQueue#tryTransfer(java.lang.Object)}
     * Transfers the element to a waiting consumer immediately, if possible.这个是立即返回的，能插入就插入,返回true，不能插入就返回false
     * 疑问:当返回是false时，意味着在此时没有消费者等待，那么这个元素是怎么存储进queue中的？
     * 回答: without enqueuing the element 不会添加进queue
     *
     * */
    public static void testTryTransfer() throws InterruptedException {
        final LinkedTransferQueue<String> queue = create();
        System.out.println(queue.tryTransfer("A"));

        System.out.println("queue size is : " + queue.size());
    }

    /**
     * {@link TransferQueue#transfer(java.lang.Object)}
     * inserts the specified element at the tail of this queue
     * and waits until the element is received by a consumer
     * 插入这个元素，如果没有消费者消费掉，则一直等下去，这个是waiting等待的
     * */
    public static void testTransfer() throws InterruptedException {
        final LinkedTransferQueue<String> queue = create();
        // 直接加入而没有消费掉，是会无限期等待的
//        queue.transfer("A");

        // 使用消费者去定时消费掉
        final ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
        executorService.schedule(() -> {
            try {
                System.out.println(queue.take());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },1,TimeUnit.SECONDS);
        executorService.shutdown();
        queue.transfer("B");

        System.out.println("queue size is : " + queue.size());
    }

    /**
     * {@link TransferQueue#hasWaitingConsumer()}
     * {@link TransferQueue#getWaitingConsumerCount()}
     * */
    public static void testTransfer2() throws InterruptedException {
        final LinkedTransferQueue<String> queue = create();
        // fasle
        System.out.println("hasWaitingConsumer ? " + queue.hasWaitingConsumer());
        // 0
        System.out.println("getWaitingConsumerCount is " + queue.getWaitingConsumerCount());

        final List<Callable<String>> callableList = IntStream.range(0, 5).boxed().map(i -> (Callable<String>) () -> {
            try {
                return queue.take();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }).collect(Collectors.toList());

        final ExecutorService executorService = Executors.newCachedThreadPool();
        callableList.stream().forEach(callable -> {

            executorService.submit(callable);
        });

        TimeUnit.SECONDS.sleep(1);
        // true
        System.out.println("hasWaitingConsumer ? " + queue.hasWaitingConsumer());
        // 5
        System.out.println("getWaitingConsumerCount is " + queue.getWaitingConsumerCount());

        // 生产资源
        IntStream.range(0, 5).boxed().map(String::valueOf).forEach(queue :: add);
        System.out.println("queue size is : " + queue.size());
    }

    /**
     * {@link LinkedTransferQueue#offer(java.lang.Object)} 添加数据，永远返回ture,异步的添加数据
     * {@link LinkedTransferQueue#add(Object)} 添加数据，永远返回ture,异步的添加数据
     * {@link LinkedTransferQueue#put(Object)} 添加数据，永不阻塞,异步的添加数据
     * */
    public static void testInsert() throws InterruptedException {
        final LinkedTransferQueue<String> queue = create();

        queue.add("A");
        queue.add("B");
        queue.offer("C");

        System.out.println("queue size is " + queue.size());
    }

    /**
     * {@link LinkedTransferQueue#poll()} 取数据，如果空了就返回null,立即返回，能取到数据就取，取不到就返回null
     * {@link LinkedTransferQueue#take()} 如果queue是空的，会wait
     * {@link LinkedTransferQueue#peek()} 取数据，每次只是拿头的数据，但是不删除数据，所以queue的长度不变,如果为空，则为null
     * {@link LinkedTransferQueue#remove()} 取数据，空了再remove就抛异常NoSuchElementException
     * {@link LinkedTransferQueue#remove(java.lang.Object)} 取数据，如果remove的没有这个元素，就返回false
     * {@link LinkedTransferQueue#element()} 与peek方法一样，但是如果queue中为空，就抛异常NoSuchElementException
     *
     * */
    public static void testRemove() throws InterruptedException {
        final LinkedTransferQueue<String> queue = create();
    }

    public static void main(String[] args) throws InterruptedException {

//        testTryTransfer();
//        testTransfer();
//        testTransfer2();
        testInsert();
    }
}
