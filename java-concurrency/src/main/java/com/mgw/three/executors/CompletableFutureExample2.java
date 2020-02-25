package com.mgw.three.executors;

import java.util.List;
import java.util.concurrent.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class CompletableFutureExample2 {

    /**
     * Future的问题
     * */
    private static void test1() throws ExecutionException, InterruptedException {
        final ExecutorService executorService = Executors.newFixedThreadPool(10);

        // 异步调用
        final Future<?> future = executorService.submit(() -> {
            try {
                TimeUnit.SECONDS.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        // 阻塞的拿结果
        future.get();
    }

    /**
     * {@link CompletableFuture#runAsync(java.lang.Runnable)}
     * 如果使用{@link java.util.concurrent.CompletableFuture#runAsync(Runnable)} 那么里面的任务可能执行不完，因为它设置成了守护线程
     * 随着主线程结束就结束了
     *
     * {@link CompletableFuture#runAsync(Runnable, Executor)} 这个需要给个线程池，但是线程池需要关闭，否则它不管的
     *
     * */
    private static void test2() throws ExecutionException, InterruptedException {
        CompletableFuture.runAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },Executors.newSingleThreadExecutor()).whenComplete((v,t) -> {
            System.out.println("DONE");
        });
        System.out.println("此处非阻塞");

        Thread.currentThread().join();
    }

    private static int get() {
        int anInt = ThreadLocalRandom.current().nextInt(20);
        try {
            System.out.println(Thread.currentThread().getName() + " get()  will be sleep "+anInt+" seconds");
            TimeUnit.SECONDS.sleep(anInt);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + " get() execute done,need "+anInt+" seconds");

        return anInt;
    }

    private static void disPlay(int data) {
        int anInt = ThreadLocalRandom.current().nextInt(20);
        try {
            System.out.println(Thread.currentThread().getName() + " disPlay() will be sleep "+anInt+" seconds");
            TimeUnit.SECONDS.sleep(anInt);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + " disPlay() execute done,value is "+data);

    }

    /**
     * executorService提交一批任务，有些需要的世间长，有些需要的时间短，短的需要等待长的任务，这样的问题展示如test3
     * */
    private static void test3() throws ExecutionException, InterruptedException {

        final ExecutorService executorService = Executors.newFixedThreadPool(10);

        final List<Callable<Integer>> tasks = IntStream.range(0, 10).boxed().map(
                i -> (Callable<Integer>) () -> get()
        ).collect(Collectors.toList());

        executorService.invokeAll(tasks).stream().map(future -> {
            try {
                return future.get();
            } catch (Exception e) {
                throw new  RuntimeException(e);
            }
        }).parallel().forEach(CompletableFutureExample2::disPlay);

    }

    /**
     * 解决test3的问题
     * */
    private static void test4() throws ExecutionException, InterruptedException {

        IntStream.range(0, 10).boxed().forEach(i -> {
            CompletableFuture.supplyAsync(CompletableFutureExample2::get).
                    thenAccept(CompletableFutureExample2::disPlay).
                    whenComplete((v,t) ->{
                        System.out.println(Thread.currentThread().getName() + "线程，编号 " + i + " DONE");
                    });
        });

        Thread.currentThread().join();
    }


    public static void main(String[] args) throws ExecutionException, InterruptedException {
//        test1();
//        test2();
//        test3();
        test4();
    }

}
