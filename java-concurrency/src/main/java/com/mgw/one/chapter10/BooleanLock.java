package com.mgw.one.chapter10;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Optional;

public class BooleanLock implements Lock {


    // intValue 值为true,则锁已经被拿走
    // intValue 值为false,则锁未被拿走
    private boolean initValue;

    private Collection<Thread> blockedThreadCollection = new ArrayList<>();

    // 标记当前线程为谁
    private Thread currentThread ;


    public BooleanLock() {

        this.initValue = false;
    }

    @Override
    public synchronized void lock() throws InterruptedException {

        // 锁已经被拿走了  则wait
        while (initValue) {
            //加入阻塞集合中
            blockedThreadCollection.add(Thread.currentThread());
            this.wait();
        }

        // 锁没有被拿走,则拿走锁，防止别的拿走
        this.initValue = true;
        this.currentThread = Thread.currentThread();
        this.blockedThreadCollection.remove(Thread.currentThread());

    }

    @Override
    public synchronized void lock(long mils) throws InterruptedException, TimeOutException {

        if (mils <= 0) {
            lock();
        }

        long hasRemaining = mils;
        long endTime = System.currentTimeMillis() + mils;

        while (initValue) {

            // 超时
            if (hasRemaining <= 0) {
                throw new TimeOutException("Time out");
            }
            blockedThreadCollection.add(Thread.currentThread());
            this.wait(mils);

            hasRemaining = endTime - System.currentTimeMillis();
        }

        this.initValue = true;
        this.currentThread = Thread.currentThread();
        this.blockedThreadCollection.remove(Thread.currentThread());

    }

    @Override
    public synchronized void unlock() {

        if (Thread.currentThread() == this.currentThread ) {
            this.initValue = false;
            Optional.of(Thread.currentThread().getName() + " relase the lock monitor.")
                    .ifPresent(System.out::println);
            this.notifyAll();
        }
    }

    @Override
    public Collection<Thread> getBlockedThread() {
        // 防止阻塞线程被修改  只可读
        return Collections.unmodifiableCollection(blockedThreadCollection);
    }

    @Override
    public int getBlockSize() {
        return blockedThreadCollection.size();
    }
}
