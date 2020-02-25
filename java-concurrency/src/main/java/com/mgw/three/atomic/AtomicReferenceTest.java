package com.mgw.three.atomic;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.atomic.AtomicReference;

public class AtomicReferenceTest {

    public static void main(String[] args) {

        test1();
    }

    public static void test1() {

        SimpleObject mmm = new SimpleObject("MMM", 12);
        AtomicReference<SimpleObject> reference = new AtomicReference<>(mmm);
        System.out.println(reference.get());

        boolean result = reference.compareAndSet(new SimpleObject("NNN",13),new SimpleObject("MMM",12));
        System.out.println(result); // false
        System.out.println(reference);

        result = reference.compareAndSet(mmm,new SimpleObject("NNN",13));
        System.out.println(result); // true
        System.out.println(reference);

        final JButton jButton = new JButton();

        final AtomicReference<SimpleObject> atomicReference = new AtomicReference<>(new SimpleObject("aaa",10));

        jButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 使用AtomicReference类来包装一个新类 避免自己写包装类ObjectWrap类
                atomicReference.set(new SimpleObject("bbb",11));
            }
        });

    }

    static class ObjectWrap<T> {

        private T value;

        public void setValue(T value) {
            this.value = value;
        }

        public T getValue() {
            return value;
        }
    }

    static class SimpleObject {

        private String name;

        private int age;

        public SimpleObject(String name, int age) {
            this.name = name;
            this.age = age;
        }

        public String getName() {
            return name;
        }

        public int getAge() {
            return age;
        }

        @Override
        public String toString() {
            return "SimpleObject{" +
                    "name='" + name + '\'' +
                    ", age=" + age +
                    '}';
        }
    }

}
