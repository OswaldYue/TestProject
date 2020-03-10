package com.mgw.aopprepare.aspectjaop;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Test {

	public static void test1() {
		// 基于@AspectJ注解方式
		ApplicationContext ctx = new ClassPathXmlApplicationContext("aspectJaop.xml");
		Dog dog = ctx.getBean("dog", Dog.class);
		dog.sayHello();
	}


	public static void test2() {
		// 引入
		ApplicationContext ctx = new ClassPathXmlApplicationContext("aspectJaop.xml");
		// 注意：getBean获取的是dog
		IIntroduce introduce = ctx.getBean("dog", IIntroduce.class);
		introduce.sayIntroduce();
	}

	public static void main(String[] args) {

//		test1();
		test2();
	}
}


