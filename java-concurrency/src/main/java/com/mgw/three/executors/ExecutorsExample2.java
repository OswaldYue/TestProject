package com.mgw.three.executors;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ExecutorsExample2 {


    /**
     * new ForkJoinPool
     *             (Runtime.getRuntime().availableProcessors(),
     *              ForkJoinPool.defaultForkJoinWorkerThreadFactory,
     *              null, true);
     * */
    public static void useWorkStealingPool() {

        ExecutorService executorService = Executors.newWorkStealingPool();
//        Optional.of(Runtime.getRuntime().availableProcessors()).ifPresent(System.out::println);

        List<Callable<String>> callableList = IntStream.range(0, 20).boxed().map(i -> {
            return (Callable<String>) () -> {
                System.out.println(Thread.currentThread().getName());
                sleepSeconds(5);
                return "Task-" + i;
            };
        }).collect(Collectors.toList());

        List<Future<String>> futures = null;
        try {
            futures = executorService.invokeAll(callableList);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        futures.stream().map(future -> {
            try {
                return future.get();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }).forEach(System.out::println);
    }

    private static void sleepSeconds(long seconds) {
        try {
            TimeUnit.SECONDS.sleep(seconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        useWorkStealingPool();
    }
}
