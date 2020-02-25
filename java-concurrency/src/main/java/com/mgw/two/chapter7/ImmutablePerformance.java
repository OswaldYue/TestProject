package com.mgw.two.chapter7;

public class ImmutablePerformance {

    public static void test1() {

        long startTimestamp = System.currentTimeMillis();
//        SyncObj syncObj = new SyncObj();
//        syncObj.setName("Alex");

        ImmutableObj immutableObj = new ImmutableObj("Alex");

        for (long l = 0;l < 100_0000;l++) {
//            System.out.println(syncObj.toString()); // 9556
            System.out.println(immutableObj.toString()); //10615
        }

        long endTimestamp = System.currentTimeMillis();

        System.out.println("Elapsed time " + (endTimestamp - startTimestamp));
    }

    public static void test2() throws InterruptedException {

        long startTimestamp = System.currentTimeMillis();

//        ImmutableObj obj = new ImmutableObj("Alex");

        SyncObj obj = new SyncObj();
        obj.setName("Alex");

        Thread t1 = new Thread() {
            @Override
            public void run() {


                for (long l = 0; l < 100_0000; l++) {
                    System.out.println(Thread.currentThread().getName() + " >> " + obj.toString());

                }



            }
        };

        Thread t2 = new Thread() {
            @Override
            public void run() {


                for (long l = 0; l < 100_0000; l++) {
                    System.out.println(Thread.currentThread().getName() + " >> " + obj.toString());

                }



            }
        };

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        long endTimestamp = System.currentTimeMillis();
        System.out.println("Elapsed time " + (endTimestamp - startTimestamp));


    }

    public static void main(String[] args) throws InterruptedException {

//        test1();
        test2();
    }

}

final class ImmutableObj {

    private final String name ;

    ImmutableObj(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "ImmutableObj{" +
                "name='" + name + '\'' +
                '}';
    }


}

class SyncObj {

    private String name;

    public synchronized void setName(String name) {
        this.name = name;
    }

    @Override
    public synchronized String toString() {
        return "SyncObj{" +
                "name='" + name + '\'' +
                '}';
    }
}
