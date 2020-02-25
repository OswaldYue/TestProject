package com.mgw.two.chapter1;

/**
 * 懒汉式
 *
 * double-check  但是有隐患 可能引起空指针异常
 * 原因:
 *  假如这个类中有很多的引用，当一个线程抢到锁后，new 一个实例，并在堆中开辟内存，
 *  并执行构造函数，此时若做运行时优化，很可能一些引用变量没有初始化完毕，
 *  然后被另一个线程将实例拿去使用，并用到了里面的引用变量，此时就会有空指针异常
 *
 *  编译时优化
 *  运行时优化
 * */
public class SingletonObject4 {

    private static SingletonObject4 instance ;

    // 私有构造方法
    private SingletonObject4() {


    }

    public  static SingletonObject4 getInstance() {

        if (null == instance) {
            synchronized (SingletonObject4.class) {
                if (null == instance) {
                    instance = new SingletonObject4();
                }
            }
        }

        return SingletonObject4.instance;
    }
}
