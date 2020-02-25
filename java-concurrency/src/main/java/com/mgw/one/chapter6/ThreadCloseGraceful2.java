package com.mgw.one.chapter6;

/**
 *  优雅的结束线程，使用打断实现
 * */
public class ThreadCloseGraceful2 {

    private static class Worker extends Thread {

        @Override
        public void run() {

            while (true) {
                //do something
//                try {
//                    Thread.sleep(1);
//                }catch (InterruptedException e) {
//                    break;
//                }
                if (Thread.interrupted()) {
                    break;
                }
            }

            System.out.println(">>结束Worker线程");
        }

    }

    public static void main(String[] args) {

        Worker worker = new Worker();
        worker.start();

        try {
            Thread.sleep(10_000);
        }catch (InterruptedException e) {
            e.printStackTrace();
        }
        // 中断
        worker.interrupt();
    }
}
