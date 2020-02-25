package com.mgw.one.chapter6;

public class ThreadInterrupt {

    private static final Object MONITOR = new Object();

    public static void main(String[] args) throws  Exception{


        Thread t = new Thread() {

            @Override
            public void run() {
                while (true) {
//                    System.out.println(">>" + this.isInterrupted());  // >>true


                    try {
                        Thread.sleep(10);

                    }catch (InterruptedException e) {
                        System.out.println("收到打断信号");
                        System.out.println(">>是否中断 : " + isInterrupted());
                        e.printStackTrace();
                    }


//                    synchronized (MONITOR) {
//                        try {
//                            MONITOR.wait(10);
//                        } catch (InterruptedException e) {
//                            e.printStackTrace();
//                        }
//                    }
                }
            }
        };

        t.start();
        Thread.sleep(100);

        System.out.println(t.isInterrupted());
        t.interrupt();
        //可能为true也可能为false 若中断异常优先被捕获，则将中断清除，此时为false
        //                      若中断异常未被捕获就执行到此句代码，则此时依然处于中断状态为true
        System.out.println(t.isInterrupted());

//        t.stop();
    }


}
