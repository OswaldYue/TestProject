package com.mgw.three.executors;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

public class CompletableFutureExample6 {

    private static void sleepSeconds(long seconds) {
        try {
            TimeUnit.SECONDS.sleep(seconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * {@link CompletableFuture#getNow(Object)}
     * */
    private static void testGetNow() throws ExecutionException, InterruptedException {
        final CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
            System.out.println("start the getNow 1");
            sleepSeconds(5);
            System.out.println("end the getNow 1");
            return "getNow1";
        });
        final String futureNow = future.getNow("getNow2");

        System.out.println(futureNow);
        System.out.println(future.get());

        Thread.currentThread().join();
    }

    /**
     * {@link CompletableFuture#complete(Object)}
     * 如果执行到complete时，第一个任务还没执行完毕，那么complete返回就是true，且future.get拿到的就是complete的值，
     * 如果第一个任务还没开始执行，那就不再执行，如果已经开始执行，那继续执行，但是future.get拿到的就是complete的值
     * */
    private static void testComplete1() throws ExecutionException, InterruptedException {
        final CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
            System.out.println("start the getNow 1");
            sleepSeconds(5);
            System.out.println("end the getNow 1");
            return "getNow1";
        });
        final boolean complete = future.complete("getNow2");
        System.out.println(complete);
        System.out.println(future.get());

        Thread.currentThread().join();
    }

    /**
     * {@link CompletableFuture#complete(Object)}
     * 如果执行到complete时，第一个任务已经执行完毕，那么complete返回就是false，且future.get拿到的是第一个任务执行完成时的值
     * */
    private static void testComplete2() throws ExecutionException, InterruptedException {
        final CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
            System.out.println("start the getNow 1");
            sleepSeconds(5);
            System.out.println("end the getNow 1");
            return "getNow1";
        });
        sleepSeconds(1);
        final boolean complete = future.complete("getNow2");
        System.out.println(complete);
        System.out.println(future.get());

        Thread.currentThread().join();
    }

    /**
     * {@link CompletableFuture#join()}
     *
     * */
    private static void testJoin() throws ExecutionException, InterruptedException {
        final CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
            System.out.println("start the getNow 1");
            sleepSeconds(5);
            System.out.println("end the getNow 1");
            return "getNow1";
        });
        final String s = future.join();

        System.out.println(s);

        Thread.currentThread().join();
    }

    /**
     * {@link CompletableFuture#completeExceptionally(Throwable)}
     * 调用时get()方法时，如果还没有执行完毕，就会抛一个异常
     * */
    private static void testCompleteExceptionally() throws ExecutionException, InterruptedException {
        final CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
            System.out.println("start the getNow 1");
            sleepSeconds(5);
            System.out.println("end the getNow 1");
            return "getNow1";
        });
        sleepSeconds(1);
        final boolean b = future.completeExceptionally(new RuntimeException("还没执行完毕"));
        System.out.println(b);
        System.out.println(future.get());

        Thread.currentThread().join();
    }

    /**
     * {@link CompletableFuture#obtrudeException(Throwable)}
     *
     * */
    private static void testObtrudeException() throws ExecutionException, InterruptedException {
        final CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
            System.out.println("start the getNow 1");
            sleepSeconds(5);
            System.out.println("end the getNow 1");
            return "getNow1";
        });

        sleepSeconds(1);
        future.obtrudeException(new Exception("错误"));
        System.out.println(future.get());

        Thread.currentThread().join();
    }
    /**
     * 第一个futrue执行完毕后返回，你可以拿上拿到返回值，第二个future1可以去执行其他接下来的事情
     * {@link CompletableFuture#exceptionally(java.util.function.Function)}
     * */
    private static void testErrorHandle() throws ExecutionException, InterruptedException {
        final CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
            System.out.println("start the Hello 1");
            sleepSeconds(5);
            System.out.println("end the Hello 1");
            return "Hello1";
        });
        final CompletableFuture<String> future1 = future.thenApply(s -> {
            Integer.parseInt(s); // 制造一个异常
            sleepSeconds(10);
            System.out.println("======继续执行=======");

            return s + " Hello2";
            // 执行出错，出错信息交给另一个任务去做
        }).exceptionally((t) -> {
            return t.getMessage();
        });

        System.out.println(future.get());
        System.out.println(future1.get());

        Thread.currentThread().join();
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
//        testGetNow();
//        testComplete1();
//        testComplete2();
//        testJoin();
//        testCompleteExceptionally();
//        testObtrudeException();
        testErrorHandle();
    }
}
