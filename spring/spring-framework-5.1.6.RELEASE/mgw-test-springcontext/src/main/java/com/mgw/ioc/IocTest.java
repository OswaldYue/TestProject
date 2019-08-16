package com.mgw.ioc;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;


public class IocTest {


	public static void test1() {

		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(IocConfig.class);
//		context.register(null);

		//Exception in thread "main" org.springframework.beans.factory.NoUniqueBeanDefinitionException: No qualifying bean of type 'com.mgw.ioc.Car' available: expected single matching bean but found 2: BWM,BYD
//		Car car = context.getBean(Car.class);
//		System.out.println(car);

		BWM bwm = context.getBean(BWM.class);
		System.out.println(bwm);
		System.out.println(bwm.getClass());

		//@Lazy这个注解  bean不会在加载容器时实例化,而是在需要用到时实例化,且只实例化一次
		/*
		*
		---lazy BYD---
		com.mgw.ioc.BYD@27abe2cd
		com.mgw.ioc.BYD@27abe2cd
		com.mgw.ioc.BYD@27abe2cd
		com.mgw.ioc.BYD@27abe2cd
		* */
		BYD byd1 = context.getBean(BYD.class);
		System.out.println(byd1);

		BYD byd2 = context.getBean(BYD.class);
		System.out.println(byd2);


		BYD byd3 = context.getBean(BYD.class);
		System.out.println(byd3);


		BYD byd4 = context.getBean(BYD.class);
		System.out.println(byd4);

	}

	/**
	 * 证明无论是接口类型拿bean,还是实现类类型拿bean,
	 * 实例化的bean其实是实现类的bean
	 * */
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

	/**
	 * 证明配置类是代理类
	 * */
	public static void test3() {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(IocConfig.class);

		IocConfig iocConfig = context.getBean(IocConfig.class);

//		com.mgw.ioc.IocConfig$$EnhancerBySpringCGLIB$$a6b74037@29444d75
//		class com.mgw.ioc.IocConfig$$EnhancerBySpringCGLIB$$a6b74037
		System.out.println(iocConfig);
		System.out.println(iocConfig.getClass());

	}

	/**
	 * spring的循环依赖 CircularReference1 CircularReference2
	 * spring可以解决循环依赖,但是必须其中一个类是单例
	 * */
	public static void test4() {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(IocConfig.class);
		AA aa = context.getBean(AA.class);


//		System.out.println(aa.getAutowireService1());

	}

	/**
	 * spring的注入方式 AA AutowireService1 AutowireService2 AutowireService3
	 *
	 * */
	public static void test5() {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(IocConfig.class);
		AA aa = context.getBean(AA.class);

//		System.out.println(aa.getAutowireService1());

	}

	/**
	 * 测试AnnotationConfigApplicationContext.scan() 扫描
	 * */
	public static void test6() {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
		context.scan("com.mgw.ioc");
		context.refresh();
	}

	public static void main(String[] args) {

//		System.out.println("=============================================");
//		test1();

//		System.out.println("=============================================");
//		test2();

//		System.out.println("=============================================");
//		test3();

//		System.out.println("=============================================");
//		test4();

		System.out.println("=============================================");
		test5();

//		System.out.println("=============================================");
//		test6();

	}
}
