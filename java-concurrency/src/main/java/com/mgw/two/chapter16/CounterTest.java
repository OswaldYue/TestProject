package com.mgw.two.chapter16;

public class CounterTest {

    public static void main(String[] args) throws InterruptedException {

        CounterIncrement counterIncrement = new CounterIncrement();
        counterIncrement.start();

        Thread.sleep(10_000);

        counterIncrement.close();

    }
}
