package com.mgw.one.chapter12;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class SimpleThreadPool {

    private final int size ;

    private final static int DEFAULT_SIZE = 10;

    private static volatile int seq = 0;

    private final static String THREAD_PREFIX = "SIMPLE_THREAD_POOL-";

    private final static ThreadGroup GROUP = new ThreadGroup("Pool_Group");

    private final static LinkedList<Runnable> TASK_QUEUE = new LinkedList<>();

    private final static List<WorkTask> THREAD_QUEUE = new ArrayList<>();

    public SimpleThreadPool() {
        this(DEFAULT_SIZE);
    }

    public SimpleThreadPool(int size) {
        this.size = size;
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


    public void submit(Runnable runnable) {

        synchronized (TASK_QUEUE) {
            TASK_QUEUE.addLast(runnable);
            TASK_QUEUE.notifyAll();
        }
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
//                    if (runnable != null) {
//                        //执行任务
//                        this.taskState = TaskState.RUNBING;
//                        runnable.run();
//                        this.taskState = TaskState.FREE;
//                    }
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


    public static void main(String[] args) {

        SimpleThreadPool threadPool = new SimpleThreadPool();

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
            System.out.println("==================================");
        }

    }

}
