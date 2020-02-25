package com.mgw.three.executors;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.TimeUnit;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;

public class CompletableFutureExample5 {

    private static void sleepSeconds(long seconds) {
        try {
            TimeUnit.SECONDS.sleep(seconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * {@link CompletableFuture#thenAcceptBoth(java.util.concurrent.CompletionStage, java.util.function.BiConsumer)}
     * */
    private static void testThenAcceptBoth() throws InterruptedException {
        CompletableFuture.supplyAsync(() -> {
            System.out.println("start the thenAcceptBoth 1");
            sleepSeconds(5);
            System.out.println("end the thenAcceptBoth 1");
            return "thenAcceptBoth";
        }).thenAcceptBoth(CompletableFuture.supplyAsync(() -> {
            System.out.println("start the thenAcceptBoth 2");
            sleepSeconds(5);
            System.out.println("end the thenAcceptBoth 2");
            return 100;
        }),(s,i) -> {
            System.out.println(s + "-----" + i);
        });
        Thread.currentThread().join();
    }

    /**
     * {@link CompletableFuture#acceptEither(CompletionStage, Consumer)}
     * */
    private static void testAcceptEither() throws InterruptedException {
        CompletableFuture.supplyAsync(() -> {
            System.out.println("start the acceptEither 1");
            sleepSeconds(5);
            System.out.println("end the acceptEither 1");
            return "acceptEither1";
        }).acceptEither(CompletableFuture.supplyAsync(() -> {
            System.out.println("start the acceptEither 2");
            sleepSeconds(5);
            System.out.println("end the acceptEither 2");
            return "acceptEither2";
        }),(v) -> {
            System.out.println(v);
        });

        Thread.currentThread().join();
    }

    /**
     * {@link CompletableFuture#runAfterBoth(CompletionStage, Runnable)}
     * 只有两个任务都执行完毕，才会去调用runAfterEither的第二个参数action
     * */
    private static void testRunAfterBoth() throws InterruptedException {
        CompletableFuture.supplyAsync(() -> {
            System.out.println("start the runAfterBoth 1");
            sleepSeconds(5);
            System.out.println("end the runAfterBoth 1");
            return "runAfterBoth1";
        }).runAfterBoth(CompletableFuture.supplyAsync(() -> {
            System.out.println("start the runAfterBoth 2");
            sleepSeconds(3);
            System.out.println("end the runAfterBoth 2");
            return 1000;
        }),() -> {
            System.out.println("====over====");
        });

        Thread.currentThread().join();
    }

    /**
     * {@link CompletableFuture#runAfterEither(CompletionStage, Runnable)}
     * 只要其中一个执行完毕，就会去调用runAfterEither的第二个参数action,但是那个没有执行完的任务还是会去继续执行
     *
     * */
    private static void testRunAfterEither() throws InterruptedException {
        CompletableFuture.supplyAsync(() -> {
            System.out.println("start the runAfterEither 1");
            sleepSeconds(5);
            System.out.println("end the runAfterEither 1");
            return "runAfterEither1";
        }).runAfterEither(CompletableFuture.supplyAsync(() -> {
            System.out.println("start the runAfterEither 2");
            sleepSeconds(3);
            System.out.println("end the runAfterEither 2");
            return 1000;
        }),() -> {
            System.out.println("====over====");
        });

        Thread.currentThread().join();
    }

    /**
     * {@link CompletableFuture#thenCombine(CompletionStage, BiFunction)}
     * */
    private static void testThenCombine() throws InterruptedException {
        CompletableFuture.supplyAsync(() -> {
            System.out.println("start the thenCombine 1");
            sleepSeconds(5);
            System.out.println("end the thenCombine 1");
            return "thenCombine1";
        }).thenCombine(CompletableFuture.supplyAsync(() -> {
            System.out.println("start the thenCombine 2");
            sleepSeconds(3);
            System.out.println("end the thenCombine 2");
            return 1000;
        }),(s,i) -> {
//            return s + "----" + i;
            return s.length() > i;
        }).whenComplete((b,t) -> {
            System.out.println(b);
        });

        Thread.currentThread().join();
    }

    /**
     * {@link CompletableFuture#thenCompose(Function)}
     * */
    private static void testThenCompose() throws InterruptedException {
        CompletableFuture.supplyAsync(() -> {
            System.out.println("start the thenCompose 1");
            sleepSeconds(5);
            System.out.println("end the thenCompose 1");
            return "thenCompose1";
        }).thenCompose(s -> {
            return CompletableFuture.supplyAsync(() -> {
                System.out.println("start the thenCompose 2");
                sleepSeconds(6);
                System.out.println("end the thenCompose 2");
                return s.length();
            });
        }).thenAccept(System.out::println);

        Thread.currentThread().join();
    }
    public static void main(String[] args) throws InterruptedException {
//        testThenAcceptBoth();
//        testAcceptEither();
//        testRunAfterBoth();
//        testRunAfterEither();
//        testThenCombine();
        testThenCompose();
    }

}
