package com.mgw.three.utils.phaser;

import java.util.Random;
import java.util.concurrent.Phaser;
import java.util.concurrent.TimeUnit;

public class PhaserExample5 {

    private static final Random random = new Random(System.currentTimeMillis());

    /**
     * arrive()方法 Arrives at this phaser, without waiting for others to arrive.
     * 当没有注册parties数量时会报错 例如:new Phaser() 没有增加parties
     *      Attempted arrival of unregistered party for java.util.concurrent.Phaser@11efd657[phase = 0 parties = 0 arrived = 0]
     * */
    public static void test1() throws InterruptedException {

        Phaser phaser = new Phaser();
        new Thread(phaser :: arrive).start();

        TimeUnit.SECONDS.sleep(4);
    }

    private static void sleepSeconds() {
        try {
            TimeUnit.SECONDS.sleep(random.nextInt(5));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static class ArriveTask extends Thread {

        private final Phaser phaser;


        private ArriveTask(Phaser phaser,int no) {
            super(String.valueOf(no));
            this.phaser = phaser;
        }

        @Override
        public void run() {
            System.out.println(getName() + " start working");
            sleepSeconds();
            System.out.println(getName() + " the phase one is running");
            phaser.arrive();

            sleepSeconds();
            System.out.println(getName() + " keep to do other thing");

        }
    }

    /**
     * 主线程只关心4个线程前面的phase one要完成，至于各个线程后面的other thing不关心
     * */
    public static void test2() throws InterruptedException {

        Phaser phaser = new Phaser(5);
        for (int i = 0 ; i < 4 ;i++) {
            new ArriveTask(phaser,i).start();
        }

        phaser.arriveAndAwaitAdvance();

        System.out.println("The phase 1 work finished done");
    }



    public static void main(String[] args) throws InterruptedException {

//        test1();
        test2();
    }
}
