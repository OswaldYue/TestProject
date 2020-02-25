package com.mgw.three.utils.cyclicbarrier;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;

public class CyclicBarrierExample1 {

    public static void main(String[] args) throws BrokenBarrierException, InterruptedException {

        CyclicBarrier barrier = new CyclicBarrier(3);
        // 回调的方式
        /*CyclicBarrier barrier1 = new CyclicBarrier(3,() -> {

            System.out.println("All finished.");
        });*/

        new Thread(){
            @Override
            public void run() {
                try {
                    TimeUnit.SECONDS.sleep(10);
                    System.out.println("T1 finished.");
                    barrier.await();
                    System.out.println("T1 the other thread finished too.");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }
        }.start();

        new Thread(){
            @Override
            public void run() {
                try {
                    TimeUnit.SECONDS.sleep(5);
                    System.out.println("T2 finished.");
                    barrier.await();
                    System.out.println("T2 the other thread finished too.");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }
        }.start();

        barrier.await();
        System.out.println("All finished.");
    }

}
