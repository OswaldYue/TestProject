package com.mgw.two.chapter6;

import java.util.Random;

public class WriterWorker extends Thread {

    private static final Random random = new Random(System.currentTimeMillis());

    private final ShareData shareData;

    private final String filler;

    private int index = 0;

    public WriterWorker(ShareData shareData,String filler) {

        this.shareData = shareData;
        this.filler = filler;
    }


    @Override
    public void run() {
        try {
            while (true) {
                char c = nextChar();
                shareData.write(c);
                Thread.sleep(random.nextInt(1_000));
            }
        }catch (InterruptedException e) {

            e.printStackTrace();
        }

    }

    private char nextChar() {

        char c = filler.charAt(index);
        index++;

        if (index >= filler.length()) {

            index=0;
        }
        return c;
    }


}
