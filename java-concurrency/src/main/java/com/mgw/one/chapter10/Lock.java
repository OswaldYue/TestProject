package com.mgw.one.chapter10;

import java.util.Collection;

public interface Lock {

    class TimeOutException extends Exception {

        public TimeOutException(String message) {

            super(message);
        }
     }

     void lock() throws InterruptedException;

    void lock(long mils) throws InterruptedException,TimeOutException;

    void unlock();

    Collection<Thread> getBlockedThread();

    int getBlockSize();

}
