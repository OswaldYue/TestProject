package com.mgw.aop;


import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;


@Component
@Aspect
public class LogUtils {

    @Pointcut("execution(* com.mgw.aop.MyCaculator.add(int,int))")
    public void point() {}

    @Before("point()")
    public void logBefore() {

        System.out.println("[xxx]方法开始前");

    }
    @After("point()")
    public void logAfter() {

        System.out.println("[xxx]方法结束");

    }


    @AfterReturning("point()")
    public void logReturn() {
        System.out.println("[xxx]方法返回");
    }

    @AfterThrowing("point()")
    public void logException() {

        System.out.println("[xxx]方法异常");
    }

}
