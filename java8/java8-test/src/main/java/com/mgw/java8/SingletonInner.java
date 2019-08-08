package com.mgw.java8;

public class SingletonInner {


    private static class Hodler {

        public static SingletonInner inner = new SingletonInner();
    }

    private SingletonInner() {

    }

    //单例
    public static SingletonInner getSingletonInner() {

        return Hodler.inner;
    }

    public static void main(String[] args) {

        System.out.println(SingletonInner.getSingletonInner());
        System.out.println(SingletonInner.getSingletonInner());
        System.out.println(SingletonInner.getSingletonInner());
    }
}
