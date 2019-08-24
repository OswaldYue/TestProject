package com.mgw.log;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * spring + mybatis + log4j 组合不会 mybatis是不会打印日志的
 * 但是同样的配置mybatis + log4j mybatis就可以打印日志
 *
 * 原因:
 * 当使用mybatis + log4j时 mybatis源码会不断的寻找最终找到第四个可用的log框架log4j 调用useLog4JLogging这个方法
 * 但是如果使用spring + mybatis + log4j 如果使用的是spring4 那么也是可以的 因为他会不断的找 最终找到jcl框架 调用useCommonsLogging这个方法 因为spring4的jcl是原生的jcl
 * 默认第一选择就是log4j 所以可以
 * 如果使用spring5以上 mybatis框架也是不断的找 最终找到jcl框架 调用useCommonsLogging这个方法 但是spring5使用的jcl确不是原生的 而是被spring改写过得spring-jcl框架
 * 而spring-jcl则默认使用的是jul 而jul的日志级别默认只会打印info级别以上的
 *
 *
 * */
public class LogTest {

    public static void test1() {

        Logger logger = LoggerFactory.getLogger(LogTest.class);

        logger.info("this is info");

    }

    public static void test2() {

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(LogConfig.class);

        context.start();

    }

    public static void main(String[] args) {

        System.out.println("===================================================");
        test1();

//        System.out.println("===================================================");
//        test2();
    }
}
