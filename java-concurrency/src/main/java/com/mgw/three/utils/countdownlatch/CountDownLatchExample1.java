package com.mgw.three.utils.countdownlatch;

import java.util.Random;
import java.util.concurrent.*;

public class CountDownLatchExample1 {

    private static Random random = new Random(System.currentTimeMillis());

    private static ExecutorService executor = Executors.newFixedThreadPool(2);

    private static final CountDownLatch countDownLatch = new CountDownLatch(10);

    public static void main(String[] args) throws InterruptedException {

        // 阶段1 取数据
        int[] data = query();

        // 阶段2 处理数据
        for (int i = 0 ; i < data.length ; i++) {
            executor.execute(new SimpleRunnable(data,i,countDownLatch));
        }

        countDownLatch.await();
        executor.shutdown();
//        executor.awaitTermination(1, TimeUnit.HOURS);

        // 阶段3 需要阶段1与阶段2全部都完成后才能开始
        System.out.println("all of work finish done.");
    }

    static class SimpleRunnable implements Runnable {

        private final int[] data;

        private final int index;

        private final CountDownLatch countDownLatch;

        SimpleRunnable(int[] data, int index, CountDownLatch countDownLatch) {
            this.data = data;
            this.index = index;
            this.countDownLatch = countDownLatch;
        }

        @Override
        public void run() {
            try {
                Thread.sleep(random.nextInt(2000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            int value = data[index];
            if (value % 2 == 0) {
                data[index] = value * 2;
            }else {
                data[index] = value * 10;
            }
            System.out.println(Thread.currentThread().getName() + " finished.");
            countDownLatch.countDown();
        }
    }

    public static int[] query() {
        return new int[] {1,2,3,4,5,6,7,8,9,10};
    }

}
