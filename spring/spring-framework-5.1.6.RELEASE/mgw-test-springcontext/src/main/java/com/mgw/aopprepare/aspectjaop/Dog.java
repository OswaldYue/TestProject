package com.mgw.aopprepare.aspectjaop;

public class Dog  implements Animal {
	@Override
	public void sayHello() {
		System.out.println("--dog 被增强的方法");
	}
}
