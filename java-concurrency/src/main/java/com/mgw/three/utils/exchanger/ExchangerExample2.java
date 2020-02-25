package com.mgw.three.utils.exchanger;

import java.util.concurrent.Exchanger;

/**
 * 交换的数据不是一种拷贝而是同一个对象 所以会有线程安全问题
 * */
public class ExchangerExample2 {

    public static void main(String[] args) {

        Exchanger<Object> exchanger = new Exchanger<>();

        new Thread("A") {
            @Override
            public void run() {
                final Object o = new Object();
                System.out.println("A will send the object "+o);
                try {
                    final Object result = exchanger.exchange(o);
                    System.out.println("A recieved the obj : " + result);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }.start();

        new Thread("B") {
            @Override
            public void run() {
                final Object o = new Object();
                System.out.println("B will send the object "+o);
                try {
                    final Object result = exchanger.exchange(o);
                    System.out.println("B recieved the obj : " + result);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }

}
