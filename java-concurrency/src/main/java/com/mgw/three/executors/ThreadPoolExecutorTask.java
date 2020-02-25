package com.mgw.three.executors;

import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

public class ThreadPoolExecutorTask {

    private static void test1()  {

        ExecutorService executorService = new ThreadPoolExecutor(10,20,30, TimeUnit.SECONDS,
                new ArrayBlockingQueue<Runnable>(10),
                r->{
                    return new Thread(r);
                },
                new ThreadPoolExecutor.AbortPolicy());
        System.out.println("The ThreadPoolExecutor create done");

        IntStream.range(0,20).boxed().forEach(i->{
            executorService.execute(() -> {
                try {
                    TimeUnit.SECONDS.sleep(20);
                    System.out.println(Thread.currentThread().getName() + " [" + i + "] finish done");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        });

        /*
        * shutdown说明:
        * 假设20个Thread
        *   10个线程work
        *   10个线程idle
        * shutdown后
        *   1.10个work线程依旧去完成工作
        *   2.打断10个idle线程
        *   3.所有线程退出
        *
        *
        * */
//        executorService.shutdown();

        /*
        * shutdownNow说明:
        * 假设20个Thread
        *   10个核心线程
        *   队列queue10个
        *   此时10个running
        *   10个在queue中等待
        * shutdownNow后
        *   返回queue中未处理完的线程
        *   依然处理完前面没有完成的10个线程
        * */
        List<Runnable> runnables = null;
        try {
            runnables = executorService.shutdownNow();

        }catch (Exception e) {

        }
        System.out.println(runnables.size());
//        try {
//            // awaitTermination方法则会阻塞等待一定的时长或者线程池完成任务退出
//            executorService.awaitTermination(1,TimeUnit.HOURS);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        System.out.println("==============over==============");

        // other work

    }


    public static void main(String[] args) {

        test1();
    }
}
