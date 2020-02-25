package com.mgw.two.chapter10;

import java.util.Random;

public class ThreadLocalSimulatorTest {

    private  static ThreadLocalSimulator threadLocal = new ThreadLocalSimulator() {

        @Override
        protected Object initialValue() {
            return "null value";
        }
    };

    private static final Random random = new Random(System.currentTimeMillis());

    public static void main(String[] args) throws InterruptedException {

        Thread thread1 = new Thread(() -> {
            threadLocal.set("Thread-T1");

            try {
                Thread.sleep(random.nextInt(1000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + " " + threadLocal.get());
        });

        Thread thread2 = new Thread(() -> {
            threadLocal.set("Thread-T2");

            try {
                Thread.sleep(random.nextInt(1000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println(Thread.currentThread().getName() + " " + threadLocal.get());
        });

        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();

        System.out.println(threadLocal.get());
        System.out.println("===============end=================");


    }

}
