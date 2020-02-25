package com.mgw.two.chapter13;

public class ProducerAndConsumerClient {

    public static void main(String[] args) {

        MessageQueue queue = new MessageQueue();

        new ProducerThread(queue,1).start();
        new ProducerThread(queue,2).start();
        new ProducerThread(queue,3).start();
        new ConsumerThread(queue,1).start();
        new ConsumerThread(queue,2).start();
    }

}
