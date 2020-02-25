package com.mgw.one.chapter6;

/**
 * 强制关闭一个线程
 * */
public class ThreadCloseForce {

    public static void main(String[] args) {

        ThreadService threadService = new ThreadService();
        long start = System.currentTimeMillis();
        threadService.execte(() -> {

            // 加载一个很重的资源 非常耗时
//            while (true) {
//
//            }

            try {
                Thread.sleep(5_000);
            }catch (InterruptedException e) {
                e.printStackTrace();
            }

        });

        threadService.shutdown(10_000);
        long end = System.currentTimeMillis();

        System.out.println(end - start);
        // 强制关闭线程 jdk提供stop()方法
    }
}
