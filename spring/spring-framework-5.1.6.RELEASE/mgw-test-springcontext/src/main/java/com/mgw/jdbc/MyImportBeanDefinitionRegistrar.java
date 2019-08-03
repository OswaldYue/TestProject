package com.mgw.jdbc;

import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;

/**
 * 主要目的是实例化一个ProxyFactoryBean这个bean
 * */
public class MyImportBeanDefinitionRegistrar implements ImportBeanDefinitionRegistrar {
	@Override
	public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {

		//给出一个包名 com.mgw.jdbc 可以得到这个包下所有的类
		//进行for() 可以将所有需要进行代理的Mapper接口类全部注入

		BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(ProxyFactoryBean.class);
		GenericBeanDefinition genericBeanDefinition = (GenericBeanDefinition)beanDefinitionBuilder.getBeanDefinition();
		genericBeanDefinition.setAutowireMode(3);//以构造方法作为注入方式
		genericBeanDefinition.getConstructorArgumentValues().addGenericArgumentValue("com.mgw.jdbc.AccountMapper");
		registry.registerBeanDefinition("accountMapper",genericBeanDefinition);

	}
}
