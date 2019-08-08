package com.mgw.spring;

import com.mgw.spring.service.UserService;
import com.mgw.spring.utils.BeanFactory;

/**
 * 这个测试类只要是实现spring的IOC方面的代码
 * */
public class Test {

    public static void test1() {

        BeanFactory beanFactory = new BeanFactory("spring.xml");
        UserService userService = (UserService)beanFactory.getBean("userService");
        //UserService userService = new UserServiceImpl();
        userService.find();

    }

    public static void main(String[] args) {
        test1();
    }

}
