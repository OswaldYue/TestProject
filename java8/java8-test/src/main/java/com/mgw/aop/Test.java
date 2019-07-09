package com.mgw.aop;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Arrays;

public class Test {





    public static void test1() {

        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationcontext.xml");

        Caculator caculator = context.getBean(Caculator.class);

        int add = caculator.add(2, 3);

        System.out.println(caculator.getClass());

    }

    public static void test2() {

        Caculator caculator = new MyCaculator();
        //使用动态代理实现aop功能
        Caculator proxy = CaculatorProxy.getProxy(caculator);
        //动态代理实际上也实现了Caculator接口[interface com.mgw.aop.Caculator]
        System.out.println(Arrays.asList(proxy.getClass().getInterfaces()));
//        proxy.add(2,3);
//        proxy.div(3,0);
    }

    public static void test3() {

        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationcontext.xml");

        Caculator bean = context.getBean(Caculator.class);

        //com.mgw.aop.MyCaculator@4e3958e7
        //class com.sun.proxy.$Proxy19
        //实际上容器中保存的组件是代理对象

        //当把切面类从容器中取消后,容器中保存的就不再是代理对象了,而是原生对象（com.mgw.aop.MyCaculator@5ccddd20      class com.mgw.aop.MyCaculator）
        System.out.println(bean);
        System.out.println(bean.getClass());

        bean.add(2,3);


        //此时强转会出错,因为代理对象已经不是原来的对象了,但是可以转为接口,可以用接口
        //MyCaculator myCaculator = (MyCaculator)context.getBean("myCaculator");
        Caculator caculator = (Caculator)context.getBean("myCaculator");
        System.out.println(caculator);


    }

    public static void main(String[] args) {


//        test1();
//        System.out.println("=============================");
//        test2();
        System.out.println("=============================");
        test3();

    }
}
