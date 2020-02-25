package com.mgw.two.chapter1;

/**
 * 饿汉式
 * 多线程情况下安全
 * 但是加载此类时会立即创建实例，导致占用内存
 * */
public class SingletonObject1 {

    private static final SingletonObject1 instance = new SingletonObject1();

    // 私有构造方法
    private SingletonObject1() {


    }

    public static SingletonObject1 getInstance() {

        return SingletonObject1.instance;
    }
}
