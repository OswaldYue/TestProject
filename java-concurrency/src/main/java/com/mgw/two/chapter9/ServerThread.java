package com.mgw.two.chapter9;

import java.util.Random;

public class ServerThread extends Thread {


    private final RequestQueue queue;

    private final Random random;

    // 为了关掉
    private volatile boolean closed = false;

    public ServerThread(RequestQueue queue) {
        this.queue = queue;
        this.random = new Random(System.currentTimeMillis());
    }



    @Override
    public void run() {

        while (!closed) {

            Request request = queue.getRequest();
            if (null == request) {
                System.out.println("Received the empty request.");
                continue;
            }
            System.out.println("Server -> " + request.getValue());
            try {
                Thread.sleep(random.nextInt(1_000));
            } catch (InterruptedException e) {
                return;
            }
        }

    }

    public void close() {
        this.closed = true;
        //当queue.getRequest中wait时，则打断让其出来
        this.interrupt();
    }

}
