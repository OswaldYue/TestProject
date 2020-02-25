package com.mgw.one.chapter12;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class SimpleThreadPoolVersion2 {

    private final int size ;

    private final int queueSize;

    private final static int DEFAULT_SIZE = 10;

    // TASK_QUEUE 默认能放2000个任务
    private final static int DEFAULT_TASK_QUEUE_SIZE = 2000;

    private static volatile int seq = 0;

    private final static String THREAD_PREFIX = "SIMPLE_THREAD_POOL-";

    private final static ThreadGroup GROUP = new ThreadGroup("Pool_Group");

    private final static LinkedList<Runnable> TASK_QUEUE = new LinkedList<>();

    private final static List<WorkTask> THREAD_QUEUE = new ArrayList<>();

    //默认的拒绝策略
    private final static DiscardPolicy DEFAULT_DISCARD_POLICY = () -> {
        throw new DiscardException("");
    };

    private final DiscardPolicy discardPolicy;

    private volatile boolean destroy = false;

    public SimpleThreadPoolVersion2() {
        this(DEFAULT_SIZE,DEFAULT_TASK_QUEUE_SIZE,DEFAULT_DISCARD_POLICY);
    }

    public SimpleThreadPoolVersion2(int size,int queueSize,DiscardPolicy discardPolicy) {
        this.size = size;
        this.queueSize = queueSize;
        this.discardPolicy = discardPolicy;

        init();
    }

    private void init() {

        for (int i = 0 ; i < size ; i++) {
            createWorkTask();
        }
    }

    private void createWorkTask() {

        WorkTask task = new WorkTask(GROUP, THREAD_PREFIX + (seq++));
        task.start();
        THREAD_QUEUE.add(task);
    }

    public int getSize() {
        return size;
    }

    public int getQueueSize() {
        return queueSize;
    }

    public boolean isDestroy() {
        return this.destroy;
    }

    public void shutdown() throws InterruptedException {

        // 等待已经加入到任务队列的任务完成
        while (!TASK_QUEUE.isEmpty()) {

            Thread.sleep(1_000);

        }
        // 等待线程池中的线程执行完毕
        int initVal = THREAD_QUEUE.size();
        while (initVal > 0) {
            for (WorkTask workTask : THREAD_QUEUE) {
                if (workTask.getTaskState() == TaskState.BLOCKED) {
                    // 打断
                    workTask.interrupt();
                    workTask.close();
                    initVal--;
                }else {

                    Thread.sleep(10);
                }
            }
        }

        //标示此线程池已经销毁
        this.destroy = true;
        System.out.println("The thread pool disposed.");
    }

    public void submit(Runnable runnable) {

        if (this.destroy) {
            throw new IllegalStateException("The thread pool already destory and not allow submit task.");
        }

        synchronized (TASK_QUEUE) {

            if (TASK_QUEUE.size() > this.queueSize) {
                this.discardPolicy.discard();
            }

            TASK_QUEUE.addLast(runnable);
            TASK_QUEUE.notifyAll();
        }
    }

    public static class DiscardException extends RuntimeException {

        public DiscardException(String message) {
            super(message);
        }
    }

    //拒绝策略
    public interface DiscardPolicy {

        void discard() throws DiscardException;
    }

    private enum TaskState {

        FREE,RUNBING,BLOCKED,DEAD
    }

    private static class WorkTask extends Thread {

        private volatile TaskState taskState = TaskState.FREE;

        public WorkTask(ThreadGroup group,String name) {

            super(group,name);
        }

        public TaskState getTaskState() {

            return this.taskState;
        }


        @Override
        public void run() {

            OUTER:
            while (this.taskState != TaskState.DEAD) {
                Runnable runnable;
                //取任务
                synchronized (TASK_QUEUE) {

                    while (TASK_QUEUE.isEmpty()) {

                        try {
                            this.taskState = TaskState.BLOCKED;
                            TASK_QUEUE.wait();
                        } catch (InterruptedException e) {
//                            e.printStackTrace();
                            break OUTER;
                        }
                    }

                    runnable = TASK_QUEUE.removeFirst();
//
                }

                if (runnable != null) {
                    //执行任务
                    this.taskState = TaskState.RUNBING;
                    runnable.run();
                    this.taskState = TaskState.FREE;
                }
            }

        }

        public void close() {

            this.taskState = TaskState.DEAD;
        }

    }


    public static void main(String[] args) throws InterruptedException {

        SimpleThreadPoolVersion2 threadPool = new SimpleThreadPoolVersion2();
//        SimpleThreadPoolVersion2 threadPool = new SimpleThreadPoolVersion2(6,10,DEFAULT_DISCARD_POLICY);

        for (int i = 0 ; i < 40 ;i++) {

            threadPool.submit(() -> {
                System.out.println("the task be serviced by " + Thread.currentThread().getName() + " start.");
                try {
                    // do work
                    Thread.sleep(10_000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("the task be serviced by " + Thread.currentThread().getName() + " finished.");
            });

        }


        Thread.sleep(10_000);
        threadPool.shutdown();

        threadPool.submit(() -> {
            System.out.println("------------------------");
        });


    }

}
