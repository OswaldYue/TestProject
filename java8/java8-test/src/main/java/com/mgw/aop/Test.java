package com.mgw.aop;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Test {




    @org.junit.Test
    public void test1() {

        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationcontext.xml");

        Caculator caculator = context.getBean(Caculator.class);

        System.out.println(caculator.getClass());

    }


    public static void main(String[] args) {

        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationcontext.xml");

        Caculator caculator = context.getBean(Caculator.class);

        caculator.add(1,1);

        System.out.println(caculator.getClass());
    }
}
