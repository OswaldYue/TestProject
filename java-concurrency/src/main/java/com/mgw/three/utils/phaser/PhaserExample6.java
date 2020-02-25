package com.mgw.three.utils.phaser;

import java.util.Random;
import java.util.concurrent.Phaser;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

/**
 *
 * */
public class PhaserExample6 {

    private static final Random random = new Random(System.currentTimeMillis());


    static class AwaitAdvanceTask extends Thread {

        private final Phaser phaser;

        AwaitAdvanceTask(Phaser phaser) {
            this.phaser = phaser;
            start();
        }

        @Override
        public void run() {
            try {
                TimeUnit.SECONDS.sleep(random.nextInt(5));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            phaser.arriveAndAwaitAdvance();
            System.out.println(getName() + " finished work");
        }
    }

    static class AwaitAdvanceTask1 extends Thread {

        private final Phaser phaser;

        AwaitAdvanceTask1(Phaser phaser) {
            this.phaser = phaser;
            start();
        }

        @Override
        public void run() {
            try {
                TimeUnit.SECONDS.sleep(random.nextInt(5));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            phaser.arrive();
            System.out.println(getName() + " finished work");
        }
    }

    private static void test1() {

        Phaser phaser = new Phaser(6);
        // 会阻拦让程序阻塞
        new Thread(() -> phaser.awaitAdvance(phaser.getPhase())).start();
        // 不会阻拦让程序不阻塞
//        new Thread(() -> phaser.awaitAdvance(10)).start();

        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(phaser.getArrivedParties());

    }

    /**
     * 刚好运行结束
     * */
    private static void test2() {

        Phaser phaser = new Phaser(6);

        IntStream.rangeClosed(0,5).boxed().map(i -> phaser).forEach(AwaitAdvanceTask :: new);

        phaser.awaitAdvance(phaser.getPhase());
        System.out.println("================");

    }

    /**
     * 定义了7个parties,实际运行中只有6个达到了arrive 少了一个parties 所以不会结束
     * */
    private static void test3() {

        Phaser phaser = new Phaser(7);

        IntStream.rangeClosed(0,5).boxed().map(i -> phaser).forEach(AwaitAdvanceTask :: new);

        phaser.awaitAdvance(phaser.getPhase());
        System.out.println("================");

    }

    /**
     * 定义了7个parties,实际运行中只有6个达到了arrive 少了一个parties 所以不会结束
     * */
    private static void test4() {

        Phaser phaser = new Phaser(7);

        IntStream.rangeClosed(0,5).boxed().map(i -> phaser).forEach(AwaitAdvanceTask1 :: new);

        phaser.awaitAdvance(phaser.getPhase());
        System.out.println("================");

    }

    public static void main(String[] args) {

//        test1();
//        test2();
//        test3();
        test4();
    }
}
