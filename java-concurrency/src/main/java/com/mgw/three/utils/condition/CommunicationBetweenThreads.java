package com.mgw.three.utils.condition;

import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

/**
 * 线程间通讯
 *
 * */
public class CommunicationBetweenThreads {

    private static int data = 0;

    private static boolean noUse = true;

    private final static Object MONITOR = new Object();

    private static void sleep(long seconds) {

        try {
            TimeUnit.SECONDS.sleep(seconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    private static void buildData() {

        synchronized (MONITOR) {
            while (noUse) {
                try {
                    MONITOR.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            data++;
            sleep(1);
            System.out.println(Thread.currentThread().getName() + "--P => " + data);
            noUse = true;
            MONITOR.notifyAll();
        }

    }

    private static void useData() {

        synchronized (MONITOR) {
            while (!noUse) {
                try {
                    MONITOR.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            sleep(2);
            System.out.println(Thread.currentThread().getName() + "--C => " + data);
            noUse = false;
            MONITOR.notifyAll();
        }

    }

    public static void main(String[] args) {

        new Thread(()-> {
            for (;;) {
                buildData();
            }
        }).start();

        IntStream.range(0,2).forEach(i -> {
            new Thread(() -> {
                for (;;) {
                    useData();
                }
            }).start();
        });

    }

}
