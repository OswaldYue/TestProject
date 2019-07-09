package com.mgw.aop;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class CaculatorProxy {



    public static Caculator getProxy(Caculator caculator) {

        ClassLoader loader = caculator.getClass().getClassLoader();
        Class<?>[] interfaces = caculator.getClass().getInterfaces();

//        Object instance = Proxy.newProxyInstance(caculator.getClass().getClassLoader(), caculator.getClass().getInterfaces(), ((proxy, method, args) -> {
//
//
//            Object result = method.invoke(args);
//
//            return result;
//
//        }));

        Object proxyInstance = Proxy.newProxyInstance(loader, interfaces, new InvocationHandler() {

            /*
            * proxy 给jdk使用的
            * */
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

                Object result = null;

                try {
                    LogUtils.logStart(method,args);
                    result = method.invoke(caculator, args);
                    LogUtils.logReturn(method,result);
                }catch (Exception e) {

                    LogUtils.logException(method,e);

                }finally {
                    LogUtils.logAfter(method);
                }
                return result;
            }
        });

        return (Caculator) proxyInstance;
    }

}
