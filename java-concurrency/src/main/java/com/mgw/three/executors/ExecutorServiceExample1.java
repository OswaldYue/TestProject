package com.mgw.three.executors;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

/**
 * {@link ExecutorService}
 * */
public class ExecutorServiceExample1 {


    /**
     * {@link ExecutorService#isShutdown()}
     * */
    private static void isShutDown() {

        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.execute(() -> {

            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        System.out.println(executorService.isShutdown());
        executorService.shutdown();
        System.out.println(executorService.isShutdown());
        // 当shutdown后，如果再提交任务会怎么样？会被拒绝 java.util.concurrent.RejectedExecutionException
        executorService.execute(() -> {

            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
    }
    /**
     * {@link ExecutorService#isTerminated()}
     * {@link ThreadPoolExecutor#isTerminated()}
     * */
    private static void isTerminated() {
        ExecutorService executorService = Executors.newFixedThreadPool(1);
        executorService.execute(() -> {
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            executorService.shutdown();
            // true
            System.out.println(executorService.isShutdown());
            // false
            System.out.println(executorService.isTerminated());
            // true  是否在结束中
            System.out.println(((ThreadPoolExecutor)executorService).isTerminating());
        });

    }


    private static void execteRunnableError() throws InterruptedException {
        // MyThreadFactory 异常回调
        ExecutorService executorService = Executors.newFixedThreadPool(10,new MyThreadFactory());
        IntStream.range(0,10).boxed().forEach(i -> {
            executorService.execute(() -> {

                System.out.println(1 / 0);
            });
        });

        executorService.shutdown();
        executorService.awaitTermination(10,TimeUnit.MINUTES);
        System.out.println("========================");
    }

    /**
     * send请求-> 插入DB-> 轮询一遍，每次拿10个，并发的去处理它
     * */
    private static void execteRunnableTask() throws InterruptedException {

        ExecutorService executorService = Executors.newFixedThreadPool(10,new MyThreadFactory());
        IntStream.range(0,10).boxed().forEach(i -> {
            executorService.execute(
                    new MyTask(i) {
                        @Override
                        protected void error(Throwable cause) {
                            System.out.println("The no :" + i +" failed,update status to ERROR");
                        }

                        @Override
                        protected void done() {
                            System.out.println("The no :" + i +" successfully,update status to DONE");
                        }

                        @Override
                        protected void doInit() {
                            // do nothing
                        }

                        @Override
                        protected void doExecute() {
                            if (i % 3 == 0) {
                                    int temp = i/0;
                                }
                        }
                    });
            });
        executorService.shutdown();
        executorService.awaitTermination(10,TimeUnit.MINUTES);
        System.out.println("============over===========");
    }

    private abstract static class MyTask implements Runnable {

        protected final int no;


        private MyTask(int no) {
            this.no = no;
        }

        @Override
        public void run() {
            try {
                this.doInit();
                this.doExecute();
                this.done();
            }catch (Throwable cause) {
                this.error(cause);
            }
        }

        protected abstract void error(Throwable cause);

        protected abstract void done();

        protected abstract void doInit();

        protected abstract void doExecute();
    }

    private static class MyThreadFactory implements ThreadFactory {

        private final static AtomicInteger SEQ = new AtomicInteger();


        @Override
        public Thread newThread(Runnable r) {
            Thread thread = new Thread(r);
            thread.setName("My-Thread-"+SEQ.get());
            thread.setUncaughtExceptionHandler((t,cause) -> {
                System.out.println("The thread " + t.getName() + " execute failed");
            });
            return thread;
        }
    }

    public static void main(String[] args) throws InterruptedException {
//        isShutDown();
//        isTerminated();
//        execteRunnableError();
        execteRunnableTask();
    }


}
