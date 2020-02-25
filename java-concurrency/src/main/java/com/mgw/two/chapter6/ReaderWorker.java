package com.mgw.two.chapter6;

public class ReaderWorker extends Thread {

    private final ShareData shareData;

    public ReaderWorker(ShareData shareData) {
        this.shareData = shareData;
    }

    @Override
    public void run() {
        try {
            while (true) {
                char[] readBuf = this.shareData.read();
                System.out.println(Thread.currentThread().getName() + " reads " + String.valueOf(readBuf));
            }
        }catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
