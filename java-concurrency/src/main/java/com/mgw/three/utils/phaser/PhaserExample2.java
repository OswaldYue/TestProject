package com.mgw.three.utils.phaser;

import java.util.Random;
import java.util.concurrent.Phaser;
import java.util.concurrent.TimeUnit;

/**
 * 循环使用计数
 * */
public class PhaserExample2 {

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
                System.out.println(no + ": start running");
                TimeUnit.SECONDS.sleep(random.nextInt(5));
                System.out.println(no + ": end running");
                System.out.println("getPhase() => " + phaser.getPhase());
                phaser.arriveAndAwaitAdvance();

                System.out.println(no + ": start bicycle");
                TimeUnit.SECONDS.sleep(random.nextInt(5));
                System.out.println(no + ": end bicycle");
                System.out.println("getPhase() ==> " + phaser.getPhase());
                phaser.arriveAndAwaitAdvance();

                System.out.println(no + ": start long jump");
                TimeUnit.SECONDS.sleep(random.nextInt(5));
                System.out.println(no + ": end long jump");
                System.out.println("getPhase() ===> " + phaser.getPhase());
                phaser.arriveAndAwaitAdvance();

            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }

    public static void main(String[] args) {

        Phaser phaser = new Phaser(5);

        for (int i= 1 ; i < 6 ;i++) {
            new Athletes(phaser,i).start();
        }
    }

}
