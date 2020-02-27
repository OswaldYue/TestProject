package com.mgw.three.collections.concurrent;

import java.util.concurrent.ConcurrentLinkedQueue;

public class ConcurrentLinkedQueueExample {

    public static void main(String[] args) {

        final ConcurrentLinkedQueue<Long> queue = new ConcurrentLinkedQueue<>();

        for (int i = 0;i<100000;i++) {
            queue.offer(System.nanoTime());
        }
        System.out.println("===== offer done =====");

        long start = System.currentTimeMillis();
        while (queue.size() > 0) {
            queue.poll();
        }

        while (!queue.isEmpty()) {
            queue.poll();
        }
        System.out.println(System.currentTimeMillis() -start);
    }
}
