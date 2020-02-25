package com.mgw.three.executors;

import java.util.concurrent.*;

public class FutureExample1 {

    /**
     * {@link Future#get()}
     * Waits if necessary for the computation to complete, and then retrieves its result.
     * get()方法会陷入阻塞,可以被打断，那个线程调用它，就由那个线程去打断，打断的过程中不影响线程池中正在执行返回此Future的线程的继续执行
     *
     * */
    public static void testGet() throws ExecutionException, InterruptedException {
        ThreadPoolExecutor threadPoolExecutor = (ThreadPoolExecutor) Executors.newCachedThreadPool();
        Future<Integer> future = threadPoolExecutor.submit(() -> {
            try {
                System.out.println("执行一个任务");
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return 10;
        });
        // =============================================
        System.out.println("做其他的事情");
        // =============================================
        Thread currentThread = Thread.currentThread();
        // 另起一个线程进行打断future.get()
        new Thread(()-> {
            try {
                TimeUnit.MILLISECONDS.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            currentThread.interrupt();
        }).start();

        System.out.println(future.get());
        System.out.println("future.get() 之后");
    }

    /**
     * {@link Future#get(long, TimeUnit)}
     * Waits if necessary for the computation to complete, and then retrieves its result.
     * 如果等待超时了，那么就会抛出TimeOutException,但是不影响线程池中正在执行返回此Future的线程的继续执行
     *
     * */
    public static void testGetWithTimeOut() throws ExecutionException, InterruptedException, TimeoutException {
        ThreadPoolExecutor threadPoolExecutor = (ThreadPoolExecutor) Executors.newCachedThreadPool();
        Future<Integer> future = threadPoolExecutor.submit(() -> {
            try {
                System.out.println("执行一个任务");
                TimeUnit.SECONDS.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return 10;
        });

        System.out.println(future.get(5, TimeUnit.SECONDS));
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException, TimeoutException {

//        testGet();
        testGetWithTimeOut();
    }

}
