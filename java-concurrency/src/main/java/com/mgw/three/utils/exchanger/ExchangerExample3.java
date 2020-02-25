package com.mgw.three.utils.exchanger;

import java.util.concurrent.Exchanger;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;


public class ExchangerExample3 {

    public static void main(String[] args) {

        Exchanger<Integer> exchanger = new Exchanger<Integer>();

        new Thread("A") {
            @Override
            public void run() {
                AtomicReference<Integer> value = new AtomicReference<>(1);
                try {
                    while (true) {
                        value.set(exchanger.exchange(value.get()));
                        System.out.println("Thread A has value : " + value.get());
                        TimeUnit.SECONDS.sleep(3);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }.start();

        new Thread("B") {
            @Override
            public void run() {
                AtomicReference<Integer> value = new AtomicReference<>(2);
                try {
                    while (true) {
                        value.set(exchanger.exchange(value.get()));
                        System.out.println("Thread B has value : " + value.get());
                        TimeUnit.SECONDS.sleep(2);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }
}
