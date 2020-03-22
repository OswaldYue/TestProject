package com.poplar.bytecode.proxy;

import java.lang.reflect.Proxy;

public class Client {
    public static void main(String[] args) {

        // 设置sun.misc.ProxyGenerator.saveGeneratedFiles属性，让其动态生成的class写到磁盘上
        // ProxyGenerator.saveGeneratedFiles属性
        System.getProperties().setProperty("sun.misc.ProxyGenerator.saveGeneratedFiles","true");

        RealSubject realSubject = new RealSubject();
        DynamicSubject dynamicSubject = new DynamicSubject();

        Class<?> aClass = realSubject.getClass();
        Subject subject = (Subject)Proxy.newProxyInstance(aClass.getClassLoader(), aClass.getInterfaces(), dynamicSubject);

        dynamicSubject.setTarget(realSubject);

        subject.request("MMMMM");

//        System.out.println(subject.toString());

        // class com.sun.proxy.$Proxy0
        System.out.println(subject.getClass());
        // class java.lang.reflect.Proxy
        System.out.println(subject.getClass().getSuperclass());
    }
}
