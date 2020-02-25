package com.mgw.one.chapter1;

public class BankVerson2 {

    public static void main(String[] args) {

//        final TicketWindowRunnable ticketWindow = new TicketWindowRunnable();

        final Runnable ticketWindow = () -> {
            int index = 1;
            while (index <= 50) {

                System.out.println(Thread.currentThread()+" 的号码是:"+(index++));

            }

        };

        Thread window1 = new Thread(ticketWindow, "一号窗口");
        Thread window2 = new Thread(ticketWindow, "二号窗口");
        Thread window3 = new Thread(ticketWindow, "三号窗口");

        window1.start();
        window2.start();
        window3.start();

    }
}
