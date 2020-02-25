package com.mgw.three.utils.phaser;

import java.util.Random;
import java.util.concurrent.Phaser;
import java.util.concurrent.TimeUnit;

public class PhaserExample3 {

    private static final Random random = new Random(System.currentTimeMillis());

    // 铁人三项运动员
    static class Athletes extends Thread {

        private final Phaser phaser;

        private final int no;


        Athletes(Phaser phaser, int no) {
            this.phaser = phaser;
            this.no = no;
        }


        @Override
        public void run() {

            try {
                sport(phaser,no + ": start running",no + ": end running");

                sport(phaser,no + ": start bicycle",no + ": end bicycle");

                sport(phaser,no + ": start long jump",no + ": end long jump");

            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }

    // 受伤的运动员
    static class InjuredAthletes extends Thread {

        private final Phaser phaser;

        private final int no;


        InjuredAthletes(Phaser phaser, int no) {
            this.phaser = phaser;
            this.no = no;
        }


        @Override
        public void run() {

            try {
                sport(phaser,no + ": start running",no + ": end running");

                sport(phaser,no + ": start bicycle",no + ": end bicycle");

                System.out.println("Oh shit,i am injured,i will be exit");
                phaser.arriveAndDeregister();

            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }

    private static void sport(Phaser phaser,String x1,String x2) throws InterruptedException {
        System.out.println(x1);
        TimeUnit.SECONDS.sleep(random.nextInt(5));
        System.out.println(x2);

        phaser.arriveAndAwaitAdvance();
    }

    public static void main(String[] args) {

        Phaser phaser = new Phaser(5);

        for (int i= 1 ; i < 5 ;i++) {
            new Athletes(phaser,i).start();
        }

        new InjuredAthletes(phaser,5).start();
    }
}
