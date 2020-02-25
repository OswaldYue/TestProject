package com.mgw.two.chapter15;

import java.util.Random;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MessageHandler {

    private final static Random random = new Random(System.currentTimeMillis());

    //使用线程池代替线程
    private final static Executor exector = Executors.newFixedThreadPool(5);


    public void request(Message message) {

        exector.execute(() -> {
            String value = message.getValue();
            try {
                Thread.sleep(random.nextInt(1000));
                System.out.println("The message will hyandler by " + Thread.currentThread().getName() + ", value is " + value);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        /*new Thread(() -> {
            String value = message.getValue();
            try {
                Thread.sleep(random.nextInt(1000));
                System.out.println("The message will hyandler by " + Thread.currentThread().getName());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }).start();*/

    }

    // 关闭线程池
    public void shutdown() {

        ((ExecutorService)exector).shutdown();
    }

}
