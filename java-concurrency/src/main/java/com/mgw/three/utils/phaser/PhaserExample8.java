package com.mgw.three.utils.phaser;

import java.sql.Time;
import java.util.concurrent.Executor;
import java.util.concurrent.Phaser;
import java.util.concurrent.TimeUnit;

/**
 * 停掉phase
 * */
public class PhaserExample8 {

    private static void test1() {

        Phaser phaser = new Phaser(3);
        new Thread(phaser::arriveAndAwaitAdvance).start();

        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(phaser.isTerminated());
        // 强制结束
        phaser.forceTermination();
        System.out.println(phaser.isTerminated());

    }
    public static void main(String[] args) {
        test1();
    }
}
