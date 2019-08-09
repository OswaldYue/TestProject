package com.mgw.jdbc;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.stereotype.Component;

/**
 *
 * FactoryBean的主要作用是实例化复杂的bean  这些复杂的bean由于太复杂  需要依赖的太多  那么xml配置或者注解配置,config配置就显得非常复杂
 * 这是可以使用FactoryBean统一进行人工初始化并交给spring容器管理
 * 例如:Mybatis在与spring整合时就提供了一个 SqlSessionFactoryBean 类来做整合
 *
 * */
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
