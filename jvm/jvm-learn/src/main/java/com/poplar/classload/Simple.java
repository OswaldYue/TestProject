package com.poplar.classload;


public class Simple {
    public Simple() {
        System.out.println("Simple is loaded by: " + this.getClass().getClassLoader());
        // 当删除classpath下的Simple,留下MyCat,Simple是由自定义加载器ClassLoadTest16加载的，
        // 当加载MyCat时，自定义加载器委托父加载器系统加载器去加载MyCat
        // 此时因为MyCat由系统类加载器加载，是ClassLoadTest16加载器的父类，子加载器可以看见父加载器信息，故下面这句代码不会报错
        // from Simple : class com.poplar.classload.MyCat
        System.out.println("from Simple : " + MyCat.class);
        new MyCat();

    }
}
