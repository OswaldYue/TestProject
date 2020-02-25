package com.mgw.two.chapter1;

/**
 *  枚举的形式
 *
 * 可以保证线程安全，效率
 * */
public class   SingletonObject7 {

    // 私有构造方法
    private SingletonObject7() {
    }

    public  static SingletonObject7 getInstance() {

        return Singleton.INSTANCE.instance;
    }


    private enum Singleton {
        INSTANCE;

        private final SingletonObject7 instance;

        Singleton() {
            instance = new SingletonObject7();
        }
    }

}
