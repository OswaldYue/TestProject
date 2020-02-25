package com.mgw.three.utils.phaser;

import java.util.concurrent.Phaser;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class PhaserExample7 {

    /**
     * arriveAndAwaitAdvance() 方法不能打断
     * */
    private static void test1() {

        Phaser phaser = new Phaser(3);
        Thread thread = new Thread(phaser::arriveAndAwaitAdvance);
        thread.start();
        System.out.println("==============");

        try {
            TimeUnit.SECONDS.sleep(10);
            thread.interrupt();
            System.out.println("====thread.interrupt====");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    /**
     * awaitAdvanceInterruptibly()方法可以被打断
     * */
    private static void test2() {

        Phaser phaser = new Phaser(3);
        Thread thread = new Thread(() -> {
            try {
                phaser.awaitAdvanceInterruptibly(phaser.getPhase());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        thread.start();
        System.out.println("==============");

        try {
            TimeUnit.SECONDS.sleep(10);
            thread.interrupt();
            System.out.println("====thread.interrupt====");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * awaitAdvanceInterruptibly()方法可以被打断 但是此方法有陷阱
     *  如果所给的phase与当前的phase不一致，则会立即返回
     * */
    private static void test3() {

        Phaser phaser = new Phaser(3);
        Thread thread = new Thread(() -> {
            try {
                phaser.awaitAdvanceInterruptibly(10);
                System.out.println("*******立即返回********");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        thread.start();
        System.out.println("==============");

        try {
            TimeUnit.SECONDS.sleep(10);
            thread.interrupt();
            System.out.println("====thread.interrupt====");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * awaitAdvanceInterruptibly(int phase,long timeout, TimeUnit unit)
     * 如果等待超时就不等了
     *
     * */
    private static void test4() {
        Phaser phaser = new Phaser(3);
        Thread thread = new Thread(() -> {
            try {
                phaser.awaitAdvanceInterruptibly(0,10,TimeUnit.SECONDS);
                System.out.println("*******立即返回********");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (TimeoutException e) {
                e.printStackTrace();
            }
        });
        thread.start();
    }

    public static void main(String[] args) {
//        test1();
//        test2();
//        test3();
        test4();
    }

}
