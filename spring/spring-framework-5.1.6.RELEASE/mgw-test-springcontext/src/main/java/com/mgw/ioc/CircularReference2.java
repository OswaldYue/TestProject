package com.mgw.ioc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CircularReference2 {

	@Autowired
	CircularReference1 circularReference1;

	public CircularReference2() {

		System.out.println("---CircularReference2---CircularReference2()---");

	}

}
