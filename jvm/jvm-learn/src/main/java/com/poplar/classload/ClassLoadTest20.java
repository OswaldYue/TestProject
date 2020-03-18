package com.poplar.classload;

import java.lang.reflect.Method;


public class ClassLoadTest20 {
    public static void main(String[] args) throws Exception {

        ClassLoadTest16 loader1 = new ClassLoadTest16("load1");
        ClassLoadTest16 loader2 = new ClassLoadTest16("load2");

        Class<?> clazz1 = loader1.loadClass("com.poplar.classload.MyPerson");
        Class<?> clazz2 = loader2.loadClass("com.poplar.classload.MyPerson");

        //clazz1和clazz均由应用类加载器加载的，第二次不会重新加载，结果为true
        System.out.println(clazz1 == clazz2);

        Object object1 = clazz1.newInstance();
        Object object2 = clazz2.newInstance();

        // 可以正常执行
        Method method = clazz1.getMethod("setMyPerson", Object.class);
        method.invoke(object1, object2);
    }
}

/*
class Person {

    private Person person;

    public void setPerson(Object object) {
        this.person = (Person) object;
    }
}*/
