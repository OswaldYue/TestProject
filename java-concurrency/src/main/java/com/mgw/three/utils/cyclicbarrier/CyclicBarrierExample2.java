package com.mgw.three.utils.cyclicbarrier;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;

public class CyclicBarrierExample2 {

    public static void main(String[] args) throws InterruptedException {

        CyclicBarrier barrier = new CyclicBarrier(3);

        new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(500);
                barrier.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
        }).start();

        new Thread(() -> {
            try {
                // java.util.concurrent.BrokenBarrierException
                // 这里会抛出异常，因为reset()时，如果有barrier在等待 则会被打断
                barrier.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
        }).start();

        new Thread(() -> {
            try {
                // java.util.concurrent.BrokenBarrierException
                // 这里会抛出异常，因为reset()时，如果有barrier在等待 则会被打断
                barrier.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
        }).start();

//        TimeUnit.SECONDS.sleep(1);
//        System.out.println(barrier.getNumberWaiting());
//        System.out.println(barrier.getParties());
//        System.out.println(barrier.isBroken());
//        barrier.reset();
    }
}
