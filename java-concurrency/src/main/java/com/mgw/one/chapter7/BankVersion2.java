package com.mgw.one.chapter7;

public class BankVersion2 {

    public static void main(String[] args) {

        final TicketWindowRunnable ticketWindowRunnable = new TicketWindowRunnable();


        Thread t1 = new Thread(ticketWindowRunnable, "1号窗口");
        Thread t2 = new Thread(ticketWindowRunnable, "2号窗口");
        Thread t3 = new Thread(ticketWindowRunnable, "3号窗口");

        t1.start();
        t2.start();
        t3.start();
    }
}
