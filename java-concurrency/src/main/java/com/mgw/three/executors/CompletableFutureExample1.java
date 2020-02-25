package com.mgw.three.executors;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class CompletableFutureExample1 {

    /**
     * Execute中返回结果Future的问题
     * */
    private static void test1() throws InterruptedException, ExecutionException {

        final ExecutorService executorService = Executors.newFixedThreadPool(5);

        final List<Runnable> tasks = IntStream.range(0, 5).boxed().map(i -> {
            return (Runnable) toTask(i);
        }).collect(Collectors.toList());

        List<Future<?>> futureList = new ArrayList<>();
        tasks.forEach(task -> {
            final Future<?> future = executorService.submit(task);
            futureList.add(future);
        });

        // 问题 ： 最快的可能已经执行完毕了，但是却要去等待没有执行完毕的任务
        System.out.println(futureList.get(4).get());
        System.out.println(futureList.get(3).get());
        System.out.println(futureList.get(2).get());
        System.out.println(futureList.get(1).get());
        System.out.println(futureList.get(0).get());

        System.out.println("=======================分割线=======================");

        // 解决方法 使用CompletionService来执行任务
        final ExecutorCompletionService<Object> service = new ExecutorCompletionService(executorService);
        tasks.forEach(task -> {
            service.submit(Executors.callable(task));
        });
        Future<?> future = null;
        while ((future = service.take()) != null) {
            System.out.println(future.get());
        }

    }

    private static Runnable toTask(int i) {
        return ()-> {
            try {
                System.out.println("执行一个任务 编号: " + i + "开始");
                TimeUnit.SECONDS.sleep(i * 5 + 10);
                System.out.println("执行一个任务 编号: " + i + "完毕");
            } catch (InterruptedException e) {
                System.out.println("执行一个任务 编号: " + i + "被打断");
            }

        };
    }

    /**
     * CompletionService的一些问题: 只关心已经完成的任务，如果正在执行的过程中任务被shutdownNow打断，是会miss的
     * 还有就是shutdownNow后拿出的队列中的未执行的任务已经不是原始任务了，而是java.util.concurrent.ExecutorCompletionService$QueueingFuture@1d81eb93这种了
     *
     * */
    private static void test2() throws InterruptedException, ExecutionException {

        final ExecutorService executorService = Executors.newSingleThreadExecutor();
        final ExecutorCompletionService<Object> service = new ExecutorCompletionService(executorService);
        final List<Runnable> tasks = IntStream.range(0, 5).boxed().map(i -> {
            return (Runnable) toTask(i);
        }).collect(Collectors.toList());

        tasks.forEach(task -> {
            service.submit(Executors.callable(task));
        });
        TimeUnit.SECONDS.sleep(20);
        final List<Runnable> runnables = executorService.shutdownNow();
        System.out.println(runnables);

    }

    private static class MyTask implements Callable<Integer> {

        private final Integer value;

        private boolean success = false;

        private MyTask(Integer value) {
            this.value = value;
        }

        @Override
        public Integer call() throws Exception {

            try {
                System.out.println("执行一个任务 编号: " + value + "开始");
                TimeUnit.SECONDS.sleep(value * 5 + 10);
                System.out.println("执行一个任务 编号: " + value + "完毕");
                success = true;
            } catch (InterruptedException e) {
                System.out.println("执行一个任务 编号: " + value + "被打断");
            }
            return value;
        }

        public boolean isSuccess() {
            return success;
        }
    }

    /**
     * 解决test2的问题
     * 自定义一个任务类，在任务类中定义标识
     * */
    private static void test3() throws InterruptedException, ExecutionException {

        final ExecutorService executorService = Executors.newSingleThreadExecutor();
        final ExecutorCompletionService<Integer> service = new ExecutorCompletionService(executorService);
        final List<Callable<Integer>> tasks = IntStream.range(0, 5).boxed().map(i -> {
            return new MyTask(i);
        }).collect(Collectors.toList());

        tasks.forEach(task -> {
            service.submit(task);
        });
        TimeUnit.SECONDS.sleep(12);
        executorService.shutdownNow();
//        System.out.println(runnables);
        tasks.stream().filter(callable -> !((MyTask)callable).isSuccess()).forEach(System.out :: println);

    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {

//        test1();
//        test2();
        test3();
    }


}
