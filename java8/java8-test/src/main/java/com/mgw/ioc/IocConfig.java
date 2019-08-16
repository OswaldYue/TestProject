package com.mgw.ioc;

import org.springframework.context.annotation.Import;

//@ComponentScan(value = "com.mgw.ioc")
//@Configuration
@Import(Audi.class)
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
