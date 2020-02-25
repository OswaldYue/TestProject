package com.mgw.three.atomic;

import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * 解决ABA问题
 * */
public class AtomicStampedReferenceTest {

    private static AtomicStampedReference<Integer> ref = new AtomicStampedReference<>(100,0);

    public static void main(String[] args) throws InterruptedException {

        Thread t1 = new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
                boolean success = ref.compareAndSet(100,101,ref.getStamp(),ref.getStamp()+1);
                System.out.println(Thread.currentThread().getName() + " success = " + success);

                success = ref.compareAndSet(101,100,ref.getStamp(),ref.getStamp()+1);
                System.out.println(Thread.currentThread().getName() + " success = " + success);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"Thread-0");

        Thread t2 = new Thread(() -> {
            try {
                int stamp = ref.getStamp();
                System.out.println("Before sleep : stamp = " + stamp);
                TimeUnit.SECONDS.sleep(2);
                boolean success = ref.compareAndSet(100,101,stamp,stamp+1);
                System.out.println(Thread.currentThread().getName() + " success = " + success);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"Thread-2");

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        final HashMap<String, String> stringStringHashMap = new HashMap<>();
        stringStringHashMap.put("A",null);

        System.out.println(stringStringHashMap.get("A"));

        new ConcurrentHashMap<>();


    }
}
