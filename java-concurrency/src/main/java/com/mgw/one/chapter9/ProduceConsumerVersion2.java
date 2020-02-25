package com.mgw.one.chapter9;

public class ProduceConsumerVersion2 {

    private  int i = 1;

    final private Object LOCK = new Object();

    private volatile boolean isProduced = false;

    private void produce() {

        synchronized (LOCK) {

            if (isProduced) {
                try {
                    LOCK.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } else {
                i++;
                System.out.println("P->" + i);
                LOCK.notify();
                isProduced = true;
            }

        }

    }

    private void consume() {

        synchronized (LOCK) {
            if (isProduced) {
                System.out.println("C->" + i);
                LOCK.notify();
                isProduced = false;
            } else {
                try {
                    LOCK.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }
    }

    public static void main(String[] args) {

        ProduceConsumerVersion2 produceConsumer = new ProduceConsumerVersion2();

        new Thread("P1") {

            @Override
            public void run() {
                while (true) {
                    produceConsumer.produce();
                }
            }
        }.start();



        new Thread("C1") {

            @Override
            public void run() {
                while (true) {
                    produceConsumer.consume();
                }
            }
        }.start();

        // 当多线程一起来执行生产者消费者时，会出现问题
        // 生产者notify了另一个生成者的wait，生产者进入wait，然后被唤醒的生成者判断isProduced为true，则被唤醒的生产者进入wait
        // 消费者notify了另一个消费者的wait，消费者进入wait，然后被唤醒的消费者判断isProduced为true，则被唤醒的消费者进入wait
        // 造成全部wait状态

//        new Thread("C2") {
//
//            @Override
//            public void run() {
//                while (true) {
//                    produceConsumer.consume();
//                }
//            }
//        }.start();
//
//        new Thread("P2") {
//
//            @Override
//            public void run() {
//                while (true) {
//                    produceConsumer.produce();
//                }
//            }
//        }.start();
    }

}
