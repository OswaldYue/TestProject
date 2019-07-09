package com.mgw.aop;


import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;


/***
 *
 *
 * try {
 *
 *      @Before
 *      method.invoke(obj,args);
 *      @AfterReturning
 *
 * }catch(Exception e) {
 *     @AfterThrowing
 * }finally {
 *
 *      @After
 * }
 *
 *
 * */

@Component
@Aspect
public class LogAspect {

    @Pointcut("execution(* com.mgw.aop.MyCaculator.*(..))")
    public void point() {}

    @Before("point()")
    public void logBefore() {

        System.out.println("[xxx]方法开始执行");

    }

    @After("point()")
    public void logAfter() {

        System.out.println("[xxx]方法最终结束");

    }

    @AfterReturning("point()")
    public void logReturn() {
        System.out.println("[xxx]方法正常执行完成,结果是:");
    }

    @AfterThrowing("point()")
    public void logException() {

        System.out.println("[xxx]方法发生异常,异常信息是:");
    }

}
