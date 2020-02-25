package com.mgw.three.executors;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadPoolExecutorBuild {

    /**
     * 测试点：
     *  1.corePoolSize=1，maximumPoolSize=2，workQueue=1时，提交3个任务会怎么样？
     *  2.corePoolSize=1，maximumPoolSize=2，workQueue=5时，提交7个任务会怎么样？
     *  3.corePoolSize=1，maximumPoolSize=2，workQueue=5时，提交8个任务会怎么样？
     *
     * int corePoolSize,
     * int maximumPoolSize,当queue满了时，才去启动额外的线程数
     * long keepAliveTime,当启用了最大线程数后，如果最大线程没有任务执行多久后回收
     *  -when the number of threads is greater than the core, this is the maximum time that excess idle threads will wait for new tasks before terminating
     * TimeUnit unit,
     * BlockingQueue<Runnable> workQueue,
     * ThreadFactory threadFactory,
     * RejectedExecutionHandler handler 执行拒绝策略的时机:the thread bounds and queue capacities are reached 达到最大线程数和queue满了
     * */
    private static ExecutorService buildThreadPoolExecutor() {

        ExecutorService executorService = new ThreadPoolExecutor(1,2,30, TimeUnit.SECONDS,
                new ArrayBlockingQueue<Runnable>(1),
                r->{
                    return new Thread(r);
                },
                new ThreadPoolExecutor.AbortPolicy());
        System.out.println("The ThreadPoolExecutor create done");

        // 提交线程
        executorService.execute(() -> sleepSeconds(100));
        executorService.execute(() -> sleepSeconds(10));
        executorService.execute(() -> sleepSeconds(10));


        return executorService;
    }

    private static void sleepSeconds(long seconds) {
        try {
            System.out.println("* " + Thread.currentThread().getName() + " *");
            TimeUnit.SECONDS.sleep(seconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {

        ThreadPoolExecutor threadPoolExecutor = (ThreadPoolExecutor)buildThreadPoolExecutor();

        int activeCount = -1;
        int queueSize = -1;


        while (true) {
            if (activeCount != threadPoolExecutor.getActiveCount() ||
            queueSize != threadPoolExecutor.getQueue().size()) {
                // 有多少个活跃的个数
                System.out.println(threadPoolExecutor.getActiveCount());
                // 有多少个核心的线程数
                System.out.println(threadPoolExecutor.getCorePoolSize());

                System.out.println(threadPoolExecutor.getQueue().size());
                // 有多少个最大线程数
                System.out.println(threadPoolExecutor.getMaximumPoolSize());

                activeCount = threadPoolExecutor.getActiveCount();
                queueSize = threadPoolExecutor.getQueue().size();
                System.out.println("=========================================");

            }
        }
    }
}
