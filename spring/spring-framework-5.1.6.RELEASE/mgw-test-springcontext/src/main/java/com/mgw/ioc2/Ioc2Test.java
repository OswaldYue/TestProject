package com.mgw.ioc2;

import com.mgw.ioc2.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Ioc2Test {

	/**
	 * 配置的形式进行spring依赖
	 *
	 * */
	public static void test1() {

		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");

		UserService userService = context.getBean(UserService.class);
		userService.test();

	}

	/**
	 * 使用配置类进行依赖
	 * */
	public static void test2() {

		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Ioc2Config.class);

		System.out.println(context);

	}

	public static void main(String[] args) {

//		System.out.println("------------------------------------------------------");
//		test1();

		System.out.println("------------------------------------------------------");
		test2();

	}
}
