package com.mgw.three.utils.cyclicbarrier;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class CyclicBarrierExample3 {

    static class MyCountDownLatch extends CountDownLatch {

        private final Runnable runnable;

        public MyCountDownLatch(int count, Runnable runnable) {
            super(count);
            this.runnable = runnable;
        }

        public void countDown() {
            super.countDown();
            if (super.getCount() == 0) {
                this.runnable.run();
            }
        }
    }


    public static void main(String[] args) {

        final MyCountDownLatch myCountDownLatch = new MyCountDownLatch(2, () -> {
            System.out.println("all of work finish done");
        });

        new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
                myCountDownLatch.countDown();
                System.out.println(Thread.currentThread().getName() + " finished wait.");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(2);
                myCountDownLatch.countDown();
                System.out.println(Thread.currentThread().getName() + " finished wait.");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }
}
