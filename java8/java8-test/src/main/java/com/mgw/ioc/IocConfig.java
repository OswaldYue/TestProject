package com.mgw.ioc;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@ComponentScan(value = "com.mgw.ioc")
@Configuration
public class IocConfig {


//    @Bean
//    public BWM bwm() {
//
//        return new BWM();
//    }
//
//    @Bean
//    public BYD byd() {
//
//        return new BYD();
//    }
}
