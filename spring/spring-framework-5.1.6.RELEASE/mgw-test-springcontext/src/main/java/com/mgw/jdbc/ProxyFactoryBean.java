package com.mgw.jdbc;

import org.springframework.beans.factory.FactoryBean;

import java.lang.reflect.Proxy;


//若加了@Component 则需要被扫描,实际上作为提供给别人用的框架来说,大多数情况是没法被扫描到的
//@Component
public class ProxyFactoryBean implements FactoryBean {

	Class mapperInterface;

	public ProxyFactoryBean() {

		System.out.println("---ProxyFactoryBean---ProxyFactoryBean()---");
	}

	public ProxyFactoryBean(Class mapperInterface) {
		System.out.println("---ProxyFactoryBean---ProxyFactoryBean(Class mapperInterface)---");
		this.mapperInterface = mapperInterface;
	}

//	public void setMapperInterface(Class mapperInterface) {
//		this.mapperInterface = mapperInterface;
//	}

	@Override
	public Object getObject() throws Exception {
		return Proxy.newProxyInstance(ProxyFactoryBean.class.getClassLoader(), new Class[]{mapperInterface}, new JdbcInvocationHandler());
	}

	@Override
	public Class<?> getObjectType() {
		return mapperInterface;
	}

	@Override
	public boolean isSingleton() {
		return true;
	}
}
