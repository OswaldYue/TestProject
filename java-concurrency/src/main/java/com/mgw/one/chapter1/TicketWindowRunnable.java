package com.mgw.one.chapter1;

/**
 * 此程序有鬓发问题
 * */
public class TicketWindowRunnable implements Runnable {

    private int index = 1;

    private final static int MAx = 50;

    @Override
    public void run() {

        while (index <= 50) {

            System.out.println(Thread.currentThread()+" 的号码是:"+(index++));

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

}
