package com.mgw.three.atomic;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 使用无锁自定义锁
 * 无锁
 * */
public class CompareAndSetLock {

    private final AtomicInteger value = new AtomicInteger(0);

    private Thread lockedThread;

    private static final CompareAndSetLock compareAndSetLock = new CompareAndSetLock();

    // 尝试拿锁，没拿到就抛出异常
    public void tryLock() throws GetLockException {

        boolean success = value.compareAndSet(0, 1);
        if (!success) {
            throw new GetLockException("Get the lock failed.");
        }else {
            lockedThread = Thread.currentThread();
        }
    }

    public void lock() {
        for (;;) {
            final boolean success = value.compareAndSet(0, 1);
            if (success)
                break;
        }
    }

    public void unLock() {
        // 已经释放
        if (0 == value.get()) {
            return;
        }
        if (lockedThread == Thread.currentThread())
            value.compareAndSet(1, 0);
    }

    private static void doSomething() throws InterruptedException, GetLockException {

        try {
            compareAndSetLock.tryLock();
            System.out.println(Thread.currentThread().getName() + " get the lock.");
            Thread.sleep(100_000);
        }
        finally {
            compareAndSetLock.unLock();
        }



    }

    public static void main(String[] args) {

        for (int i = 0 ;i < 5 ;i++) {

            new Thread(()->{
                try {
                    doSomething();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (GetLockException e) {
                    e.printStackTrace();
                }
            }).start();
        }

    }

}

