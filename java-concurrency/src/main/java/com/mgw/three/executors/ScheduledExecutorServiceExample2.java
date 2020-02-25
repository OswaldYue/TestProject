package com.mgw.three.executors;

import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

public class ScheduledExecutorServiceExample2 {


    /**
     * {@link ScheduledExecutorService#scheduleWithFixedDelay(java.lang.Runnable, long, long, java.util.concurrent.TimeUnit)}
     * 它的执行周期时间其实是任务的执行时间 + 固定延时的时间 例如下面的测试代码中的周期时间是: 3 + 2 = 5秒
     *
     * */
    private static void testScheduleWithFixedDelay() {

        ScheduledThreadPoolExecutor executor = new ScheduledThreadPoolExecutor(2);
        final ScheduledFuture<?> future = executor.scheduleWithFixedDelay(() -> {
            System.out.println("执行一个循环任务,时间戳 ： " + System.currentTimeMillis());
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, 1, 2, TimeUnit.SECONDS);
    }

    /**
     * {@link ScheduledThreadPoolExecutor#setContinueExistingPeriodicTasksAfterShutdownPolicy(boolean)}
     * Sets the policy on whether to continue executing existing
     * periodic tasks even when this executor has been {@code shutdown}.
     * 如果设置为true，则就算是shutdown了，那么定时任务也会继续定时执行，所以源码中默认给的是false
     * */
    private static void test1() {

        ScheduledThreadPoolExecutor executor = new ScheduledThreadPoolExecutor(2);
        System.out.println(executor.getContinueExistingPeriodicTasksAfterShutdownPolicy());
        executor.setContinueExistingPeriodicTasksAfterShutdownPolicy(true);
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
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        executor.shutdown();
        System.out.println("shutdown掉");
    }

    /**
     * {@link ScheduledThreadPoolExecutor#setExecuteExistingDelayedTasksAfterShutdownPolicy(boolean)}
     * 在shundown时，如果在执行任务就会将任务执行完毕,如果还没开始任务那就不再执行
     * 设置为true或者false 测试发现没什么区别
     * */
    private static void test2() {

        ScheduledThreadPoolExecutor executor = new ScheduledThreadPoolExecutor(2);
        System.out.println(executor.getExecuteExistingDelayedTasksAfterShutdownPolicy());
        executor.setExecuteExistingDelayedTasksAfterShutdownPolicy(false);
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
        try {
            // 休眠2秒，然后shutdown，上面的定时任务已经开始执行，故执行完此次任务后结束
            TimeUnit.SECONDS.sleep(2);
            // 休眠1秒，然后shutdown，上面的定时任务还未开始，那就不再开始执行，直接结束
//            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        executor.shutdown();
        System.out.println("shutdown掉");
    }

    public static void main(String[] args) {
//        testScheduleWithFixedDelay();
//        test1();
        test2();
    }
}
