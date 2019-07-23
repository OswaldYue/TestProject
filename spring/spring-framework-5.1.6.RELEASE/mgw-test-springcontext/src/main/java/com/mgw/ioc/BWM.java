package com.mgw.ioc;

import org.springframework.stereotype.Component;

@Component
public class BWM implements Car {
	BWM() {
		System.out.println("---not lazy BWM---");
	}
}
