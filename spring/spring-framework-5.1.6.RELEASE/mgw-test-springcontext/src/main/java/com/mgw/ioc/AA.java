package com.mgw.ioc;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class AA  {

	@PostConstruct
	public void initAA() {

		System.out.println("---AA---initAA()---Lifecycle Callback---");

	}

	AA(){
		System.out.println("---AA---AA()---");
	}
}
