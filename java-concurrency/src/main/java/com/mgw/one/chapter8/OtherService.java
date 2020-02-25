package com.mgw.one.chapter8;

public class OtherService {

    private Object lock = new Object();

    private DeadLock deadLock;

    public void setDeadLock(DeadLock deadLock) {

        this.deadLock = deadLock;
    }

    public void s1() {

        synchronized (lock) {
            System.out.println(">>s1");
        }

    }

    public void s2() {

        synchronized (lock) {
            System.out.println(">>s2");
            deadLock.m2();
        }

    }
}
