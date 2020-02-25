package com.mgw.classload.chapter2;

import java.util.concurrent.atomic.AtomicBoolean;

public class ClinitThreadTest {

    public static void main(String[] args) {

        new Thread(() -> new SimpleObject()).start();
        new Thread(() -> new SimpleObject()).start();


    }

    // 虚拟机有义务保证<clinit>()方法的线程安全
    static class SimpleObject {

        private static AtomicBoolean init = new AtomicBoolean(true);

        static {
            System.out.println(Thread.currentThread().getName() + " --- I will be initial.");
            while (init.get()){

            }

            System.out.println(Thread.currentThread().getName() + " --- I am finished inital.");

        }
    }

}
