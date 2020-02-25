package com.mgw.two.chapter2;

import java.util.Optional;
import java.util.stream.IntStream;

public class WaitSet {

    private static final Object LOCK = new Object();

    public static void main(String[] args) {

        IntStream.range(1,10).forEach(i -> {
            new Thread("Thread " + String.valueOf(i)) {
                @Override
                public void run() {
                    synchronized (LOCK) {
                        System.out.println();
                        try {
                            Optional.of(Thread.currentThread().getName() + " will come to wait set.")
                                    .ifPresent(System.out :: println);
                            Thread.sleep(2_000);
                            LOCK.wait();
                            Optional.of(Thread.currentThread().getName() + " will leave to wait set.")
                                    .ifPresent(System.out :: println);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }.start();
        });

        /*
        *  start方法是瞬发的方法，可能还没有全部启动完 下面的代码就已经抢到锁了 执行了notify方法
        * */

        IntStream.range(1,10).forEach(i -> {
            synchronized (LOCK) {
                System.out.println("notify " + i + "次.");
                LOCK.notify();
                try {
                    Thread.sleep(1_000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

    }

}
