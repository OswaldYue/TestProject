package com.mgw.three.executors;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

public class ExecutorsExample1 {

    /**
     * These pools will typically improve the performance of programs that execute many short-lived asynchronous tasks.
     * new ThreadPoolExecutor(0, Integer.MAX_VALUE,
     *                                       60L, TimeUnit.SECONDS,
     *                                       new SynchronousQueue<Runnable>());
     * */
    public static void useCachedThreadPool() {

        ExecutorService executorService = Executors.newCachedThreadPool();

        // 0  因为构造时corePoolSize为0
        System.out.println(((ThreadPoolExecutor)executorService).getActiveCount());
        executorService.execute(() -> {
            System.out.println("========");
        });
        // 1 因为上面提交了一个任务到SynchronousQueue中，这个SynchronousQueue每次只装一个任务 满足queue满再去开新线程的条件 所以它开了个新线程
        System.out.println(((ThreadPoolExecutor)executorService).getActiveCount());

        IntStream.range(0,100).boxed().forEach(i -> {
            executorService.execute(() -> {
                try {
                    TimeUnit.SECONDS.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + " [" + i + "]");
            });
        });

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // 100 针对上面提交的100个任务会开100个新线程去执行，执行完毕后会自动结束，因为线程池的corePoolSize为0执行完毕任务后线程回收，所以运行完毕就结束了
        // 所以这个CachedThreadPool线程池印证了注解说的主要是用来执行短存活的异步任务的
        System.out.println(((ThreadPoolExecutor)executorService).getActiveCount());

    }

    /**
     * new ThreadPoolExecutor(nThreads, nThreads,
     *                                       0L, TimeUnit.MILLISECONDS,
     *                                       new LinkedBlockingQueue<Runnable>());
     * 创建的核心线程数与最大线程数相等，所以永远可用的线程数是固定不变的，积存在queue中的任务也只会任务处理完一个再去拿一个这样的方式
     * LinkedBlockingQueue的数量为Integer.MAX_VALUE,因此只有超过了Integer.MAX_VALUE才会去调用拒绝策略
     * 不会自动回收，因为线程数与最大线程数相等
     * */
    public static void useFixedThreadPool() {

        ExecutorService executorService = Executors.newFixedThreadPool(10);
        System.out.println(((ThreadPoolExecutor)executorService).getActiveCount());

        IntStream.range(0,100).boxed().forEach(i -> {
            executorService.execute(() -> {
                try {
                    TimeUnit.SECONDS.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + " [" + i + "]");
            });
        });
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // 10
        System.out.println(((ThreadPoolExecutor)executorService).getActiveCount());
    }

    /**
     * SingleThreadExecutor与一个线程的区别:
     *  1.一个线程执行完毕后生命周期结束掉，而SingleThreadExecutor不会结束掉，可以复用
     *  2.一个线程不能把一些任务存到queue中，而SingleThreadExecutor可以
     *
     * new FinalizableDelegatedExecutorService
     *             (new ThreadPoolExecutor(1, 1,
     *                                     0L, TimeUnit.MILLISECONDS,
     *                                     new LinkedBlockingQueue<Runnable>()));
     *  FinalizableDelegatedExecutorService只是做了一个包装:
     *      A wrapper class that exposes only the ExecutorService methods of an ExecutorService implementation.
     *  目的是只让其使用ExecutorService中的方法，但是ThreadPoolExecutor的方法不许使用
     * */
    public static void useSingleThreadExecutor() {

        ExecutorService executorService = Executors.newSingleThreadExecutor();
        // java.lang.ClassCastException
//        System.out.println(((ThreadPoolExecutor)executorService).getActiveCount());

        IntStream.range(0,100).boxed().forEach(i -> {
            executorService.execute(() -> {
                try {
                    TimeUnit.SECONDS.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + " [" + i + "]");
            });
        });
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {

//        useCachedThreadPool();
//        useFixedThreadPool();
        useSingleThreadExecutor();
    }

}
