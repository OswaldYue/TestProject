package com.mgw.one.chapter3;

import java.util.Arrays;

public class CreateThread2 {

    public static  void main(String[] args) {

        Thread t = new Thread(() -> {
            try {
                Thread.sleep(1000 * 5);
            }catch (Exception e) {

            }

        });
        t.start();

        // java.lang.ThreadGroup[name=main,maxpri=10]
        System.out.println(t.getThreadGroup());
        // main
        System.out.println(Thread.currentThread().getName());
        // java.lang.ThreadGroup[name=main,maxpri=10]
        System.out.println(Thread.currentThread().getThreadGroup());
        ThreadGroup threadGroup = Thread.currentThread().getThreadGroup();
        System.out.println(threadGroup.activeCount());
        Thread[] lists = new Thread[threadGroup.activeCount()];
        threadGroup.enumerate(lists);
        // Thread[main,5,main]
        // Thread[Monitor Ctrl-Break,5,main]
        // Thread[Thread-0,5,]
        Arrays.asList(lists).forEach(System.out::println);

//        for (Thread list : lists) {
//            System.out.println(list);
//        }


    }

}
