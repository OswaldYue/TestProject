package com.mgw.two.chapter6;

public class ReadWriteLock {

    // 当前几个进行读的操作
    private int readingReaders = 0;

    //几个线程想读，但是读不了，放入等待队列中
    private int waitingReaders = 0;

    // 当前几个线程进行写  必须只有一个
    private int writingWriters = 0;

    // 等待写的线程的个数
    private int waitingWriters = 0;

    private boolean preferWriter = true;

    public ReadWriteLock() {
        this(true);
    }

    public ReadWriteLock(boolean preferWriter) {
        this.preferWriter = preferWriter;
    }


    public synchronized void readLock() throws InterruptedException {

        this.waitingReaders++;
        try {
            while (this.writingWriters > 0 || (preferWriter && this.waitingWriters > 0)) {
                this.wait();
            }
            this.readingReaders++;
        }finally {
            this.waitingReaders--;
        }

    }

    public synchronized  void readUnlock() {
        this.readingReaders--;
        this.notifyAll();

    }

    public synchronized  void writeLock() throws InterruptedException {

        this.waitingWriters++;
        try {
            while (readingReaders>0 || writingWriters>0) {
                this.wait();
            }
            this.writingWriters++;
        }finally {
            this.waitingWriters--;
        }
    }

    public synchronized void writeUnlock() {
        this.writingWriters--;
        this.notifyAll();
    }


}
