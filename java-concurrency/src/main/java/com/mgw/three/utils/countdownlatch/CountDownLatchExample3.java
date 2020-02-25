package com.mgw.three.utils.countdownlatch;

import com.mgw.two.chapter14.CountDown;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class CountDownLatchExample3 {


    // await的结束情况1 ： countDown的数量自然减少完毕
    private static void test1() throws InterruptedException {

        CountDownLatch latch = new CountDownLatch(1);


        new Thread() {
            @Override
            public void run() {
                try {
                    Thread.sleep(10_000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                latch.countDown();
            }
        }.start();

        latch.await();

        System.out.println("=============");
    }

    // await的结束情况2 ： 由他人进行中断
    private static void test2() throws InterruptedException {

        CountDownLatch latch = new CountDownLatch(1);

        final Thread currentThread = Thread.currentThread();

        new Thread() {
            @Override
            public void run() {
                try {
                    Thread.sleep(10_000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                currentThread.interrupt();
            }
        }.start();

        latch.await();

        System.out.println("=============");
    }

    // await(long timeout, TimeUnit unit)的使用
    private static void test3() throws InterruptedException {

        CountDownLatch latch = new CountDownLatch(1);

        new Thread() {
            @Override
            public void run() {
                try {
                    Thread.sleep(10_000);
//                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }.start();

        // 当await()的时间小于工作的线程的时间  则不再await 继续向后执行
        // 当await()的时间大于工作的线程的时间  则工作线程结束  继续向后执行
        latch.await(2, TimeUnit.SECONDS);

        System.out.println("=============");
    }

    public static void main(String[] args) throws InterruptedException {

//        test1();
//        test2();
        test3();
    }

}
