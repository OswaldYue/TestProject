package com.mgw.one.chapter3;

public class CreateThread {


    public static void main(String[] args) {

        Thread thread1 = new Thread();
        Thread thread2 = new Thread();

        thread1.start();
        thread2.start();

        System.out.println(thread1.getName());
        System.out.println(thread2.getName());

    }

}
