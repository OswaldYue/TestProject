package com.mgw.jdbc;

import org.springframework.beans.factory.FactoryBean;

import java.lang.reflect.Proxy;


//若加了@Component 则需要被扫描,实际上作为提供给别人用的框架来说,大多数情况是没法被扫描到的  所以使用ProxyFactoryBean这个类的方法
//@Component
public class ProxyFactoryBean1 implements FactoryBean {

	@Override
	public Object getObject() throws Exception {
		return Proxy.newProxyInstance(ProxyFactoryBean1.class.getClassLoader(), new Class[]{AccountMapper.class}, new JdbcInvocationHandler());
	}

	@Override
	public Class<?> getObjectType() {
		return AccountMapper.class;
	}

	@Override
	public boolean isSingleton() {
		return true;
	}
}
