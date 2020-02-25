package com.mgw.two.chapter9;

public class SuspensionClient {

    public static void main(String[] args) throws InterruptedException {

        final RequestQueue queue = new RequestQueue();

        new ClientThread(queue,"MMM").start();
        ServerThread serverThread = new ServerThread(queue);
        serverThread.start();

        // 10秒是模拟等clientThread全部制造完毕,serverThread全部消费完毕
//        Thread.sleep(10_000);
        // 2秒模拟不等clientThread全部制造完毕,就将serverThread关闭
        Thread.sleep(2_000);

        serverThread.close();

    }
}
