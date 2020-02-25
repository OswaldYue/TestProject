package com.mgw.three.executors;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

public class CompletableFutureExample3 {

    /**
     * {@link CompletableFuture#supplyAsync(java.util.function.Supplier)}
     * {@link CompletableFuture#runAfterBoth(java.util.concurrent.CompletionStage, java.lang.Runnable)}
     * runAfterBoth前面的与后面的是并行执行的，谁先执行完不确定，
     * 但是runAfterBoth中的第二个参数action是肯定在其两者完成之后执行的
     * */
    private static void testSupplyAsync() throws InterruptedException {

        CompletableFuture.supplyAsync(Object::new).thenAcceptAsync(obj -> {
            try {
                System.out.println("obj=======start");
                TimeUnit.SECONDS.sleep(5);
                System.out.println("obj=======end " + obj);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).runAfterBoth(CompletableFuture.supplyAsync(() -> "hello")
                .thenAcceptAsync(str -> {
                    try {
                        System.out.println("String=======start");
                        TimeUnit.SECONDS.sleep(3);
                        System.out.println("str=======end " + str);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }),() -> {
                    System.out.println("==========Finish=========");
                }
        );

        Thread.currentThread().join();
    }

    /**
     * {@link CompletableFuture#runAsync(Runnable)}
     * */
    private static void testRunAsync() throws InterruptedException {

        CompletableFuture.runAsync(() -> {
            System.out.println("============start==========");
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("============end==========");
        }).whenComplete((v,t) -> {
            System.out.println("===========回调over==========");
        });

        Thread.currentThread().join();
    }

    /**
     * {@link CompletableFuture#completedFuture(Object)}
     * */
    private static void testCompletedFuture() throws InterruptedException {
        final CompletableFuture<Void> future = CompletableFuture.completedFuture("Hello").thenAccept(System.out::println);
        Thread.currentThread().join();
    }

    /**
     * {@link CompletableFuture#anyOf(CompletableFuture[])}
     *
     * 这个任意其中的一个指的不是任意执行其中的一个任务，而是执行完任务结束后任意拿到最后的那个Future值
     * */
    private static void testAnyOf() throws InterruptedException, ExecutionException {
        final CompletableFuture<Object> future = CompletableFuture.anyOf(CompletableFuture.runAsync(() -> {
            System.out.println("1============start==========");
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("1============end==========");
        }).whenComplete((v, t) -> {
            System.out.println("1===========回调over==========");
        }), CompletableFuture.supplyAsync(() -> {
            System.out.println("2============start==========");
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("2============end==========");
            return "HELLO";
        }).whenComplete((v, t) -> {
            System.out.println("2===========回调over==========value = " + v);
        }));

        System.out.println(future.get());
        Thread.currentThread().join();
    }
    /**
     * {@link CompletableFuture#allOf(CompletableFuture[])}
     * 这个本身不会返回一个值给你
     * */

    private static void testAllOf() throws InterruptedException, ExecutionException {
        final CompletableFuture<Void> future = CompletableFuture.allOf(CompletableFuture.runAsync(() -> {
            System.out.println("1============start==========");
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("1============end==========");
        }).whenComplete((v, t) -> {
            System.out.println("1===========回调over==========");
        }), CompletableFuture.supplyAsync(() -> {
            System.out.println("2============start==========");
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("2============end==========");
            return "HELLO";
        }).whenComplete((v, t) -> {
            System.out.println("2===========回调over==========value = " + v);
        }));
        Thread.currentThread().join();
    }
    /**
     * {@link CompletableFuture#CompletableFuture()}
     * 这种构造不要用
     * */
    private static void testConstructors() {
        final CompletableFuture<String> completableFuture = new CompletableFuture<>();
        // 相当于等价下面的
        String s = null;
        CompletableFuture.supplyAsync(() -> s);
    }
    public static void main(String[] args) throws InterruptedException, ExecutionException {
//        testSupplyAsync();
//        testRunAsync();
//        testCompletedFuture();
//        testAnyOf();
        testAllOf();
    }

}
