package com.mgw.two.chapter16;

import java.util.Random;

public class CounterIncrement extends Thread{

    private volatile boolean terminated = false;

    private int counter = 0;

    private final static Random random = new Random(System.currentTimeMillis());

    @Override
    public void run() {

        try {
            while (!terminated) {
                System.out.println(Thread.currentThread().getName() + " " + counter++);
                Thread.sleep(random.nextInt(1000));
            }
        }catch (InterruptedException e) {
//            e.printStackTrace();
        }finally {
            // 线程结束 做线程清理工作
            this.clean();
        }

    }

    private void clean() {

        System.out.println("do some clean work for the second phase,current counter " + counter);



    }


    public void close() {

        this.terminated = true;
        this.interrupt();

    }

}
