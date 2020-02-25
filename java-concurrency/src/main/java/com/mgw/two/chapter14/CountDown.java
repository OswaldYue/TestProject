package com.mgw.two.chapter14;

public class CountDown {

    private final int total;

    // 计数器
    private int counter;

    public CountDown(int total) {
        this.total = total;
    }

    public void down() {

        synchronized (this) {

            this.counter++;
            this.notifyAll();
        }
    }

    public void await() throws InterruptedException {
        synchronized (this) {
            while (counter != total) {
                this.wait();
            }
        }
    }

}
