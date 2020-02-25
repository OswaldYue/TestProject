package com.mgw.two.chapter1;

/**
 * 懒汉式
 * 线程不是安全的
 * */
public class SingletonObject2 {

    private static  SingletonObject2 instance ;

    // 私有构造方法
    private SingletonObject2() {


    }

    public static SingletonObject2 getInstance() {

        if (null == instance)
            instance = new SingletonObject2();

        return SingletonObject2.instance;
    }
}
