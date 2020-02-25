package com.mgw.three.utils.exchanger;

import java.util.concurrent.Exchanger;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class ExchangerExample1 {


    public static void main(String[] args) {

        Exchanger<String> exchanger = new Exchanger<>();

        new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + " start");
            try {
                // 如果一个超时结束，另一个会一直wait等待那个点的到来
                final String result = exchanger.exchange("I am come from T-A",10,TimeUnit.SECONDS);
                System.out.println(Thread.currentThread().getName() + " get value [" + result + "]");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (TimeoutException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + " end");
        },"==A==").start();

        new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + " start");
            try {
                TimeUnit.SECONDS.sleep(20);
                final String result = exchanger.exchange("I am come from T-B");
                System.out.println(Thread.currentThread().getName() + " get value [" + result + "]");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + " end");
        },"==B==").start();
    }

}
