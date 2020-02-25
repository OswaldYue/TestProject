package com.mgw.two.chapter3;

public class VolatileTest {

    //当不加volatile关键字时，操作INIT_VALUE这个共享值时，会一直到缓存中取，按理说应该会有刷新到主内存中的机会的
    //但是此程序运行时，一次都没有去主内存中取值，主要原因在于READER线程只读不写 所以cpu一直不认为INIT_VALUE更新过了
//    private volatile static int INIT_VALUE = 0;
    private  static int INIT_VALUE = 0;

    private  static final int MAX_VALUE = 5;

    public static void main(String[] args) {


        new Thread("READER") {
            @Override
            public void run() {
                int localValue = INIT_VALUE;
                while (localValue < MAX_VALUE) {
                    if (localValue != INIT_VALUE) {
                        System.out.printf("The Value updated to [%d] \n",INIT_VALUE);
                        localValue = INIT_VALUE;
                    }
                }
            }
        }.start();

        new Thread("UPDATED") {
            @Override
            public void run() {
                int localValue = INIT_VALUE;
                while (INIT_VALUE < MAX_VALUE) {
                    System.out.printf("Update the value to [%d] \n",(++localValue));
                    INIT_VALUE = localValue;

                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();

    }

}
