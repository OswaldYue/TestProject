package com.mgw.ioc;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
@Lazy
public class BYD implements Car {
	BYD() {

		System.out.println("---lazy BYD---");
	}
}
