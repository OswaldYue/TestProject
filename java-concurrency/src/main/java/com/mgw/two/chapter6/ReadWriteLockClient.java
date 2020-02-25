package com.mgw.two.chapter6;

/**
 *
 * */
public class ReadWriteLockClient {

    public static void main(String[] args) {

        final ShareData shareData = new ShareData(10);

        new ReaderWorker(shareData).start();
        new ReaderWorker(shareData).start();
        new ReaderWorker(shareData).start();
        new ReaderWorker(shareData).start();
        new ReaderWorker(shareData).start();

        new WriterWorker(shareData,"qeweweqweqweqdasd").start();
        new WriterWorker(shareData,"QEWEWEQWEQWEQDASD").start();
    }

}
