package com.mgw.aop;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class AopTest {





    public static void test1() {

		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AopConfig.class);

		Caculator caculator = context.getBean(Caculator.class);

        int add = caculator.add(2, 3);

        System.out.println(caculator.getClass());

    }



    public static void main(String[] args) {
		System.out.println("=============================");
        test1();

    }
}
