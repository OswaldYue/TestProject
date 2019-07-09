package com.mgw.aop;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class CaculatorProxy {



    public static Caculator getProxy(Caculator caculator) {

        ClassLoader loader = caculator.getClass().getClassLoader();
        Class<?>[] interfaces = caculator.getClass().getInterfaces();

        Object proxyInstance = Proxy.newProxyInstance(loader, interfaces, new InvocationHandler() {

            /*
            * proxy 给jdk使用的
            * */
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

                Object invoke = method.invoke(caculator, args);

                return invoke;
            }
        });

        return (Caculator) proxyInstance;
    }

}
