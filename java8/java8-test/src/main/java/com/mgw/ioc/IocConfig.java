package com.mgw.ioc;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@ComponentScan(value = "com.mgw.ioc")
@Configuration
//@Import(Audi.class)
//@Import(MyImportBeanDefinitionRegistrar.class)
//@Import(MyImportSelector.class)
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

    @Bean
    public Audi1 audi1() {

        return new Audi1();
    }

    @Bean
    public Audi audi() {
        audi1();
        return new Audi();
    }

}
