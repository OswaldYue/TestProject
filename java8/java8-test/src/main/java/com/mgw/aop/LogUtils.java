package com.mgw.aop;


import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;


@Component
@Aspect
public class LogUtils {


    @Before("execution(* com.mgw.aop.MyCaculator.add(int,int))")
    public void logBefore() {

        System.out.println("[xxx]方法开始前");

    }

    public void logAfter() {

        System.out.println("[xxx]方法结束");

    }


    public void logReturn() {
        System.out.println("[xxx]方法返回");
    }

    public void logException() {

        System.out.println("[xxx]方法异常");
    }

}
