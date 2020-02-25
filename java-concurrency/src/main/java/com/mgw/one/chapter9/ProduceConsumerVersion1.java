package com.mgw.one.chapter9;

public class ProduceConsumerVersion1 {

    private int i = 1;

    final private Object LOCK = new Object();

    private void produce() {

        synchronized (LOCK) {

            System.out.println("P->" + (i++));
        }

    }

    private void consume() {

        synchronized (LOCK) {
            System.out.println("C->" + i);
        }
    }

    public static void main(String[] args) {

        ProduceConsumerVersion1 produceConsumer = new ProduceConsumerVersion1();

        new Thread("P") {

            @Override
            public void run() {
                while (true) {
                    produceConsumer.produce();
                }
            }
        }.start();

        new Thread("C") {

            @Override
            public void run() {
                while (true) {
                    produceConsumer.consume();
                }
            }
        }.start();
    }

}
