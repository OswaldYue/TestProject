package com.mgw.two.chapter10;

import java.util.HashMap;
import java.util.Map;

public class ThreadLocalSimulator<T> {

    private final Map<Thread,T> storage = new HashMap<>();

    public void set(T t) {
        synchronized (this) {
            storage.put(Thread.currentThread(),t);
        }
    }

    public T get() {
        synchronized (this) {
            Thread key = Thread.currentThread();
            T value = storage.get(key);
            if (null == value) {
                return initialValue();
            }
            return value;
        }
    }

    protected T initialValue() {
        return null;
    }

}
