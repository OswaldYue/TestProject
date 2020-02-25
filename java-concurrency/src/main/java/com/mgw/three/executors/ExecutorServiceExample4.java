package com.mgw.three.executors;

import java.util.Collection;
import java.util.List;
import java.util.concurrent.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ExecutorServiceExample4 {


    /**
     * {@link ExecutorService#invokeAny(Collection)} 这个方法是blocked的，是同步方法
     * 问题: 当invokeAny方法返回结果时，其他任务还会执行吗？不会，其他任务会在其中一个执行完毕后取消其他未执行的任务
     * */
    private static void testInvokeAny() throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(10);

        final List<Callable<Integer>> callableList = IntStream.range(0, 5).boxed().map(
                i -> (Callable<Integer>) () -> {
                    System.out.println(Thread.currentThread().getName() + "执行任务sleep前-"+i);
                    TimeUnit.SECONDS.sleep(ThreadLocalRandom.current().nextInt(20));
                    System.out.println(Thread.currentThread().getName() + "执行任务sleep后-"+i);
                    return i;
                }
        ).collect(Collectors.toList());

        Integer value = executorService.invokeAny(callableList);
        System.out.println("============over============");
        System.out.println(value);
    }

    /**
     * {@link ExecutorService#invokeAny(Collection, long, TimeUnit)} 这个方法是blocked的，是同步方法
     *
     * */
    private static void testInvokeAnyTimeout() throws ExecutionException, InterruptedException, TimeoutException {
        ExecutorService executorService = Executors.newFixedThreadPool(10);

        final List<Callable<Integer>> callableList = IntStream.range(0, 5).boxed().map(
                i -> (Callable<Integer>) () -> {
                    System.out.println(Thread.currentThread().getName() + "执行任务sleep前-"+i);
                    TimeUnit.SECONDS.sleep(20);
                    System.out.println(Thread.currentThread().getName() + "执行任务sleep后-"+i);
                    return i;
                }
        ).collect(Collectors.toList());

        Integer value = executorService.invokeAny(callableList,1,TimeUnit.SECONDS);
        System.out.println("============over============");
        System.out.println(value);
    }

    /**
     * {@link ExecutorService#invokeAll(Collection)} 这个方法是blocked的，是同步方法
     *
     * */
    private static void testInvokeAll() throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(10);

        final List<Callable<Integer>> callableList = IntStream.range(0, 5).boxed().map(
                i -> (Callable<Integer>) () -> {
                    System.out.println(Thread.currentThread().getName() + "执行任务sleep前-"+i);
                    TimeUnit.SECONDS.sleep(5);
                    System.out.println(Thread.currentThread().getName() + "执行任务sleep后-"+i);
                    return i;
                }
        ).collect(Collectors.toList());

        final List<Future<Integer>> futures = executorService.invokeAll(callableList);
        System.out.println("============over============");
        futures.forEach(future -> {
            try {
                System.out.println(future.get());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });

    }

    /**
     * {@link ExecutorService#invokeAll(Collection, long, TimeUnit)}  这个方法是blocked的，是同步方法
     *  RxJava
     * */
    private static void testInvokeAllTimeOut() throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(10);

        final List<Callable<Integer>> callableList = IntStream.range(0, 5).boxed().map(
                i -> (Callable<Integer>) () -> {
                    System.out.println(Thread.currentThread().getName() + "执行任务sleep前-"+i);
                    TimeUnit.SECONDS.sleep(5);
                    System.out.println(Thread.currentThread().getName() + "执行任务sleep后-"+i);
                    return i;
                }
        ).collect(Collectors.toList());

        final List<Future<Integer>> futures = executorService.invokeAll(callableList,2,TimeUnit.SECONDS);
        System.out.println("============over============");
        futures.forEach(future -> {
            try {
                System.out.println(future.get());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });

    }

    /**
     * {@link ExecutorService#submit(Runnable)}
     *
     * */
    private static void testSubmitRunnable() {

        ExecutorService executorService = Executors.newFixedThreadPool(10);
        final Future<?> future = executorService.submit(() -> {
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        try {
            System.out.println("返回值:" + future.get()); // 返回值:null
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

    }

    /**
     * {@link ExecutorService#submit(Runnable, Object)}
     * 这个返回值太不痛不痒了，如果需要返回值 使用 {@link ExecutorService#submit(Runnable)}
     *
     * */
    private static void testSubmitRunnableWithResult() {

        ExecutorService executorService = Executors.newFixedThreadPool(10);
        String result = "DONE";
        final Future<String> future = executorService.submit(() -> {
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },result);

        try {
            System.out.println("返回值:" + future.get()); // 返回值:null
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) throws ExecutionException, InterruptedException, TimeoutException {

//        testInvokeAny();
//        testInvokeAnyTimeout();
//        testInvokeAll();
//        testInvokeAllTimeOut();
//        testSubmitRunnable();
        testSubmitRunnableWithResult();
    }
}
