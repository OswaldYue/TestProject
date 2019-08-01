package com.mgw.jdbc;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.stereotype.Component;

@Component
public class MyFactoryBean implements FactoryBean {

	@Override
	public Object getObject() throws Exception {
		return new TestBean1();
	}

	@Override
	public Class<?> getObjectType() {
		return TestBean1.class;
	}

	@Override
	public boolean isSingleton() {
		return true;
	}
}
