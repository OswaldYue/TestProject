package com.mgw.aopprepare.proxy;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class MyCglibProxy implements MethodInterceptor {

	private Enhancer enhancer = new Enhancer();

	// 这里的目标类型为Object，则可以接受任意一种参数作为被代理类，实现了动态代理
	public Object getInstance(Class clazz) {
		enhancer.setSuperclass(clazz);
		enhancer.setCallback(this);
		// 返回代理对象
		return enhancer.create();
	}

	@Override
	public Object intercept(Object proxy, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
		System.out.println("==代理方法开始执行");
		Object result = methodProxy.invokeSuper(proxy, args);
		System.out.println("==代理方法结束执行");
		return result;
	}
}
