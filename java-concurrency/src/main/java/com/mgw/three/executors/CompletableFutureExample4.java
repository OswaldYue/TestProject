package com.mgw.three.executors;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.function.Supplier;

public class CompletableFutureExample4 {

    /**
     * {@link CompletableFuture#whenComplete(java.util.function.BiConsumer)}
     * supplyAsync方法的返回值为一个future，whenComplete的返回值也是一个future，
     * 但是其实返回值都是第一个future的返回值
     * 这个方法是个同步方法，意思是future会在whenComplete执行完毕后返回结果
     * */
    private static void test1() throws ExecutionException, InterruptedException {
        final CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> "hello");
        final CompletableFuture<String> future1 = future.whenComplete((v, throwable) -> {
            try {
                TimeUnit.SECONDS.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("=========回调over=========");
        });

        System.out.println("111111111111111111111");
        System.out.println(future.get());// hello
        System.out.println(future1.get());// hello
    }

    /**
     * {@link CompletableFuture#whenCompleteAsync(BiConsumer)}
     * 这是个异步方法
     * 意思是whenCompleteAsync在执行时，能返回future就返回了，而不用等到whenCompleteAsync执行完毕
     * */
    private static void test2() throws ExecutionException, InterruptedException {
        final CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> "hello");
        final CompletableFuture<String> future1 = future.whenCompleteAsync((v,t) -> {
            try {
                TimeUnit.SECONDS.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("=========回调over=========");
        });
        System.out.println("111111111111111111111");
        System.out.println(future.get());// hello
        System.out.println(future1.get());// hello

        Thread.currentThread().join();
    }

    /**
     * {@link CompletableFuture#thenApply(Function)}
     * */
    private static void test3() throws ExecutionException, InterruptedException {
        final CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> "hello");
        final CompletableFuture<Integer> future1 = future.thenApply((v) -> {
            try {
                TimeUnit.SECONDS.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("=========over=========");
            return v.length();
        });
        System.out.println(future.get()); // hello
        System.out.println(future1.get()); // 5

        Thread.currentThread().join();
    }

    /**
     * {@link CompletableFuture#thenApplyAsync(Function)}
     * */
    private static void test4() throws ExecutionException, InterruptedException {
        final CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> "hello");
        final CompletableFuture<Integer> future1 = future.thenApplyAsync((v) -> {
            try {
                TimeUnit.SECONDS.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("=========over=========");
            return v.length();
        });
        System.out.println(future.get()); // hello
        System.out.println(future1.get()); // 5

        Thread.currentThread().join();
    }

    /**
     * {@link CompletableFuture#thenApplyAsync(Function)}
     * 与{@link CompletableFuture#whenComplete(BiConsumer)} 相比
     * 它会返回一个新的future
     * */
    private static void test5() throws ExecutionException, InterruptedException {
        // 正常情况
//        final CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> "hello");
//        final CompletableFuture<Integer> future1 = future.handle((v, t) -> {
//            return v.length();
//        });
        // 异常情况
        final CompletableFuture<Integer> future2 = CompletableFuture.supplyAsync((Supplier<String>) () -> {
            throw new RuntimeException("not get data");
        }).handle((s, t) -> {
            Optional.of(t).ifPresent(e -> {
                System.out.println("Error");
            });
            return s == null ? 0 : s.length();
        });

//        System.out.println(future.get());
//        System.out.println(future1.get());
        System.out.println(future2.get());

        Thread.currentThread().join();
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {

//        test1();
//        test2();
//        test3();
//        test4();
        test5();
    }
}
