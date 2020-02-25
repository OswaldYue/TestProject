package com.mgw.two.chapter8;

public interface Future<T> {

    T get() throws InterruptedException;



}
