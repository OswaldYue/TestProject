package com.mgw.aopprepare.advisor;

public class Dog implements Animal {

	@Override
	public void sayHello() {
		System.out.println("我是Dog类的sayHello方法。。。");
	}

	public void sayHelloDog() {
		System.out.println("我是一只狗。。。");
	}
}
