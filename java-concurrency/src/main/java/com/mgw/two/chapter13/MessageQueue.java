package com.mgw.two.chapter13;

import java.util.LinkedList;

public class MessageQueue {

    private final LinkedList<Message> queue;

    // 默认的最大限制
    private final static int DEFAULT_MAX_LIMIT = 100;

    private final int limit;

    public MessageQueue() {
        this(DEFAULT_MAX_LIMIT);
    }

    public MessageQueue(final int limit) {
        this.limit = limit;
        queue = new LinkedList<>();
    }

    public void put(Message message) throws InterruptedException {

        synchronized (queue) {
            while (queue.size() > limit) {
                queue.wait();
            }
            queue.addLast(message);
            queue.notifyAll();
        }
    }

    public Message take() throws InterruptedException {

        synchronized (queue) {

            while (queue.isEmpty()) {

                queue.wait();
            }
            Message message = queue.removeFirst();
            queue.notifyAll();

            return message;
        }
    }

    public int getMaxLimit() {

        return this.limit;
    }

    public int getMessageSize()  {

        synchronized (queue) {

            return queue.size();
        }
    }

}
