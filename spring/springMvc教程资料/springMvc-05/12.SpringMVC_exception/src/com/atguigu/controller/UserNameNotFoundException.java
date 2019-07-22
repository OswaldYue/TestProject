package com.atguigu.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


@ResponseStatus(reason="用户被拒绝登陆",value=HttpStatus.NOT_ACCEPTABLE)
public class UserNameNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
}
