package com.mgw.three.executors;


import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

/**
 * 定时任务可以使用的季中方式:
 *  1.Timer或者TimerTask
 *      Timer在jdk中已经被淘汰了
 *  2.ScheduledExecutorService
 *  3.crontab（linux）
 *  4.cron4j（简单好用）
 *  5.quartz
 *
 *
 * Timer的问题:TimerTask任务执行超过1秒，而Timer每隔一秒调用一次，会发生什么？它不会再每隔一秒调用一次，而是等任务完成后再调用
 * */
public class TimerScheduler {

    public static void main(String[] args) {

        Timer timer = new Timer();

        final TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                System.out.println(System.currentTimeMillis());
                try {
                    TimeUnit.SECONDS.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        timer.schedule(timerTask,1000,1000);
    }
}
