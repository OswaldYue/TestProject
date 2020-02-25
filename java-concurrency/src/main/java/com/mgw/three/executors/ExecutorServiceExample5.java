package com.mgw.three.executors;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class ExecutorServiceExample5 {



    /**
     * {@link ThreadPoolExecutor#getQueue()}
     * 不能在线程池没有执行线程时使用getQueue直接加入到queue中(因为提交任务或者执行任务时，有创建线程的信号信息)
     * 但是可以在有执行线程的情况下这么做
     * */
    public static void main(String[] args) {

        ThreadPoolExecutor executorService = (ThreadPoolExecutor)Executors.newFixedThreadPool(5);
        executorService.execute(() -> {
            System.out.println("执行一个任务");
        });
        executorService.getQueue().add(() -> {
            System.out.println("直接加入到queue中");
        });
    }
}
