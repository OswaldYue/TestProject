package com.mgw.three.collections.concurrent;

import java.util.Collections;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class JDKMapPerformance {

    private static void pressureTest(final Map<String,Integer> map,int threshold,boolean retrieve) throws InterruptedException {
        System.out.println("Start pressure testing the map [" + map.getClass() + "] use the threshold ["+threshold+"],retrieve="+retrieve);
        long totalTime = 0L;
        final int MAX_THRESHOLD = 500000;
        for (int i = 0;i<5;i++) {
            long startTime = System.nanoTime();
            final ExecutorService executorService = Executors.newFixedThreadPool(threshold);
            for (int j=0;j<threshold;j++) {
                executorService.execute(new Runnable() {
                    @Override
                    public void run() {
                        for (int i=0;i<MAX_THRESHOLD;i++) {
                            Integer randomNumber = (int)Math.ceil(Math.random()*600000);
                            if (retrieve)
                                map.get(String.valueOf(randomNumber));
                            map.put(String.valueOf(randomNumber),randomNumber);
                        }
                    }
                });
            }
            executorService.shutdown();
            executorService.awaitTermination(2, TimeUnit.HOURS);
            long endTime = System.nanoTime();
            long period = (endTime-startTime)/1000000L;

            System.out.println(threshold*MAX_THRESHOLD + " element insert/retrieved in " + period + " ms");
            totalTime+=period;
        }
        System.out.println("For the map ["+map.getClass()+"] the average time is " + (totalTime/5) + " ms" );
    }

    public static void main(String[] args) throws InterruptedException {

        pressureTest(new Hashtable<String, Integer>(),5,false);
        pressureTest(new Hashtable<String, Integer>(),5,true);

        pressureTest(Collections.synchronizedMap(new HashMap<String,Integer>()),5,false);
        pressureTest(Collections.synchronizedMap(new HashMap<String,Integer>()),5,true);

        pressureTest(new ConcurrentHashMap<String, Integer>(),5,false);
        pressureTest(new ConcurrentHashMap<String, Integer>(),5,true);

        System.out.println("=======================================");

        pressureTest(new Hashtable<String, Integer>(),1,false);
        pressureTest(new Hashtable<String, Integer>(),1,true);

        pressureTest(Collections.synchronizedMap(new HashMap<String,Integer>()),1,false);
        pressureTest(Collections.synchronizedMap(new HashMap<String,Integer>()),1,true);

        pressureTest(new ConcurrentHashMap<String, Integer>(),1,false);
        pressureTest(new ConcurrentHashMap<String, Integer>(),1,true);

        pressureTest(new HashMap<String, Integer>(),1,false);
        pressureTest(new HashMap<String, Integer>(),1,true);
    }
}
