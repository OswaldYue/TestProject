package com.poplar.bytecode.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class DynamicSubject implements InvocationHandler {

    private Object target;

    public DynamicSubject() {

    }

    public DynamicSubject(Object target) {
        this.target = target;
    }

    public void setTarget(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        System.out.println("before invoke : " + target.getClass());

        method.invoke(target,args);

        System.out.println("after invoke : " + target.getClass());

        return null;
    }
}
