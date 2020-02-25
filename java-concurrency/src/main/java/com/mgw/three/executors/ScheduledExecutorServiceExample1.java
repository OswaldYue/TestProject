package com.mgw.three.executors;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicLong;

public class ScheduledExecutorServiceExample1 {


    /**
     * {@link ScheduledExecutorService#schedule(java.lang.Runnable, long, java.util.concurrent.TimeUnit)}
     * 问题:执行的是一个runnable任务，而runnable任务不返回值的？为什么还要返回一个Future呢?因为它允许你去cancle这个任务,cancle之后这个延时任务就不会被执行
     *
     * */
    private static void testScheduleRunnable() {
        ScheduledThreadPoolExecutor executor = new ScheduledThreadPoolExecutor(2);

        final ScheduledFuture<?> future = executor.schedule(() -> {
            System.out.println("执行一个延时任务");
        }, 2, TimeUnit.SECONDS);

        System.out.println(future.cancel(true));
    }

    /**
     * {@link ScheduledExecutorService#submit(Callable)}
     * */
    private static void testScheduleCallable() throws ExecutionException, InterruptedException {
        ScheduledThreadPoolExecutor executor = new ScheduledThreadPoolExecutor(2);

        final ScheduledFuture<?> future = executor.schedule(() -> {
            System.out.println("执行一个延时任务");
            return 2;
        }, 2, TimeUnit.SECONDS);

        System.out.println(future.get());
    }

    /**
     * {@link ScheduledExecutorService#scheduleAtFixedRate(Runnable, long, long, TimeUnit)}
     * 一样返回一个future
     * 问题:假设每隔2秒执行一个任务，但是执行的任务需要5秒，那么它是依然每隔2秒执行一次。还是等5秒任务执行完毕后，再去重复执行任务？
     * 回答: 是等5秒任务执行完毕后，再去重复执行任务这种方式
     * */
    private static void testscheduleAtFixedRate() throws ExecutionException, InterruptedException {
        ScheduledThreadPoolExecutor executor = new ScheduledThreadPoolExecutor(2);

        final AtomicLong interval = new AtomicLong(0L);
        final ScheduledFuture<?> future = executor.scheduleAtFixedRate(() -> {
            System.out.println("执行一个定时任务");
            System.out.println("当前的线程: " + Thread.currentThread().getName());
            long currentTimeMillis = System.currentTimeMillis();
            if (interval.get() == 0) {
                System.out.printf("The first time trigger task at %d\n",currentTimeMillis);
            }else {
                System.out.printf("The actually spend [%d]\n",currentTimeMillis - interval.get());
            }

            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            interval.set(currentTimeMillis);

        }, 1,2, TimeUnit.SECONDS);


    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {

//        testScheduleRunnable();
//        testScheduleCallable();
        testscheduleAtFixedRate();
    }

}
