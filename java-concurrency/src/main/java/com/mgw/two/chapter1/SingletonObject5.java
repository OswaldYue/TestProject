package com.mgw.two.chapter1;

/**
 * 懒汉式
 *
 * double-check  没有有隐患
 * */
public class SingletonObject5 {

    private static volatile SingletonObject5 instance ;

    // 私有构造方法
    private SingletonObject5() {


    }

    public  static SingletonObject5 getInstance() {

        if (null == instance) {
            synchronized (SingletonObject5.class) {
                if (null == instance) {
                    instance = new SingletonObject5();
                }
            }
        }

        return SingletonObject5.instance;
    }
}
