package com.mgw.three.utils.phaser;

import java.util.Random;
import java.util.concurrent.Phaser;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

/**
 * 动态增加parties
 * */
public class PhaserExample1 {

    private static final Random random = new Random(System.currentTimeMillis());

    public static void main(String[] args) {

        Phaser phaser = new Phaser();

//        IntStream.rangeClosed(1,5).boxed().map(i -> phaser).forEach(Task :: new);

        IntStream.rangeClosed(1,5).forEach(i -> {
            new Task(phaser).start();
        });

        phaser.register();
        phaser.arriveAndAwaitAdvance();

        System.out.println("All of worker finished the work");


    }

    static class Task extends Thread {

        private final Phaser phaser;

        Task(Phaser phaser) {
            this.phaser = phaser;
            this.phaser.register();
        }

        @Override
        public void run() {

            System.out.println("The woker ["+Thread.currentThread().getName()+"] is working...");
            try {
                TimeUnit.SECONDS.sleep(random.nextInt(5));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            phaser.arriveAndAwaitAdvance();
        }
    }
}
