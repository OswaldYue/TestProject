package com.mgw.one.chapter1;

public class Bank {


    public static void main(String[] args) {

        TicketWindow ticketWindow1 = new TicketWindow("一号");
        ticketWindow1.start();

        TicketWindow ticketWindow2 = new TicketWindow("二号");
        ticketWindow2.start();

        TicketWindow ticketWindow3 = new TicketWindow("三号");
        ticketWindow3.start();
    }
}
