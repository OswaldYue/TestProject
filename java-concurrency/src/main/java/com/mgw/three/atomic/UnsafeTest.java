package com.mgw.three.atomic;

import sun.misc.Unsafe;

import java.lang.reflect.Field;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class UnsafeTest {


    public static void main(String[] args) throws InterruptedException, NoSuchFieldException {

        // Unsafe不能这么直接拿到
        // Exception in thread "main" java.lang.SecurityException: Unsafe
//        final Unsafe unsafe = Unsafe.getUnsafe();
//        System.out.println(unsafe);
//        Unsafe unsafe = getUnsafe();
//        System.out.println(unsafe);

        ExecutorService service = Executors.newFixedThreadPool(1000);
        Counter counter = new CASCounter();
        long start = System.currentTimeMillis();
        for(int i = 0 ;i < 1000; i++) {
            service.submit(new CounterRunnable(counter,10000));
        }
        service.shutdown();
        service.awaitTermination(1, TimeUnit.HOURS);
        long end = System.currentTimeMillis();
        /*
        * StupidCounter:
        *   Counter result : 9938103
        *   Time passed in ms : 456
        *
        * SyncCounter:
        *   Counter result : 10000000
        *   Time passed in ms : 977
        *
        * LockCounter:
        *   Counter result : 10000000
        *   Time passed in ms : 911
        *
        * AtomicCounter:
        *   Counter result : 10000000
        *   Time passed in ms : 1223
        *
        * CASCounter:
        *   Counter result : 10000000
        *   Time passed in ms : 1160
        *
        * */
        System.out.println("Counter result : " + counter.getCounter());
        System.out.println("Time passed in ms : " + (end - start));
    }

    /**
     * 使用反射拿到Unsafe
     * */
    private static Unsafe getUnsafe() {
         Unsafe unsafe = null;
        try {
            Field f = Unsafe.class.getDeclaredField("theUnsafe");
            f.setAccessible(true);
            unsafe =  (Unsafe) f.get(null);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return unsafe;
    }

    interface Counter {
        void increment();
        long getCounter();
    }

    /**
     * 线程不安全的Counter
     * */
    static class StupidCounter implements Counter {

        private long counter = 0;

        @Override
        public void increment() {
            counter++;
        }

        @Override
        public long getCounter() {
            return counter;
        }
    }

    static class SyncCounter implements Counter {

        private long counter = 0;

        @Override
        public synchronized void increment() {
            counter++;
        }

        @Override
        public long getCounter() {
            return counter;
        }
    }

    static class LockCounter implements Counter {

        private long counter = 0;

        private final Lock lock = new ReentrantLock();

        @Override
        public void increment() {
            try {
                lock.lock();
                counter++;
            }finally {
                lock.unlock();
            }

        }

        @Override
        public long getCounter() {
            return counter;
        }
    }

    static class AtomicCounter implements Counter {

        private final AtomicLong counter = new AtomicLong();

        @Override
        public void increment() {
            counter.incrementAndGet();
        }

        @Override
        public long getCounter() {
            return counter.get();
        }
    }

    static class CASCounter implements Counter {

        private volatile long counter = 0;
        private Unsafe unsafe;
        private long offset;

        CASCounter() throws NoSuchFieldException {
            unsafe = getUnsafe();
            offset = unsafe.objectFieldOffset(CASCounter.class.getDeclaredField("counter"));
        }

        @Override
        public void increment() {
            long current = counter;
            while (!unsafe.compareAndSwapLong(this,offset,current,current+1)) {
                current = counter;
            }

        }

        @Override
        public long getCounter() {
            return counter;
        }
    }

    static class CounterRunnable implements Runnable {

        private final Counter counter;

        private final int num;

        CounterRunnable(Counter counter, int num) {
            this.counter = counter;
            this.num = num;
        }
        
        @Override
        public void run() {
            for (int i = 0; i < num; i++) {
                counter.increment();
            }
        }
    }
}
