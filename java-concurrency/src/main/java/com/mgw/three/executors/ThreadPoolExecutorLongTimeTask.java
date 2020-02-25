package com.mgw.three.executors;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

public class ThreadPoolExecutorLongTimeTask {

    public static void main(String[] args) {

        ExecutorService executorService = new ThreadPoolExecutor(10,20,30, TimeUnit.SECONDS,
                new ArrayBlockingQueue<Runnable>(10),
                r->{
                    Thread thread = new Thread(r);
                    // 设置成守护线程 当长时间任务无法结束时 会随着主线程死亡而死亡
                    thread.setDaemon(true);
                    return thread;
                },
                new ThreadPoolExecutor.AbortPolicy());
        System.out.println("The ThreadPoolExecutor create done");

        // 期待并行处理
        IntStream.range(0,10).boxed().forEach(i -> {
            executorService.submit(() -> {
                // 长时间的任务
                while (true) {

                }
            });
        });

        // 期待串行处理
        executorService.shutdown();
        try {
            executorService.awaitTermination(5,TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("==========开始串行化工作==========");

    }
}
