package com.mgw.two.chapter15;

import java.util.stream.IntStream;

public class PerThreadClient {

    public static void main(String[] args) {

        MessageHandler messageHandler = new MessageHandler();

        IntStream.rangeClosed(1,10).forEach(i -> {
            messageHandler.request(new Message(String.valueOf(i)));
        });

        messageHandler.shutdown();

    }
}
