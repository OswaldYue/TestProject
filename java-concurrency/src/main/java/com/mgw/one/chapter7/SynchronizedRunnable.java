package com.mgw.one.chapter7;

public class SynchronizedRunnable implements Runnable {


    private int index = 1;

    private final static int MAX = 500;

//    private final Object MONITOR = new Object();


    @Override
    public  void run() {

        while (true) {

            if (ticket()){

                break;
            }

        }
    }

    // 锁的对象时this
    private synchronized boolean ticket() {

        // index 是一个读操作，一个线程读的时候，另一个线程可能已经将index值修改，故需加锁
        // MAX 是只读数据，基本不需要管线程安全
        if (index > MAX) {
            return true;
        }

        try {
            Thread.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //写操作
        System.out.println(Thread.currentThread() + " 的号码是:" + (index++));

        return false;
    }
}
