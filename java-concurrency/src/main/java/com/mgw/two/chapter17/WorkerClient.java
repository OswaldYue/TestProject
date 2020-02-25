package com.mgw.two.chapter17;

/**
 * Worker-Thread 设计模式
 *
 * */
public class WorkerClient {

    public static void main(String[] args) {

        Channel channel = new Channel(5);
        channel.startWorker();

        new TransportThread("MMM",channel).start();
        new TransportThread("NNN",channel).start();
        new TransportThread("QQQ",channel).start();
    }
}
