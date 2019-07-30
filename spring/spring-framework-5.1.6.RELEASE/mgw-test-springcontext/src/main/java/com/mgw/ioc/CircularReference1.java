package com.mgw.ioc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CircularReference1 {

	@Autowired
	CircularReference2 circularReference2;

	public CircularReference1() {
		System.out.println("---CircularReference1---CircularReference1()---");
	}

}
