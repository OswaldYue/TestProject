package com.mgw.three.executors;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;

/**
 * Future的缺点
 * 无法进行callback
 * */
public class CompletionServiceExample1 {

    /**
     * 缺点1：get()会阻塞，没有callback
     * */
    private static void futureDefect1() throws ExecutionException, InterruptedException {
        final ExecutorService executorService = Executors.newFixedThreadPool(2);
        final Future<Integer> future = executorService.submit(() -> {
            try {
                System.out.println("执行一个任务");
                TimeUnit.SECONDS.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return 100;
        });

        System.out.println("做其他事情");
        System.out.println(future.get());
    }

    private static void sleepSeconds(long seconds) {
        try {
            TimeUnit.SECONDS.sleep(seconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    /**
     * 批量执行的任务，批量执行完毕后才能拿结果，假如其中一个任务1分钟执行完毕，另一个1小时执行完毕，则需要等1小时后才能拿到结果
     * */
    private static void futureDefect2() throws ExecutionException, InterruptedException {
        final ExecutorService executorService = Executors.newFixedThreadPool(2);

        List<Callable<Integer>> callables = Arrays.asList(() -> {
            sleepSeconds(10);
            System.out.println("the 10 finish");
            return 10;
        },() -> {
            sleepSeconds(20);
            System.out.println("the 20 finish");
            return 20;
        });
        final List<Future<Integer>> futures = executorService.invokeAll(callables);
        final Integer r1 = futures.get(1).get();
        final Integer r0 = futures.get(0).get();
        System.out.println(r1);
        System.out.println(r0);

//        比较笨的解决方法
        List<Future<Integer>> futuresList = new ArrayList<>();
        futuresList.add(executorService.submit(callables.get(0)));
        futuresList.add(executorService.submit(callables.get(1)));
        for (Future<Integer> future : futuresList) {
            System.out.println(future.get());
        }
        

    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
//        futureDefect1();
        futureDefect2();
    }

}
