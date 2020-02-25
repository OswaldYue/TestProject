package com.mgw.one.chapter12;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class SimpleThreadPoolVersion3 extends Thread{

    private  int size ;

    private final int queueSize;

    // 默认线程数为10个
//    private final static int DEFAULT_SIZE = 10;

    private final static int DEFAULT_MIN_SIZE = 4;

    private final static int DEFAULT_ACTIVE_SIZE = 8;

    private final static int DEFAULT_MAX_SIZE = 12;

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

    private int min;

    private int max;

    private int active;

    public SimpleThreadPoolVersion3() {
        this(DEFAULT_MIN_SIZE,DEFAULT_ACTIVE_SIZE,DEFAULT_MAX_SIZE,DEFAULT_TASK_QUEUE_SIZE,DEFAULT_DISCARD_POLICY);
    }

    public SimpleThreadPoolVersion3(int min,int active,int max,int queueSize, DiscardPolicy discardPolicy) {
        this.min = min;
        this.active = active;
        this.max = max;
        this.queueSize = queueSize;
        this.discardPolicy = discardPolicy;

        init();
    }

    private void init() {

        for (int i = 0 ; i < this.min ; i++) {
            createWorkTask();
        }

        this.size = min;
        this.start();
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
        synchronized (THREAD_QUEUE) {
            int initVal = THREAD_QUEUE.size();
            while (initVal > 0) {
                for (WorkTask workTask : THREAD_QUEUE) {
                    if (workTask.getTaskState() == TaskState.BLOCKED) {
                        // 打断
                        workTask.interrupt();
                        workTask.close();
                        initVal--;
                    } else {

                        Thread.sleep(10);
                    }
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

    public int getMin() {
        return min;
    }

    public int getMax() {
        return max;
    }

    public int getActive() {
        return active;
    }

    @Override
    public void run() {

        while (!this.destroy) {
            System.out.printf("Pool#Min : %d,Active : %d,Max : %d,Current Size : %d,TaskQueueSize : %d\n",
                    this.min,this.active,this.max,this.size,TASK_QUEUE.size());
            try {
                Thread.sleep(5_000);

                if (TASK_QUEUE.size() > this.active && this.size < this.active) {
                    for (int i = this.size ; i < this.active ; i++) {
                        //扩充线程
                        createWorkTask();
                    }
                    System.out.printf("The pool incremented to active : %d \n",this.active);
                    this.size = active;
                }else if (TASK_QUEUE.size() > this.max && this.size < this.max) {
                    for (int i = this.size ; i < this.max ; i++) {
                        //扩充线程
                        createWorkTask();
                    }
                    System.out.printf("The pool incremented to max : %d \n",this.max);
                    this.size = max;
                }
                //闲时回收
                synchronized (THREAD_QUEUE) {
                    if (TASK_QUEUE.isEmpty() && this.size > this.active) {
                        System.out.printf("===================Reduce to active : %d===================\n",this.active);
                        // 关闭已经完成任务的线程 若线程有任务正在执行 则不许关闭
                        int releaseSize = this.size - this.active;
                        while (releaseSize > 0) {
                            for (Iterator<WorkTask> it = THREAD_QUEUE.iterator();it.hasNext();) {
                                if (releaseSize <= 0) {
                                    break;
                                }
                                WorkTask task = it.next();
                                if (task.taskState == TaskState.BLOCKED || task.taskState == TaskState.FREE) {

                                    task.close();
                                    task.interrupt();
                                    it.remove();
                                    releaseSize--;
                                }else {
                                    Thread.sleep(10);
                                }
                            }
                        }

                        this.size = this.active;
                    }
                }

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
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
                            System.out.printf("Closed thread : %s.\n",Thread.currentThread().getName() );
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

        SimpleThreadPoolVersion3 threadPool = new SimpleThreadPoolVersion3();


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


        Thread.sleep(20_000);
        threadPool.shutdown();
//
//        threadPool.submit(() -> {
//            System.out.println("------------------------");
//        });


    }

}
