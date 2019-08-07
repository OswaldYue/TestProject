package com.mgw.proxy.proxyCoustom.utils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ProxyInvocationHandlerImpl implements ProxyInvocationHandler {

    Object target;
    public ProxyInvocationHandlerImpl(Object target) {

        this.target = target;
    }

    @Override
    public Object invoke(Method method) {

        System.out.println("-----------------------------------oooooooooo----------------------");
        try {
            method.invoke(target);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return null;
    }
}
