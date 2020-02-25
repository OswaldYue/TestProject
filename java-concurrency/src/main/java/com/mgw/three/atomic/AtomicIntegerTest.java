package com.mgw.three.atomic;

public class AtomicIntegerTest {

    private static volatile int value = 0;

    public static void main(String[] args) throws InterruptedException {

        final Thread t1 = new Thread() {
            @Override
            public void run() {
                int x = 0;
                while (x < 500) {
                    int tem = value;
                    System.out.println(Thread.currentThread().getName() + " :" + tem);
                    value += 1;
                    x++;
                }

            }
        };

        final Thread t2 = new Thread() {
            @Override
            public void run() {
                int x = 0;
                while (x < 500) {
                    int tem = value;
                    System.out.println(Thread.currentThread().getName() + " :" + tem);
                    value += 1;
                    x++;
                }

            }
        };

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        System.out.println("Finish...");;
    }

}
