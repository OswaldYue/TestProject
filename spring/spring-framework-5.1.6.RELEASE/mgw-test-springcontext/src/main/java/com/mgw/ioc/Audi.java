package com.mgw.ioc;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

@Component
public class Audi implements InitializingBean {



	Audi() {
		System.out.println("---Audi---Audi()---");
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		System.out.println("---Audi---afterPropertiesSet()---Lifecycle Callback---");
	}
}
