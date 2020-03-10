package com.mgw.aopprepare.advice;

import org.springframework.aop.framework.ProxyFactory;

public class Test {

	public static void test1() {
		// 前置增强
		// 1、实例化bean和增强
		Animal dog = new Dog();
		MyMethodBeforeAdvice advice = new MyMethodBeforeAdvice();

		// 2、创建ProxyFactory并设置代理目标和增强
		ProxyFactory proxyFactory = new ProxyFactory();
		proxyFactory.setTarget(dog);
		proxyFactory.addAdvice(advice);

		// 3、生成代理实例
		Animal proxyDog = (Animal) proxyFactory.getProxy();
		proxyDog.sayHello("一哈", 1);
	}



	public static void test2() {
		// 后置增强
		// 1、实例化bean和增强
		Animal dog = new Dog();
		MyAfterReturningAdvice advice = new MyAfterReturningAdvice();

		// 2、创建ProxyFactory并设置代理目标和增强
		ProxyFactory proxyFactory = new ProxyFactory();
		proxyFactory.setTarget(dog);
		proxyFactory.addAdvice(advice);

		// 3、生成代理实例
		Animal proxyDog = (Animal) proxyFactory.getProxy();
		proxyDog.sayHello("二哈", 2);

	}


	public static void test3() {
		// 异常增强
		// 1、实例化bean和增强
		Animal dog = new Dog();
		MyThrowsAdvice advice = new MyThrowsAdvice();

		// 2、创建ProxyFactory并设置代理目标和增强
		ProxyFactory proxyFactory = new ProxyFactory();
		proxyFactory.setTarget(dog);
		proxyFactory.addAdvice(advice);

		// 3、生成代理实例
		Animal proxyDog = (Animal) proxyFactory.getProxy();
		proxyDog.sayException("三哈", 3);

	}


	public static void test4() {
		// 环绕增强
		// 1、实例化bean和增强
		Animal dog = new Dog();
		MyMethodInterceptor advice = new MyMethodInterceptor();

		// 2、创建ProxyFactory并设置代理目标和增强
		ProxyFactory proxyFactory = new ProxyFactory();
		proxyFactory.setTarget(dog);
		proxyFactory.addAdvice(advice);

		// 3、生成代理实例
		Animal proxyDog = (Animal) proxyFactory.getProxy();
		proxyDog.sayHello("四哈", 4);

	}

	public static void main(String[] args) {
//		test1();
//		test2();
//		test3();
		test4();
	}
}
