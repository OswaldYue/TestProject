package com.mgw.two.chapter1;

/**
 * 懒汉式
 * 线程安全的
 * 加锁会将getInstance() 函数进行串行化
 * */
public class SingletonObject3 {

    private static SingletonObject3 instance ;

    // 私有构造方法
    private SingletonObject3() {


    }

    public synchronized static SingletonObject3 getInstance() {

        if (null == instance)
            instance = new SingletonObject3();

        return SingletonObject3.instance;
    }
}
