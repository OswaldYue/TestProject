package com.mgw.two.chapter8;

/**
 * 同步调用
 * */
public class SyncInvoker {

    public static void main(String[] args) throws InterruptedException {

        String result = get();
        System.out.println(result);

    }


    public static String get() throws InterruptedException {

        // 模拟工作了很久的时间
        Thread.sleep(10_000);

        return "FINISH";

    }

}
