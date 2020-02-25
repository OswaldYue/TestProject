package com.mgw.three.atomic;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * 这个程序如果init变量不加volatile，则线程Thread-0不会结束，原因就是可见性引起的
 * 但是这个程序还有几个问题造成的
 * 1.启动后cpu优先执行了Thread-1线程，而后执行了Thread-0线程
 * 2.如果init变量不加volatile，在Thread-0 中加 System.out.println("..") 则Thread-0会结束
 * 原因:JIT进行编译时，如果init不加volatile，如果while函数体中没有语句，则编译时直接将while (!init){} 优化编译为while (true){}
 * 但是如果while函数体中有语句，就不会优化编译为while(true){}
 *
 * 解决方案:
 * 1.加volatile关键字
 * 2.使用原子类型AtomicBoolean
 *
 * */
public class JITTest {

    private static  boolean init = false;

    public static void main(String[] args) throws InterruptedException {

        new Thread("Thread-0") {
            @Override
            public void run() {
                while (!init) {
//                    System.out.println("..");
                }
            }
        }.start();

        Thread.sleep(1_000);

        new Thread("Thread-1") {
            @Override
            public void run() {
                init = true;
                System.out.println(Thread.currentThread().getName() + " 结束");
            }
        }.start();

    }
}
