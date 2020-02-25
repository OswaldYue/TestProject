package com.mgw.one.chapter5;

public class ThreadJoin3 {


    public static void main(String[] args) throws  Exception{

        long startTimeStamp = System.currentTimeMillis();

        Thread m1 = new Thread(new CaptureRunable("M1", 10_000));
        Thread m2 = new Thread(new CaptureRunable("M1", 30_000));
        Thread m3 = new Thread(new CaptureRunable("M1", 15_000));

        m1.start();
        m2.start();
        m3.start();

        m1.join();
        m2.join();
        m3.join();

        long endTimeStamp = System.currentTimeMillis();
        System.out.printf("Save date begin timestamp is:%s,end timestamp is:%s \n",startTimeStamp,endTimeStamp);
        //保存数据


    }

}

class CaptureRunable implements Runnable {

    private String machineName;

    private long spendTime;

    public CaptureRunable(String machineName,long spendTime) {

        this.machineName = machineName;
        this.spendTime = spendTime;
    }

    @Override
    public void run() {

        // do the really capture date.
        try {
            Thread.sleep(spendTime);
            System.out.printf(machineName + " completed data capture at timestamp[%s] and success.\n",System.currentTimeMillis());
        }catch (Exception e) {

        }

    }


    public String getResult() {

        return machineName + " finish.";
    }

}




