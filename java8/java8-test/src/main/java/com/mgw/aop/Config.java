package com.mgw.aop;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@ComponentScan("com.mgw.aop")
@Configuration
@EnableAspectJAutoProxy //相当于<aop:aspectj-autoproxy></aop:aspectj-autoproxy>
public class Config {




}
