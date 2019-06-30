package com.mgw.proxy;

import org.junit.Test;

public class TestProxy {

    @Test
    public void testProxy() {

        //创建代理对象的对象
        CreateProxy createProxy = new CreateProxy();
        Person person = new Person();

        //代理对象
        Subject proxy = (Subject)createProxy.create(person);
        //代理对象调用方法
        proxy.shopping();

    }

    public static void main(String[] args) {
        //创建代理对象的对象
        CreateProxy createProxy = new CreateProxy();
        Person person = new Person();

        //代理对象
        Subject proxy = (Subject)createProxy.create(person);
        //代理对象调用方法
        proxy.shopping();
    }

}
