package com.mgw.three.executors;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class ExecutorServiceExample2 {


    /**
     * {@link ThreadPoolExecutor.AbortPolicy}
     * 直接拒绝
     * */
    private static void testAbortPolicy() {

        ExecutorService executorService = new ThreadPoolExecutor(
                1,
                2,
                30,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(1),
                r -> {
                    return  new Thread(r);
                },
                new ThreadPoolExecutor.AbortPolicy());

        // 提交3个任务 一个核心执行，queue满了，故要开辟新线程去执行，刚刚好开辟两个
        for (int i = 0 ; i < 3 ; i++) {
            executorService.execute(() -> {
                try {
                    TimeUnit.SECONDS.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }

        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(((ThreadPoolExecutor)executorService).getActiveCount());
        System.out.println(((ThreadPoolExecutor)executorService).getQueue().size());

        executorService.execute(() -> System.out.println("再提交一个任务"));

    }

    /**
     * {@link ThreadPoolExecutor.DiscardPolicy}
     * 直接放弃，啥事不做，就像没看见一样 看源码就知道了
     * */
    private static void testDiscardPolicy() {
        ExecutorService executorService = new ThreadPoolExecutor(
                1,
                2,
                30,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(1),
                r -> {
                    return  new Thread(r);
                },
                new ThreadPoolExecutor.DiscardPolicy());

        // 提交3个任务 一个核心执行，queue满了，故要开辟新线程去执行，刚刚好开辟两个
        for (int i = 0 ; i < 3 ; i++) {
            executorService.execute(() -> {
                try {
                    TimeUnit.SECONDS.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        executorService.execute(() -> System.out.println("再提交一个任务"));
        System.out.println("======testDiscardPolicy======");
    }

    /**
     * {@link ThreadPoolExecutor.CallerRunsPolicy}
     * 直接在你调用的线程中执行run方法，属于阻塞的形式执行
     * */
    private static void testCallerRunsPolicy() {
        ExecutorService executorService = new ThreadPoolExecutor(
                1,
                2,
                30,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(1),
                r -> {
                    return  new Thread(r);
                },
                new ThreadPoolExecutor.CallerRunsPolicy());

        // 提交3个任务 一个核心执行，queue满了，故要开辟新线程去执行，刚刚好开辟两个
        for (int i = 0 ; i < 3 ; i++) {
            executorService.execute(() -> {
                try {
                    TimeUnit.SECONDS.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        executorService.execute(() -> {
            System.out.println("再提交一个任务");
            // main
            System.out.println("执行线程是:" + Thread.currentThread().getName());
        });
        System.out.println("======testCallerRunsPolicy======");
    }

    /**
     * {@link ThreadPoolExecutor.DiscardOldestPolicy}
     * 丢掉queue中的一个待任务，将我最新提交的那个放入queue
     * */
    private static void testDiscardOldestPolicy() {

        ExecutorService executorService = new ThreadPoolExecutor(
                1,
                2,
                30,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(1),
                r -> {
                    return  new Thread(r);
                },
                new ThreadPoolExecutor.DiscardOldestPolicy());

        // 提交3个任务 一个核心执行，queue满了，故要开辟新线程去执行，刚刚好开辟两个
        final AtomicInteger atomicInteger = new AtomicInteger();
        for (int i = 0 ; i < 3 ; i++) {
            executorService.execute(() -> {
                try {
                    System.out.println("提交的第"+atomicInteger.getAndIncrement()+"个任务");
                    TimeUnit.SECONDS.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        executorService.execute(() -> {
            System.out.println("再提交一个任务");
        });
        System.out.println("======testDiscardOldestPolicy======");
    }

    public static void main(String[] args) {
//        testAbortPolicy();
//        testDiscardPolicy();
//        testCallerRunsPolicy();
        testDiscardOldestPolicy();
    }

}
