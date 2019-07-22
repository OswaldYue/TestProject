package com.mgw.ioc;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class IocTest {


	public static void test1() {

		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(IocConfig.class);

		//Exception in thread "main" org.springframework.beans.factory.NoUniqueBeanDefinitionException: No qualifying bean of type 'com.mgw.ioc.Car' available: expected single matching bean but found 2: BWM,BYD
//		Car car = context.getBean(Car.class);
//		System.out.println(car);

		BYD byd = context.getBean(BYD.class);
		System.out.println(byd);

	}


	public static void main(String[] args) {

		System.out.println("=============================================");
		test1();


	}
}
