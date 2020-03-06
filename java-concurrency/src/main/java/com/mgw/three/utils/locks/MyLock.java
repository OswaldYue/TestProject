package com.mgw.three.utils.locks;

import sun.misc.Unsafe;

import java.lang.reflect.Field;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;

public class MyLock {

    private static volatile int status = 0;

    private final Unsafe unsafe;

    private final long stateOffset;

    public MyLock() {
        Unsafe unsafe = null;
        long stateOffset=0;
        try {
            Field f = Unsafe.class.getDeclaredField("theUnsafe");
            f.setAccessible(true);
            unsafe =  (Unsafe) f.get(null);
            stateOffset = unsafe.objectFieldOffset
                    (MyLock.class.getDeclaredField("status"));

        } catch (Exception e) {
            e.printStackTrace();
        }

        this.unsafe = unsafe;
        this.stateOffset = stateOffset;
    }

    public void lock() {

        while (!unsafe.compareAndSwapInt(this,stateOffset,0,1)) {
            Thread.yield();
        }
    }

    public void unLock() {
        while (!unsafe.compareAndSwapInt(this,stateOffset,1,0)) {

        }
    }
}
