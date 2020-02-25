package com.mgw.two.chapter14;

import java.util.Random;
import java.util.stream.IntStream;

public class CountDownTest {

    private static final Random random = new Random(System.currentTimeMillis());

    public static void main(String[] args) throws InterruptedException {

        CountDown countDown = new CountDown(5);

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
                countDown.down();
            },String.valueOf(i)).start();
        });

        countDown.await();
        //第二阶段
        System.out.println("多线程任务处理全部结束，准备第二阶段");
        System.out.println("----------------第二阶段---------------");
        System.out.println("FINISH");
    }
}
