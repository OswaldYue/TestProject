package com.mgw.one.chapter9;

import java.util.stream.Stream;

public class ProduceConsumerVersion3 {

    private  int i = 1;

    final private Object LOCK = new Object();

    private volatile boolean isProduced = false;

    private void produce() {

        synchronized (LOCK) {

            // 方法1
            while (isProduced) {
                try {
                    LOCK.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            i++;
            System.out.println(Thread.currentThread().getName() + "->" + i);
            LOCK.notifyAll();
            isProduced = true;

            // 方法2
//            if (isProduced) {
//                try {
//                    LOCK.wait();
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            } else {
//                i++;
//                System.out.println(Thread.currentThread().getName() + "->" + i);
//                LOCK.notifyAll();
//                isProduced = true;
//            }

        }

    }

    private void consume() {

        synchronized (LOCK) {
            // 方法1
            while (!isProduced) {
                try {
                    LOCK.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println(Thread.currentThread().getName() +"->" + i);
            LOCK.notifyAll();
            isProduced = false;

            // 方法2
//            if (isProduced) {
//                System.out.println(Thread.currentThread().getName() +"->" + i);
//                LOCK.notifyAll();
//                isProduced = false;
//            } else {
//                try {
//                    LOCK.wait();
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }

        }
    }

    public static void main(String[] args) {

        ProduceConsumerVersion3 produceConsumer = new ProduceConsumerVersion3();

        Stream.of("P1","p2","P3","p4").forEach((name)-> {
            new Thread(name) {

                @Override
                public void run() {
                    while (true) {
                        produceConsumer.produce();
                        try {
                            Thread.sleep(100);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }.start();
        });

        Stream.of("C1","C3","C3").forEach((name) -> {

            new Thread(name) {

                @Override
                public void run() {
                    while (true) {
                        produceConsumer.consume();
                        try {
                            Thread.sleep(100);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }.start();
        });


    }

}
