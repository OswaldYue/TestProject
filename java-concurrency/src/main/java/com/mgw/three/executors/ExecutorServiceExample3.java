package com.mgw.three.executors;


import java.util.concurrent.*;
import java.util.stream.IntStream;

public class ExecutorServiceExample3 {

    public static void test1() throws InterruptedException {

        ThreadPoolExecutor threadPoolExecutor = (ThreadPoolExecutor)Executors.newFixedThreadPool(5);
        // 0
        System.out.println(threadPoolExecutor.getActiveCount());

        threadPoolExecutor.execute(() -> {
            try {
                TimeUnit.SECONDS.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        TimeUnit.SECONDS.sleep(1);
        // 1
        System.out.println(threadPoolExecutor.getActiveCount());

    }

    /**
     * {@link ThreadPoolExecutor#allowCoreThreadTimeOut(boolean)}
     * */
    public static void testAllowsCoreThreadTimeOut() throws InterruptedException {

        ThreadPoolExecutor threadPoolExecutor = (ThreadPoolExecutor)Executors.newFixedThreadPool(5);

        threadPoolExecutor.setKeepAliveTime(10,TimeUnit.SECONDS);
        // 设置核心线程数允许被回收
        threadPoolExecutor.allowCoreThreadTimeOut(true);

        IntStream.range(0,5).boxed().forEach(i -> {
            threadPoolExecutor.execute(() -> {
                try {
                    TimeUnit.SECONDS.sleep(3);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        });

        System.out.println(threadPoolExecutor.getActiveCount());
    }

    /**
     * {@link ThreadPoolExecutor#remove(Runnable)}
     * remove方法：
     *  删除一个queue中的任务，当此任务如果还没被执行，则可以删除，否则不可以
     * */
    public static void testRemove() throws InterruptedException {

        ThreadPoolExecutor threadPoolExecutor = (ThreadPoolExecutor)Executors.newFixedThreadPool(2);

        threadPoolExecutor.setKeepAliveTime(10,TimeUnit.SECONDS);
        // 设置核心线程数允许被回收
        threadPoolExecutor.allowCoreThreadTimeOut(true);

        IntStream.range(0,2).boxed().forEach(i -> {
            threadPoolExecutor.execute(() -> {
                try {
                    TimeUnit.SECONDS.sleep(5);
                    System.out.println("I am finisd");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        });

        TimeUnit.SECONDS.sleep(1);
        Runnable runnable = () -> {
            System.out.println("I will never be executed");
        };
        threadPoolExecutor.execute(runnable);
        TimeUnit.MILLISECONDS.sleep(20);
        threadPoolExecutor.remove(runnable);

    }

    /**
     * {@link ThreadPoolExecutor#prestartAllCoreThreads()}
     * prestartAllCoreThreads方法 ： Starts all core threads, causing them to idly wait for work.
     *
     * 返回值(个数) ： the number of threads started
     * */
    public static void testPrestartAllCoreThreads() throws InterruptedException {

        ThreadPoolExecutor threadPoolExecutor = (ThreadPoolExecutor)Executors.newFixedThreadPool(2);

        threadPoolExecutor.setCorePoolSize(3);
        System.out.println(threadPoolExecutor.getActiveCount()); // 0

        System.out.println(threadPoolExecutor.prestartAllCoreThreads()); // 3
        System.out.println(threadPoolExecutor.getActiveCount()); // 3
    }
    /**
     * {@link ThreadPoolExecutor#prestartCoreThread()}
     * prestartCoreThread方法：Starts a core thread, causing it to idly wait for work.
     *  返回值（boolean）: if a thread was started
     * */
    public static void testPrestartCoreThread() throws InterruptedException {

        ThreadPoolExecutor threadPoolExecutor = (ThreadPoolExecutor)Executors.newFixedThreadPool(2);

        System.out.println(threadPoolExecutor.getActiveCount());// 0

        threadPoolExecutor.prestartCoreThread();
        System.out.println(threadPoolExecutor.getActiveCount());// 1

        threadPoolExecutor.prestartCoreThread();
        System.out.println(threadPoolExecutor.getActiveCount());// 2

        threadPoolExecutor.prestartCoreThread();
        System.out.println(threadPoolExecutor.getActiveCount());// 2 总共核心线程是2个  故此到顶了

    }

    /**
     * {@link ThreadPoolExecutor#prestartCoreThread()}
     * prestartCoreThread方法：Starts a core thread, causing it to idly wait for work.
     *  返回值（boolean）: if a thread was started
     * 这个方法有坑，貌似刚起来时的线程也可以算是活跃的线程
     * */
    public static void testPrestartCoreThread2() throws InterruptedException {

        ThreadPoolExecutor threadPoolExecutor = (ThreadPoolExecutor)Executors.newFixedThreadPool(2);

        System.out.println(threadPoolExecutor.getActiveCount());// 0

        threadPoolExecutor.prestartCoreThread();
        TimeUnit.SECONDS.sleep(1);
        System.out.println(threadPoolExecutor.getActiveCount());// 0

        threadPoolExecutor.prestartCoreThread();
        TimeUnit.SECONDS.sleep(1);
        System.out.println(threadPoolExecutor.getActiveCount());// 0

        IntStream.range(0,2).boxed().forEach(i -> {
            try {
                TimeUnit.MILLISECONDS.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("执行任务-"+i);
        });

        threadPoolExecutor.prestartCoreThread();
        TimeUnit.SECONDS.sleep(1);
        System.out.println(threadPoolExecutor.getActiveCount());// 0

    }

    /**
     * beforeExecute与afterExecute方法就像切面似的
     * */
    private static class MyThreadPoolExecutor extends ThreadPoolExecutor {

        public MyThreadPoolExecutor(int corePoolSize,
                                    int maximumPoolSize,
                                    long keepAliveTime,
                                    TimeUnit unit,
                                    BlockingQueue<Runnable> workQueue,
                                    ThreadFactory threadFactory,
                                    RejectedExecutionHandler handler) {
            super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, threadFactory, handler);
        }

        @Override
        protected void beforeExecute(Thread t, Runnable r) {
            // 初始化工作
            System.out.println("init the " + ((MyRunnable)r).getData());
        }

        @Override
        protected void afterExecute(Runnable r, Throwable t) {
            if (null == t) {
                System.out.println("successful " + ((MyRunnable)r).getData());
            }else {
                t.printStackTrace();
            }
        }
    }

    private abstract static class MyRunnable implements Runnable {

        private final int no;

        private MyRunnable(int no) {
            this.no = no;
        }

        protected int getData() {
            return this.no;
        }

    }

    public static void testThreadPoolAdvice() throws InterruptedException {
        ExecutorService executorService = new MyThreadPoolExecutor(
                1,
                2,
                30,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(1),
                r -> {
                    return  new Thread(r);
                },
                new ThreadPoolExecutor.AbortPolicy());

        // 执行成功的方法
        executorService.execute(new MyRunnable(1) {
            @Override
            public void run() {
                System.out.println("执行任务");
            }
        });

        // 执行失败的方法
        executorService.execute(new MyRunnable(2) {
            @Override
            public void run() {
                int temp = 10/0;
            }
        });
    }

    public static void main(String[] args) throws InterruptedException {

//        test1();
//        testAllowsCoreThreadTimeOut();
//        testRemove();
//        testPrestartCoreThread();
//        testPrestartCoreThread2();
//        testPrestartAllCoreThreads();
        testThreadPoolAdvice();
    }

}
