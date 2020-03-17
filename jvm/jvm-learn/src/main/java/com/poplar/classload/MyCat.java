package com.poplar.classload;


public class MyCat {
    public MyCat() {
//        System.out.println("MyCat by load " + MyCat.class.getClassLoader());
        System.out.println("MyCat is loaded by: " + this.getClass().getClassLoader());
        // 当删除classpath下的Simple,留下MyCat,Simple是由自定义加载器ClassLoadTest16加载的，
        // 当加载MyCat时，自定义加载器委托父加载器系统加载器去加载MyCat
        // 此时因为MyCat由系统类加载器加载，是ClassLoadTest16加载器的父类，父类加载器看不到子加载器的信息，
        // 所以此时报错java.lang.NoClassDefFoundError
        System.out.println("from MyCat : " + Simple.class);
    }
}
