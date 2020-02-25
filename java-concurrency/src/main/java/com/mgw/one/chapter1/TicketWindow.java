package com.mgw.one.chapter1;

public class TicketWindow extends Thread {

    // 加上static共享资源
    private static final int MAX = 50;
    // 加上static共享资源
    private static int index = 1;

    //柜台
    private final String name;

    public TicketWindow(String name) {

        this.name = name;
    }

    @Override
    public void run() {

        while (index <= MAX) {

            System.out.println("柜台: "+name+" 当前的号码是："+index++);
        }
    }
}
