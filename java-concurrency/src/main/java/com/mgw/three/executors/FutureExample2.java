package com.mgw.three.executors;

import java.sql.Time;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicBoolean;

public class FutureExample2 {

    /**
     * {@link Future#isDone()}
     * 异步方法，立即返回
     *  Completion may be due to normal termination, an exception, or cancellation -- in all of these cases, this method will return
     *  三种情况叫isDone
     *  1.normal termination正常结束
     *  2.exception异常
     *  3.cancellation取消
     * */
    public static void testDone() throws ExecutionException, InterruptedException {

        ExecutorService executorService = Executors.newCachedThreadPool();
        Future<Integer> future = executorService.submit(() -> {
            try {
                System.out.println("执行一个任务");
                TimeUnit.SECONDS.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return 10;
        });

        System.out.println(future.isDone());
    }

    /**
     * 1.normal termination正常结束
     * */
    public static void testDone1() throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newCachedThreadPool();
        Future<Integer> future = executorService.submit(() -> {
            try {
                System.out.println("执行一个任务");
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return 10;
        });
        System.out.println(future.get()); // 阻塞拿结果，拿到后就正常结束
        System.out.println("is done ? " + future.isDone());
    }

    /**
     * 2.exception异常
     * */
    public static void testDone2() throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newCachedThreadPool();
        Future<Integer> future = executorService.submit(() -> {
            throw new RuntimeException();
        });
        try {
            System.out.println(future.get());
        }catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("is done ? " + future.isDone());
    }

    /**
     * 3.cancellation取消
     * 执行了{@link Future#cancel(boolean)}且返回结果为true
     * */
    public static void testDone3() throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newCachedThreadPool();
        Future<Integer> future = executorService.submit(() -> {
            try {
                System.out.println("执行一个任务");
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return 10;
        });
        System.out.println(future.get()); // 阻塞拿结果，拿到后就正常结束
        System.out.println("is done ? " + future.isDone());
    }

    /**
     * {@link Future#cancel(boolean)}
     * 取消任务
     * Attempts to cancel execution of this task.
     * This attempt will fail if the task has already completed,
     * has already been cancelled,
     * or could not be cancelled for some other reason.
     * 取消任务失败的三个原因:
     * 1.task has already completed任务已经完成
     * 2.has already been cancelled任务已经被取消
     * 3.some other reason其他一些原因
     *
     * If successful, and this task has not started when {@code cancel} is called,
     * this task should never run.  If the task has already started,
     * then the {@code mayInterruptIfRunning} parameter determines
     * whether the thread executing this task should be interrupted in
     * an attempt to stop the task.
     * 如果这个任务还没被执行(比如延时任务)，那么这个任务将不会被执行，
     * 如果这个任务已经开始执行，那么如果你cancle方法给的参数是true，那么会打断这个任务，
     * 至于这个任务会不会被执行，就得看任务的执行代码有没有对InterruptedException做处理了
     * */
    public static void testcancel() throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newCachedThreadPool();
        Future<Integer> future = executorService.submit(() -> {
            System.out.println("执行一个任务");
            return 10;
        });
        System.out.println(future.get());
        // 1.task has already completed任务已经完成
        // false
        System.out.println(future.cancel(true));

    }



    /**
     * 2.has already been cancelled任务已经被取消
     * */
    public static void testcancel2() throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newCachedThreadPool();
        Future<Integer> future = executorService.submit(() -> {
            System.out.println("执行一个任务");
            TimeUnit.SECONDS.sleep(10);
            return 10;
        });
        TimeUnit.MILLISECONDS.sleep(20);
        System.out.println(future.cancel(true));
        System.out.println(future.cancel(true));


    }

    /**
     * 3.some other reason其他一些原因
     * */
    public static void testcancel3() throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newCachedThreadPool();
        AtomicBoolean running = new AtomicBoolean(true);
        Future<Integer> future = executorService.submit(() -> {
            System.out.println("执行一个任务");
            while (running.get()) {

            }
            return 10;
        });
        TimeUnit.MILLISECONDS.sleep(20);
        System.out.println(future.cancel(true));
        System.out.println(future.isDone());
        System.out.println(future.isCancelled());

    }
    /**
     * 测试被cancel后，线程池中执行此任务的线程会继续执行
     * 但是如果再调用future.get()方法拿结果会抛出CancellationException
     * */
    public static void testcancel4() {
        ExecutorService executorService = Executors.newCachedThreadPool();
        Future<Integer> future = executorService.submit(() -> {
            System.out.println("执行一个任务开始");
//            try {
//                TimeUnit.SECONDS.sleep(10);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
            while (!Thread.interrupted()) {

            }
             System.out.println("执行一个任务结束");
            return 10;
        });
        try {
            TimeUnit.MILLISECONDS.sleep(20);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(future.cancel(true));
        System.out.println(future.isDone());
        System.out.println(future.isCancelled());
        try {
            System.out.println(future.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
//        testDone();
//        testDone1();
//        testDone2();
//        testcancel();
//        testcancel2();
//        testcancel3();
        testcancel4();
    }
}
