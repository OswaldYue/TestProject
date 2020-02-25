package com.mgw.three.utils.locks;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReadWriteLockExample {

    private static final ReentrantLock lock = new ReentrantLock(true);

    private static final ReadWriteLock readWriteLock = new ReentrantReadWriteLock();

    private static final Lock readLock = readWriteLock.readLock();

    private static final Lock writeLock = readWriteLock.writeLock();

    private final static List<Long> data = new ArrayList<>();

    public static void writeByWriteLock() {
        try {
            writeLock.lock();
            TimeUnit.SECONDS.sleep(5);
            long millis = System.currentTimeMillis();
            data.add(millis);
            System.out.println("write data : " + millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            writeLock.unlock();
        }
    }

    public static void readByReadLock() {
        try {
            readLock.lock();
            TimeUnit.SECONDS.sleep(5);
            data.forEach(System.out::println);
            System.out.println(Thread.currentThread().getName() + "============");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            readLock.unlock();
        }
    }

    public static void write() {
        try {
            lock.lock();
            data.add(System.currentTimeMillis());
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    // 读读不需要加锁 需要改进此代码
    public static void read() {
        try {
            lock.lock();
            data.forEach(System.out::println);
            TimeUnit.SECONDS.sleep(3);
            System.out.println(Thread.currentThread().getName() + "============");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) throws InterruptedException {

        Thread t1 =  new Thread(ReadWriteLockExample::readByReadLock);
        t1.start();

        TimeUnit.SECONDS.sleep(1);

        Thread t2 =  new Thread(ReadWriteLockExample::readByReadLock);
        t2.start();
    }
}
