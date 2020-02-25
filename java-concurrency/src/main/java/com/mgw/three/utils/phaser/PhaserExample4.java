package com.mgw.three.utils.phaser;

import java.sql.Time;
import java.util.concurrent.Phaser;
import java.util.concurrent.TimeUnit;

public class PhaserExample4 {

    private static void test1() {
        Phaser phaser = new Phaser(1);

        System.out.println(phaser.getPhase()); // 0
        phaser.arriveAndAwaitAdvance();
        System.out.println(phaser.getPhase()); // 1
        phaser.arriveAndAwaitAdvance();
        System.out.println(phaser.getPhase()); // 2
        phaser.arriveAndAwaitAdvance();
        System.out.println(phaser.getPhase()); // 3
    }

    private static void test2() {

        Phaser phaser = new Phaser(1);

        System.out.println(phaser.getRegisteredParties()); // 1
        phaser.register();
        System.out.println(phaser.getRegisteredParties()); // 2
        phaser.register();
        System.out.println(phaser.getRegisteredParties()); // 3

    }
    private static void test3() {

        Phaser phaser = new Phaser(1);

        System.out.println(phaser.getArrivedParties()); // 0
        System.out.println(phaser.getUnarrivedParties()); // 1
    }

    private static void test4() throws InterruptedException {

        Phaser phaser = new Phaser(1);

        phaser.bulkRegister(10);
        System.out.println(phaser.getRegisteredParties()); // 11
        System.out.println(phaser.getArrivedParties()); // 0
        System.out.println(phaser.getUnarrivedParties()); // 11
        new Thread(phaser :: arriveAndAwaitAdvance).start();
        TimeUnit.SECONDS.sleep(1);
        System.out.println("=============");
        System.out.println(phaser.getRegisteredParties()); // 11
        System.out.println(phaser.getArrivedParties()); // 1
        System.out.println(phaser.getUnarrivedParties()); // 10
    }

    static class OnAdvanceTask extends Thread {

        private final Phaser phaser;

        OnAdvanceTask(String name, Phaser phaser) {
            super(name);
            this.phaser = phaser;
        }

        @Override
        public void run() {
            System.out.println(getName() + " i am [1] start and the phase " + phaser.getPhase());
            phaser.arriveAndAwaitAdvance();
            System.out.println(getName() + " i am [1] end");

            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (getName().equals("A")) {
                System.out.println(getName() + " i am [2] start and the phase " + phaser.getPhase());
                phaser.arriveAndAwaitAdvance();
                System.out.println(getName() + " i am [2] end");
            }

        }
    }

    /**
     *onAdvance() 方法的返回值如果为true 则表示此phase应该被结束掉 也就是就算还没arrived也不会再阻拦
     *              为false 则表示此phase不应该被结束掉 如果没arrived会一直等待
     *  可以使用isTerminated()方法去判断是否已经结束掉
     * */
    private static void test5() throws InterruptedException {

        Phaser phaser = new Phaser(2) {
            @Override
            protected boolean onAdvance(int phase, int registeredParties) {
                return false;
            }
        };

        new OnAdvanceTask("A",phaser).start();
        new OnAdvanceTask("B",phaser).start();

        TimeUnit.SECONDS.sleep(2);
        System.out.println(phaser.getUnarrivedParties());
        System.out.println(phaser.getArrivedParties());
    }

    public static void main(String[] args) throws InterruptedException {

//        test1();
//        test2();
//        test3();
//        test4();
        test5();
    }


}
