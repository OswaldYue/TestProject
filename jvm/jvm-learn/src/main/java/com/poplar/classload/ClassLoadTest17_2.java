package com.poplar.classload;

/**
 * 关于命名空间的重要说明:
 * 1.子加载器所加载的类能访问父加载器所加载的类
 * 2.父加载器所加载的类不能访问子加载器所加载的类
 */
public class ClassLoadTest17_2 {
    public static void main(String[] args) throws Exception {
        ClassLoadTest16 loader1 = new ClassLoadTest16("loader1");
        loader1.setPath("E:\\code\\AppData\\classloader4\\");

        Class<?> clazz = loader1.loadClass("com.poplar.classload.Simple");
        System.out.println("class : " + clazz.hashCode());
        Object instance = clazz.newInstance();//实列化Simple和MyCat
        //删除classpath下的MyCat,留下Simple，程序报错 java.lang.NoClassDefFoundError: com/poplar/classload/MyCat
        //因为命名空间，Simple是由父加载器系统加载器加载的，当加载MyCat时，系统加载器找不到MyCat，因为找不到，所以报错

        //删除classpath下的Simple,留下MyCat,程序不会报错
        //Simple是由自定义加载器ClassLoadTest16加载的，当加载MyCat时，自定义加载器委托父加载器系统加载器去加载MyCat，可以找到，顺利加载
    }
}

class MyCat2 {
    public MyCat2() {
        System.out.println("MyCat2 loaded by: " + this.getClass().getClassLoader());

    }
}

class Simple2 {
    public Simple2() {
        System.out.println("Simple2 loaded by: " + this.getClass().getClassLoader());
        new MyCat2();

    }
}