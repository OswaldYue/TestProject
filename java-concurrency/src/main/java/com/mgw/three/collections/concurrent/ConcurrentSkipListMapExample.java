package com.mgw.three.collections.concurrent;

import java.util.concurrent.ConcurrentNavigableMap;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.function.BiFunction;

public class ConcurrentSkipListMapExample {

    public static <K,V> ConcurrentSkipListMap<K,V> create() {
        return new ConcurrentSkipListMap<K,V>();
    }

    /**
     * 1.4 round -> 1
     * 1.5 round -> 2
     *
     * 1.1 ceiling -> 2
     * 1.9 ceiling -> 2
     *
     * 1.1 floor -> 1
     * 1.9 floor -> 1
     *
     * {@link ConcurrentSkipListMap#ceilingKey(java.lang.Object)}
     * {@link ConcurrentSkipListMap#ceilingEntry(Object)}
     * 向上找
     * */
    public static void testCeiling() {
        final ConcurrentSkipListMap<Integer, String> map = create();

        map.put(1,"A");
        map.put(5,"B");
        map.put(8,"C");

        System.out.println(map.ceilingKey(2)); // 5
        System.out.println(map.ceilingEntry(4).getValue());

    }
    /**
     * {@link ConcurrentSkipListMap#floorKey(Object)}
     * {@link ConcurrentSkipListMap#floorEntry(Object)}
     * 向下找
     * */
    public static void testFloor() {
        final ConcurrentSkipListMap<Integer, String> map = create();

        map.put(1,"A");
        map.put(5,"B");
        map.put(8,"C");

        System.out.println(map.floorKey(1));
        System.out.println(map.floorEntry(2).getValue());
    }

    /**
     * {@link ConcurrentSkipListMap#firstKey()}
     * {@link ConcurrentSkipListMap#firstEntry()}
     * */
    public static void testFirst() {
        final ConcurrentSkipListMap<Integer, String> map = create();

        map.put(1,"A");
        map.put(5,"B");
        map.put(8,"C");

        System.out.println(map.firstKey());
        System.out.println(map.firstEntry().getValue());
    }

    /**
     * {@link ConcurrentSkipListMap#lastKey()}
     * {@link ConcurrentSkipListMap#lastEntry()}
     * */
    public static void testLast() {
        final ConcurrentSkipListMap<Integer, String> map = create();

        map.put(1,"A");
        map.put(5,"B");
        map.put(8,"C");

        System.out.println(map.lastKey());
        System.out.println(map.lastEntry().getValue());
    }

    /**
     * {@link ConcurrentSkipListMap#merge(Object, Object, BiFunction)}
     *
     * */
    public static void testMerge() {
        final ConcurrentSkipListMap<Integer, String> map = create();

        map.put(1,"A");
        map.put(5,"B");
        map.put(8,"C");

        // 如果此key之前没有，则新加入
        System.out.println(map.merge(9, "D", (ov, v) -> {
            return ov + v;
        }));
        System.out.println(map.get(9));

        // 如果已经存了此key,则会根据BiFunction进行替换或者删除操作
        System.out.println(map.merge(1, "E", (ov, v) -> {
            return ov + v;
        }));
        System.out.println(map.get(1));
    }

    /**
     * {@link ConcurrentSkipListMap#tailMap(Object)}
     * 大于等于某个key开始，返回新的Map
     *
     * */
    public static void testTailMap() {
        final ConcurrentSkipListMap<Integer, String> map = create();

        map.put(1, "A");
        map.put(5, "B");
        map.put(8, "C");

        final ConcurrentNavigableMap<Integer, String> tailMap = map.tailMap(5);
        tailMap.forEach((k,v) -> {
            System.out.println("k=" + k + ",v="+v);
        });
    }

    /**
     * {@link ConcurrentSkipListMap#compute(Object, BiFunction)}
     * 如果没有key，则新增k-v
     * 如果有，则使用BiFunction替换或者删除
     * */
    public static void testCompute() {
        final ConcurrentSkipListMap<Integer, String> map = create();

        map.put(1, "A");
        map.put(5, "B");
        map.put(8, "C");

        map.compute(9,(key,v) -> {
            return key+v;
        });
        System.out.println(map.get(9));

        map.compute(5,(key,v) -> {
            return key+v;
        });
        System.out.println(map.get(5));

    }


    public static void main(String[] args) {

//        testCeiling();
//        testFloor();
//        testFirst();
//        testLast();
//        testMerge();
//        testTailMap();
        testCompute();
    }

}
