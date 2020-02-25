package com.mgw.one.chapter6;

public class ThreadInterrupt2 {

    public static void main(String[] args) {


        Thread t = new Thread() {

            @Override
            public void run() {
                while (true) {

                    try {
                        Thread.sleep(10_000);

                    }catch (InterruptedException e) {
                        System.out.println(">>>>接收到打断信号");
                        e.printStackTrace();
                    }

                }
            }
        };
        t.start();

        Thread main = Thread.currentThread();
        Thread t2 = new Thread() {

            @Override
            public void run() {
               try {
                    Thread.sleep(100);
               }catch (InterruptedException e) {
                    e.printStackTrace();

               }
               // t.interrupt() 此处的中断信号，只有在t线程中捕获到，而不能在main线程中捕获到
//               t.interrupt();
               //  main.interrupt()此处的打断信号，可以在main线程中捕获到
                main.interrupt();
                System.out.println(">>interrupt");
            }
        };
        t2.start();

        try {
            t.join();
        }catch (InterruptedException e) {
            System.out.println("接收到打断信号");
            e.printStackTrace();
        }


    }
}
