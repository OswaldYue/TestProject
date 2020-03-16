package com.mgw.config;

import org.springframework.context.ApplicationContextInitializer;
import org.springframework.web.context.support.XmlWebApplicationContext;

public class MyContextInitializerClasses implements ApplicationContextInitializer<XmlWebApplicationContext> {
	/**
	 * Initialize the given application context.
	 * @param applicationContext the application to configure
	 */
	@Override
	public void initialize(XmlWebApplicationContext applicationContext) {
		System.out.println("MyContextInitializerClasses initialize ...");
		System.out.println("MyContextInitializerClasses " + applicationContext.toString());
	}
}
