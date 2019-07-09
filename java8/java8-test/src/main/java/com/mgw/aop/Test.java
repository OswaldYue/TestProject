package com.mgw.aop;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Test {





    public static void test1() {

        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationcontext.xml");

        Caculator caculator = context.getBean(Caculator.class);

        int add = caculator.add(2, 3);



        System.out.println(caculator.getClass());

    }

    public static void test2() {

        Caculator caculator = new MyCaculator();
        //使用动态代理实现aop功能
        Caculator proxy = CaculatorProxy.getProxy(caculator);

    }


    public static void main(String[] args) {


        //test1();
        System.out.println("=============================");


    }
}
