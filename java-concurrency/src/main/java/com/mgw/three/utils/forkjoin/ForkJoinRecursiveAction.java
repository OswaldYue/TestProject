package com.mgw.three.utils.forkjoin;

import java.util.Optional;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.IntStream;

// 无返回值
public class ForkJoinRecursiveAction {

    // 最大的阈值
    private final static int MAX_THRESHOLD = 3;

    private final static AtomicLong SUM = new AtomicLong();

    private static class CalculatedRecursiveAction extends RecursiveAction {

        private final int start;

        private final int end;

        private CalculatedRecursiveAction(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        protected void compute() {
            if ((end-start) <= MAX_THRESHOLD) {
                SUM.addAndGet(IntStream.rangeClosed(start,end).sum());
            }else {
                int middle = (start+end)/2;
                CalculatedRecursiveAction leftAction = new CalculatedRecursiveAction(start, middle);
                CalculatedRecursiveAction rightAction = new CalculatedRecursiveAction(middle+1, end);

                leftAction.fork();
                rightAction.fork();
            }
        }
    }


    public static void main(String[] args) {

        ForkJoinPool forkJoinPool = new ForkJoinPool();

        forkJoinPool.submit(new CalculatedRecursiveAction(0,10));

        try {
            forkJoinPool.awaitTermination(1, TimeUnit.SECONDS);
            Optional.of(SUM.get()).ifPresent(System.out::println);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

}
