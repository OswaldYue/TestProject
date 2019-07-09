package com.mgw.aop;


import java.lang.reflect.Method;
import java.util.Arrays;

public class LogUtils {


    public static void logStart(Method method,Object... args) {

        System.out.println("["+method.getName()+"]方法开始执行,参数列表是:"+ Arrays.asList(args));
    }

    public static void logAfter(Method method) {
        System.out.println("["+method.getName()+"]方法最终结束");

    }

    public static void logReturn(Method method,Object result) {
        System.out.println("["+method.getName()+"]方法正常执行返回,结果是:"+result);
    }


    public static void logException(Method method,Exception e) {
        System.out.println("["+method.getName()+"]方法异常,异常信息是:" + e.getCause());
    }

}
