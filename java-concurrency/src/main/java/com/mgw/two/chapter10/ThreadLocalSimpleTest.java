package com.mgw.two.chapter10;

public class ThreadLocalSimpleTest {

    private  static ThreadLocal threadLocal = new ThreadLocal() {
        @Override
        protected Object initialValue() {
            return "NNNN";
        }
    };

    public static void main(String[] args) {

//        threadLocal.set("MMM");
        try {
            Thread.sleep(1_000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(threadLocal.get());
    }

}
