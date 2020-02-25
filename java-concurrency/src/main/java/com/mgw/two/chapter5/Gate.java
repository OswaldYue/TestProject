package com.mgw.two.chapter5;

public class Gate {

    private int counter = 0;

    private String name = "Nobaody";

    private String address = "Nowhere";

    /**
     *  如果这方法不加锁 会出现线程安全问题 Gate是同一份数据 故前面的几个赋值 必须保证同一个线程去操作
     *  临界值
      */
    public synchronized void pass(String name,String address) {
        this.counter++;
        this.name = name;
        this.address = address;

        verify();
    }

    private  void verify() {

        if (this.name.charAt(0) != this.address.charAt(0)) {

            System.out.println("******************BROKEN***************"+ toString());
        }

    }

    @Override
    public String toString() {
        return "No." + counter + ":" + name + "," + address;
    }
}
