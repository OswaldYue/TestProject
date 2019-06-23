package com.mgw.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 *
 * 动态生成一个代理对象
 * */
public class CreateProxy implements InvocationHandler {

    //被代理的对象
    private Object target;

    public Object create(Object target) {

        this.target = target;

        Object proxy = Proxy.newProxyInstance(target.getClass().getClassLoader(),
                target.getClass().getInterfaces(), this);

        return proxy;
    }

    /**
     * 代理对象执行的方法
     *
     * @param proxy 代理对象
     * @param method 被代理对象的方法
     * @param args 被代理对象方法的参数
     *
     * @return
     * */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        System.out.println("去海外寻找客户需要的商品");
        System.out.println("与客户确认物品");

        method.invoke(target,args);

        System.out.println("完成海淘订单");

        return null;
    }

}
