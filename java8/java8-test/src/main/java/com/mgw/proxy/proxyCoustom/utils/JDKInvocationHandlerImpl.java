package com.mgw.proxy.proxyCoustom.utils;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class JDKInvocationHandlerImpl implements InvocationHandler {

    Object target;

    public JDKInvocationHandlerImpl(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        System.out.println("--------------------JDKInvocationHandlerImpl代理处理类----------------------");
        return method.invoke(target,args);
    }
}
