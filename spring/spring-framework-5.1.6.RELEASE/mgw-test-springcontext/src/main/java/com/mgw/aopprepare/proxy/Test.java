package com.mgw.aopprepare.proxy;

public class Test {


	public static void test1() {
		// JDK动态代理
		MyInvocationHandler handler = new MyInvocationHandler(new JDKDog());
		JDKAnimal proxy = (JDKAnimal) handler.getProxy();
		proxy.sayHello();
	}

	public static void test2() {
		// CGLIB动态代理
		CglibCat cat = (CglibCat) new MyCglibProxy().getInstance(CglibCat.class);
		cat.sayHello();
	}

	public static void main(String[] args) {

//		test1();
		test2();
	}
}
