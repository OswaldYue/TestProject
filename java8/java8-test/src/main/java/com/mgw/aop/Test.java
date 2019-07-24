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

        System.out.println("----------------------------------");

        //当一个类在spring容器中有代理时,此时强转会出错,因为代理对象已经不是原来的对象了,但是可以转为接口(jdk动态代理时),可以用接口
        //com.sun.proxy.$Proxy30 cannot be cast to com.mgw.aop.MyCaculator
        //MyCaculator myCaculator = (MyCaculator)context.getBean("myCaculator");
        Caculator caculator = (Caculator)context.getBean("myCaculator");

        //com.mgw.aop.MyCaculator@491b9b8
        //class com.sun.proxy.$Proxy30
        System.out.println(caculator);
        System.out.println(caculator.getClass());

        bean.div(3,0);


    }


    public static void test4() {

        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationcontext.xml");

        //当MyCaculator没有接口时,会创建一个cglib代理
        MyCaculator bean = context.getBean(MyCaculator.class);

        //当MyCaculator没有接口时,会创建一个cglib代理,此时可以转为MyCaculator类型
        MyCaculator bean2 = (MyCaculator)context.getBean("myCaculator");

    }

    public static void test5() {

        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationcontext.xml");
        Caculator bean = context.getBean(Caculator.class);

        int add = bean.add(3, 2);
        System.out.println("=========结果:"+add);
        bean.div(3,0);
    }

    public static void test6() {

        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationcontext.xml");
        Caculator bean = context.getBean(Caculator.class);

        /**
         * 当无环绕通知时:
         * =============================
         * LogAspect切面前置[add]方法开始执行,参数列表是:[3, 2]
         * ValidateAspect切面前置[add]方法开始执行,参数列表是:[3, 2]
         * add 执行
         * ValidateAspect切面后置[add]方法最终结束
         * ValidateAspect切面返回[add]方法正常执行完成,结果是:5
         * LogAspect切面后置[add]方法最终结束
         * LogAspect切面返回[add]方法正常执行完成,结果是:5
         * =========结果:5
         *
         *
         * 当有环绕通知时:目标方法永远只执行一次(环绕先就在环绕中执行)
         * =============================
         * LogAspect切面环绕[环绕前置通知]add方法开始
         * LogAspect切面前置[add]方法开始执行,参数列表是:[3, 2]
         * ValidateAspect切面环绕[环绕前置通知]add方法开始
         * ValidateAspect切面前置[add]方法开始执行,参数列表是:[3, 2]
         * add 执行
         * 环绕...
         * ValidateAspect切面环绕[环绕返回通知]add方法返回,结果是:5
         * ValidateAspect切面环绕[环绕后置通知]add方法最终结束
         * ValidateAspect切面后置[add]方法最终结束
         * ValidateAspect切面返回[add]方法正常执行完成,结果是:5
         * 环绕...
         * LogAspect切面环绕[环绕返回通知]add方法返回,结果是:5
         * LogAspect切面环绕[环绕后置通知]add方法最终结束
         * LogAspect切面后置[add]方法最终结束
         * LogAspect切面返回[add]方法正常执行完成,结果是:5
         * =========结果:5
         *
         * 当环绕通知处于第二执行的切面类时:目标方法永远只执行一次(普通通知先就在普通中执行)
         * =============================
         * LogAspect切面环绕[环绕前置通知]add方法开始
         * LogAspect切面前置[add]方法开始执行,参数列表是:[3, 2]
         * ValidateAspect切面前置[add]方法开始执行,参数列表是:[3, 2]
         * add 执行
         * ValidateAspect切面后置[add]方法最终结束
         * ValidateAspect切面返回[add]方法正常执行完成,结果是:5
         * 环绕...
         * LogAspect切面环绕[环绕返回通知]add方法返回,结果是:5
         * LogAspect切面环绕[环绕后置通知]add方法最终结束
         * LogAspect切面后置[add]方法最终结束
         * LogAspect切面返回[add]方法正常执行完成,结果是:5
         * =========结果:5
         *
         * 当两个切面同时切入时,后进入的先执行
         * */
        int add = bean.add(3, 2);
        System.out.println("=========结果:"+add);
        bean.div(3,0);
    }

    public static void test7() {

        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationcontext.xml");
        Caculator bean = context.getBean(Caculator.class);

        bean.muti(4,2);

    }

    public static void main(String[] args) {

//        test1();
//        System.out.println("=============================");
//        test2();
        System.out.println("=============================");
        test3();
//        System.out.println("=============================");
//        test5();
//        System.out.println("=============================");
//        test6();

//        System.out.println("=============================");
//        test7();
    }
}
