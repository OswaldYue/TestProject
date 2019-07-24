package com.mgw.ioc;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class IocTest {


	public static void test1() {

		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(IocConfig.class);

		//Exception in thread "main" org.springframework.beans.factory.NoUniqueBeanDefinitionException: No qualifying bean of type 'com.mgw.ioc.Car' available: expected single matching bean but found 2: BWM,BYD
//		Car car = context.getBean(Car.class);
//		System.out.println(car);

		BWM bwm = context.getBean(BWM.class);
		System.out.println(bwm);
		System.out.println(bwm.getClass());

		BYD byd1 = context.getBean(BYD.class);
		System.out.println(byd1);

		BYD byd2 = context.getBean(BYD.class);
		System.out.println(byd2);


		BYD byd3 = context.getBean(BYD.class);
		System.out.println(byd3);


		BYD byd4 = context.getBean(BYD.class);
		System.out.println(byd4);

	}

	public static void test2() {

		//此测试案例的前提是Car接口只有一个BWM的实现类

		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(IocConfig.class);

		Car car = context.getBean(Car.class);
//		---car---com.mgw.ioc.BWM@2a098129
//		---car---class com.mgw.ioc.BWM
		System.out.println("---car---"+car);
		System.out.println("---car---"+car.getClass());

		Car car1 = context.getBean(BWM.class);
//		---car1---com.mgw.ioc.BWM@2a098129
//		---car1---class com.mgw.ioc.BWM
		System.out.println("---car1---"+car1);
		System.out.println("---car1---"+car1.getClass());

		BWM bwm = context.getBean(BWM.class);
//		com.mgw.ioc.BWM@2a098129
//		class com.mgw.ioc.BWM
		System.out.println(bwm);
		System.out.println(bwm.getClass());
	}

	public static void test3() {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(IocConfig.class);
	}

	public static void main(String[] args) {

//		System.out.println("=============================================");
//		test1();

//		System.out.println("=============================================");
//		test2();

		System.out.println("=============================================");
		test3();

	}
}
