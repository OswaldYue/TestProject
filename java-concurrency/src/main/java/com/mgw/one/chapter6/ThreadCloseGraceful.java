package com.mgw.one.chapter6;

/**
 * 优雅的结束一个线程 通过flag的方式
 * */
public class ThreadCloseGraceful {

    private static class Worker extends Thread {

        private volatile boolean start = true;

        @Override
        public void run() {

            while (start) {
                //do something

            }

            System.out.println(">>结束Worker线程");
        }

        public void shutdown() {

            this.start = false;
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

        worker.shutdown();


    }

}
