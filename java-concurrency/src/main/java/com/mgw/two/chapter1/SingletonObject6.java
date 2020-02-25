package com.mgw.two.chapter1;

/**
 *
 *
 * 内部类形式
 * 可以保证线程安全，效率
 * */
public class SingletonObject6 {

    // 私有构造方法
    private SingletonObject6() {
    }

    public  static SingletonObject6 getInstance() {

        return InstanceHodler.instance;
    }

    public static class InstanceHodler {
        public static final SingletonObject6 instance = new SingletonObject6();
    }

}
