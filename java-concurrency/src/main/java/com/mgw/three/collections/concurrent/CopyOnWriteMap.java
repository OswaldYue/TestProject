package com.mgw.three.collections.concurrent;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 自实现CopyOnWriteMap
 * */
public class CopyOnWriteMap<K,V> implements Map<K,V> {

    private volatile Map<K,V> map;

    private ReentrantLock lock = new ReentrantLock();

    public CopyOnWriteMap() {
        this.map = new HashMap<K,V>();
    }


    @Override
    public int size() {
        return map.size();
    }

    @Override
    public boolean isEmpty() {
        return map.isEmpty();
    }

    @Override
    public boolean containsKey(Object key) {
        return map.containsKey(key);
    }

    @Override
    public boolean containsValue(Object value) {
        return map.containsValue(value);
    }

    @Override
    public V get(Object key) {
        return map.get(key);
    }

    @Override
    public V put(K key, V value) {
        try {
            lock.lock();
            Map<K,V> newMap = new HashMap<K,V>(map);
            final V v = newMap.put(key, value);
            this.map = newMap;
            return v;

        }finally {
            lock.unlock();
        }
    }

    @Override
    public V remove(Object key) {
        try {
            lock.lock();
            Map<K,V> newMap = new HashMap<K,V>(map);
            final V v = newMap.remove(key);
            this.map = newMap;
            return v;

        }finally {
            lock.unlock();
        }
    }

    @Override
    public void putAll(Map<? extends K, ? extends V> m) {
        try {
            lock.lock();
            Map<K,V> newMap = new HashMap<K,V>(map);
            newMap.putAll(m);
            this.map = newMap;
        }finally {
            lock.unlock();
        }
    }

    @Override
    public void clear() {
        try {
            lock.lock();
            map = new HashMap<K,V>();
        }finally {
            lock.unlock();
        }

    }

    @Override
    public Set<K> keySet() {
        return map.keySet();
    }

    @Override
    public Collection<V> values() {
        return map.values();
    }

    @Override
    public Set<Entry<K, V>> entrySet() {
        return map.entrySet();
    }
}
