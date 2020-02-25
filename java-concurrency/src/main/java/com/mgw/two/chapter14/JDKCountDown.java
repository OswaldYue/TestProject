package com.mgw.two.chapter14;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.stream.IntStream;

public class JDKCountDown {

    private static final Random random = new Random(System.currentTimeMillis());

    public static void main(String[] args) throws InterruptedException {

        CountDownLatch countDownLatch = new CountDownLatch(5);

        System.out.println("准备多线程处理任务");
        // 第一阶段
        IntStream.rangeClosed(1,5).forEach(i -> {
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName() + " is working.");
                try {
                    Thread.sleep(random.nextInt(1000));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                countDownLatch.countDown();
            },String.valueOf(i)).start();
        });

        countDownLatch.await();
        //第二阶段
        System.out.println("多线程任务处理全部结束，准备第二阶段");
        System.out.println("----------------第二阶段---------------");
        System.out.println("FINISH");
    }

}
