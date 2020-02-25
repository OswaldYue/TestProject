package com.mgw.three.utils.condition;

import java.util.LinkedList;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.IntStream;

public class ConditionExample3 {

    private final static ReentrantLock LOCK = new ReentrantLock();

    private final static Condition PRODUCE_CONDITION = LOCK.newCondition();

    private final static Condition CONSUME_CONDITION = LOCK.newCondition();

    private final static LinkedList<Long> TIMESTAMP_POOL = new LinkedList<>();

    private final static int MAX_CAPACITY = 100;

    private static void sleep(long milliseconds) {

        try {
            TimeUnit.MILLISECONDS.sleep(milliseconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void produce() {

        try {
            LOCK.lock();
            while (TIMESTAMP_POOL.size() >= MAX_CAPACITY) {
                PRODUCE_CONDITION.await();
            }
            sleep(500);
            System.out.println("PRODUCE_CONDITION.getWaitQueueLength =>" + LOCK.getWaitQueueLength(PRODUCE_CONDITION));
            System.out.println("PRODUCE_CONDITION.hasWaiters ==>" + LOCK.hasWaiters(PRODUCE_CONDITION));
            long value = System.currentTimeMillis();
            System.out.println(Thread.currentThread().getName() + "-PPP: " + value);
            TIMESTAMP_POOL.addLast(value);

            CONSUME_CONDITION.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            LOCK.unlock();
        }
    }

    private static void consume() {

        try {
            LOCK.lock();
            while (TIMESTAMP_POOL.isEmpty()) {
                CONSUME_CONDITION.await();
            }
            sleep(1000);
            System.out.println("CONSUME_CONDITION.getWaitQueueLength => " + LOCK.getWaitQueueLength(CONSUME_CONDITION));
            System.out.println("CONSUME_CONDITION.hasWaiters ==> " + LOCK.hasWaiters(CONSUME_CONDITION));
            long value = TIMESTAMP_POOL.removeFirst();
            System.out.println(Thread.currentThread().getName() + "-CCC: " + value);

            PRODUCE_CONDITION.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            LOCK.unlock();
        }
    }

    public static void main(String[] args) {

        IntStream.range(0,3).forEach(i -> {
            new Thread(() -> {
                for (;;) {
                    produce();
                }
            },"P"+i).start();
        });

        IntStream.range(0,5).forEach(i -> {
            new Thread(() ->{
                for (;;) {
                    consume();
                }
            },"C"+i).start();
        });

//        for (;;) {
//            sleep(5000);
//            System.out.println("PRODUCE_CONDITION.getWaitQueueLength =>" + LOCK.getWaitQueueLength(PRODUCE_CONDITION));
//            System.out.println("CONSUME_CONDITION.getWaitQueueLength => " + LOCK.getWaitQueueLength(CONSUME_CONDITION));
//
//            System.out.println("PRODUCE_CONDITION.hasWaiters ==>" + LOCK.hasWaiters(PRODUCE_CONDITION));
//            System.out.println("CONSUME_CONDITION.hasWaiters ==> " + LOCK.hasWaiters(CONSUME_CONDITION));
//        }
    }
}
