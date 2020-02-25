package com.mgw.three.executors;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;

public class CompletionServiceExample2 {

    private static void sleepSeconds(long seconds) {
        try {
            TimeUnit.SECONDS.sleep(seconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * {@link CompletionService#take()}
     * 这个方法是阻塞的方法
     * */
    private static void testTake() throws InterruptedException, ExecutionException {
        final ExecutorService executorService = Executors.newFixedThreadPool(2);

        List<Callable<Integer>> callables = Arrays.asList(() -> {
            sleepSeconds(10);
            System.out.println("the 10 finish");
            return 10;
        },() -> {
            sleepSeconds(20);
            System.out.println("the 20 finish");
            return 20;
        });

        ExecutorCompletionService<Integer> completionService = new ExecutorCompletionService<>(executorService);

        List<Future<Integer>> futures = new ArrayList<>();
        callables.stream().forEach(callable -> {
            futures.add(completionService.submit(callable));
        });
        Future<Integer> f = null;
        // 可以拿到最快结束的任务
        while ((f = completionService.take()) != null) {
            System.out.println(f.get());
        }
    }

    /**
     * {@link CompletionService#poll()}
     * poll方法是非阻塞的,这个方法使用是有风险的，因为你在使用时，任务不一定结束，而poll非阻塞，所以可能拿到的是null
     * */
    private static void testPoll() throws InterruptedException, ExecutionException {
        final ExecutorService executorService = Executors.newFixedThreadPool(2);

        List<Callable<Integer>> callables = Arrays.asList(() -> {
            sleepSeconds(10);
            System.out.println("the 10 finish");
            return 10;
        },() -> {
            sleepSeconds(20);
            System.out.println("the 20 finish");
            return 20;
        });

        ExecutorCompletionService<Integer> completionService = new ExecutorCompletionService<>(executorService);

        List<Future<Integer>> futures = new ArrayList<>();
        callables.stream().forEach(callable -> {
            futures.add(completionService.submit(callable));
        });
        Future<Integer> f = completionService.poll();
        // null 因为任务还没结束，没放进队列中
        System.out.println(f);

        // 可以这么使用  测试时无法达到预期 暂时没找到原因 TODO....
        /*Future<Integer> ff = null;
        while ((ff = completionService.poll()) != null) {
            System.out.println("==========");
            System.out.println(ff.get());
        }*/
    }

    private static class Event {
        private final int eventId;
        private String result;


        private Event(int eventId) {
            this.eventId = eventId;
        }

        public void setResult(String result) {
            this.result = result;
        }

        public int getEventId() {
            return eventId;
        }

        public String getResult() {
            return result;
        }
    }

    private static class MyTask implements Runnable {

        private final Event event;

        private MyTask(Event event) {
            this.event = event;
        }

        @Override
        public void run() {
            sleepSeconds(10);
            event.setResult("I am successfully");
        }
    }

    private static void testSubmitRunnable() throws InterruptedException, ExecutionException {

        ExecutorService executorService = Executors.newFixedThreadPool(2);
        ExecutorCompletionService<Event> completionService = new ExecutorCompletionService<>(executorService);

        Event event = new Event(1);
        Future<Event> future = completionService.submit(new MyTask(event), event);
        // 阻塞等待结果执行完毕与下面的take方法几乎同一时间执行
        System.out.println(future.get());

        System.out.println(completionService.take().get().getResult());
    }

    public static void main(String[] args) throws InterruptedException, ExecutionException {

//        testTake();
//        testPoll();
        testSubmitRunnable();
    }

}
