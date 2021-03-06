package com.poplar.classload;

import java.io.*;

public class ClassLoadTest16 extends ClassLoader {

    private String classLoadName;

    private final String fileExtension = ".class";

    private String path;

    public ClassLoadTest16(String classLoadName) {

        super(); //将系统类加载器作为父加载器
        this.classLoadName = classLoadName;
    }

    public ClassLoadTest16(ClassLoader parent,String classLoadName) {
        super(parent); //显示指定该类的父加载器
        this.classLoadName = classLoadName;
    }

    public ClassLoadTest16(ClassLoader parent) {
        super(parent); //显示指定该类的父加载器
    }

    public void setPath(String path) {
        this.path = path;
    }

    @Override
    protected Class<?> findClass(String className) throws ClassNotFoundException {
        System.out.println("findClass invoked:" + className);
        System.out.println("class loader name:" + this.classLoadName);
        byte[] bytes = loadClassData(className);
        return defineClass(className,bytes,0,bytes.length);

    }

    private byte[] loadClassData(String className)  {

        InputStream is = null;
        byte[] data = null;
        ByteArrayOutputStream baos = null;

        className = className.replace(".","\\");

        try {

            is = new FileInputStream(new File(this.path + className + this.fileExtension));
            baos = new ByteArrayOutputStream();

            int ch = 0;
            while (-1 != (ch = is.read())) {
                baos.write(ch);
            }

            data = baos.toByteArray();
        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                is.close();
                baos.close();
            }catch (Exception e) {
                e.printStackTrace();
            }

        }

        return data;
    }

    @Override
    public String toString() {
        return "[" + classLoadName + "]";
    }

    public static void test() throws Exception {

        // 将父加载器设置为null,这样双亲委派机制就触发不了了
        ClassLoadTest16 myLoader1 = new ClassLoadTest16(null,"myLoader1");
        myLoader1.setPath("E:\\code\\AppData\\classloader4\\");

        Class<?> clazz = myLoader1.loadClass("com.poplar.classload.ClassLoadTest15");
        System.out.println("class:" + clazz.hashCode());
        Object object = clazz.newInstance();
        System.out.println(object);
        System.out.println(object.getClass().getClassLoader());
    }

    public static void test1() throws Exception {

        ClassLoadTest16 myLoader1 = new ClassLoadTest16("myLoader1");
        myLoader1.setPath("E:\\code\\AppData\\classloader4\\");

        Class<?> clazz = myLoader1.loadClass("com.poplar.classload.ClassLoadTest15");
        System.out.println("class:" + clazz.hashCode());
        Object object = clazz.newInstance();
        System.out.println(object);
        System.out.println(object.getClass().getClassLoader());
    }

    /**
     * 若可用父加载器系统加载器加载类，则其加载出来的class一致
     * 若不可用父加载器系统加载器加载类，使用自定义加载器加载类，则其加载出来的class不一致
     * */
    public static void test2() throws Exception {

        ClassLoadTest16 myLoader1 = new ClassLoadTest16("myLoader1");
        myLoader1.setPath("E:\\code\\AppData\\classloader4\\");

        Class<?> clazz = myLoader1.loadClass("com.poplar.classload.ClassLoadTest15");
        System.out.println("class:" + clazz.hashCode());
        Object object = clazz.newInstance();
        System.out.println(object);
        System.out.println(object.getClass().getClassLoader());

        System.out.println("================================================");

        ClassLoadTest16 myLoader2= new ClassLoadTest16("myLoader2");
        myLoader2.setPath("E:\\code\\AppData\\classloader4\\");

        Class<?> clazz2 = myLoader2.loadClass("com.poplar.classload.ClassLoadTest15");
        System.out.println("class:" + clazz2.hashCode());
        Object object2 = clazz2.newInstance();
        System.out.println(object2);
        System.out.println(object2.getClass().getClassLoader());

    }

    /**
     * 将myLoader1作为myLoader2的父加载器，此时委托给myLoader1加载，那么此时加载出来的class都一致
     * */
    public static void test3() throws Exception {

        ClassLoadTest16 myLoader1 = new ClassLoadTest16("myLoader1");
        myLoader1.setPath("E:\\code\\AppData\\classloader4\\");

        Class<?> clazz = myLoader1.loadClass("com.poplar.classload.ClassLoadTest15");
        System.out.println("class:" + clazz.hashCode());
        Object object = clazz.newInstance();
        System.out.println(object);
        System.out.println(object.getClass().getClassLoader());

        System.out.println("================================================");

        ClassLoadTest16 myLoader2= new ClassLoadTest16(myLoader1,"myLoader2");
        myLoader2.setPath("E:\\code\\AppData\\classloader4\\");

        Class<?> clazz2 = myLoader2.loadClass("com.poplar.classload.ClassLoadTest15");
        System.out.println("class:" + clazz2.hashCode());
        Object object2 = clazz2.newInstance();
        System.out.println(object2);
        System.out.println(object2.getClass().getClassLoader());

    }

    /**
     * 若可以被父加载器系统加载器加载类，则class一致
     * 否则class1与class2一致，class3不与其他两个一致
     * */
    public static void test4() throws Exception {

        ClassLoadTest16 myLoader1 = new ClassLoadTest16("myLoader1");
        myLoader1.setPath("E:\\code\\AppData\\classloader4\\");

        Class<?> clazz = myLoader1.loadClass("com.poplar.classload.ClassLoadTest15");
        System.out.println("class:" + clazz.hashCode());
        Object object = clazz.newInstance();
        System.out.println(object);
        System.out.println(object.getClass().getClassLoader());

        System.out.println("================================================");

        ClassLoadTest16 myLoader2= new ClassLoadTest16(myLoader1,"myLoader2");
        myLoader2.setPath("E:\\code\\AppData\\classloader4\\");

        Class<?> clazz2 = myLoader2.loadClass("com.poplar.classload.ClassLoadTest15");
        System.out.println("class:" + clazz2.hashCode());
        Object object2 = clazz2.newInstance();
        System.out.println(object2);
        System.out.println(object2.getClass().getClassLoader());

        System.out.println("================================================");

        ClassLoadTest16 myLoader3= new ClassLoadTest16("myLoader3");
        myLoader3.setPath("E:\\code\\AppData\\classloader4\\");

        Class<?> clazz3 = myLoader3.loadClass("com.poplar.classload.ClassLoadTest15");
        System.out.println("class:" + clazz3.hashCode());
        Object object3 = clazz3.newInstance();
        System.out.println(object3);
        System.out.println(object3.getClass().getClassLoader());

    }


    /**
     * 如果是使用父加载器系统类加载器来加载，实际上经测试是不会去卸载ClassLoadTest15这个Class的
     * 但是如果使用我们自定义类加载器ClassLoadTest16来加载，经测试发现会卸载ClassLoadTest15
     * [Unloading class com.poplar.classload.ClassLoadTest15 0x0000000100061028]
     *
     * 印证一个结论:
     * 1.由Java虚拟机自带的类加载器所加载的类，在虚拟机的生命周期中，始终不会被卸载。
     * Java虚拟机本身会始终引用这些加载器，而这些类加载器则会始终引用他们所加载的类的Class对象，因此这些Class对象是可触及的
     * 2.由用户自定义的类加载器所加载的类是可以被卸载的
     *  */
    public static void test5() throws Exception {

        ClassLoadTest16 myLoader1 = new ClassLoadTest16("myLoader1");
        myLoader1.setPath("E:\\code\\AppData\\classloader4\\");

        Class<?> clazz = myLoader1.loadClass("com.poplar.classload.ClassLoadTest15");
        System.out.println("class:" + clazz.hashCode());
        Object object = clazz.newInstance();
        System.out.println(object);
        System.out.println(object.getClass().getClassLoader());

        System.out.println("================================================");

        // 让其gc,模拟类卸载
        myLoader1 = null;
        clazz = null;
        object = null;
        System.gc();

        Thread.sleep(10_000);

        myLoader1 = new ClassLoadTest16("myLoader1");
        myLoader1.setPath("E:\\code\\AppData\\classloader4\\");

        clazz = myLoader1.loadClass("com.poplar.classload.ClassLoadTest15");
        System.out.println("class:" + clazz.hashCode());
        object = clazz.newInstance();
        System.out.println(object);
        System.out.println(object.getClass().getClassLoader());

    }

    /**
     * 若可以被父加载器系统加载器加载类，则class一致
     * 否则不一致
     * */
    public static void test6() throws Exception {

        ClassLoadTest16 myLoader1 = new ClassLoadTest16("myLoader1");
        myLoader1.setPath("E:\\code\\AppData\\classloader4\\");

        Class<?> clazz = myLoader1.loadClass("com.poplar.classload.ClassLoadTest15");
        System.out.println("class:" + clazz.hashCode());
        Object object = clazz.newInstance();
        System.out.println(object);
        System.out.println(object.getClass().getClassLoader());

        System.out.println("================================================");

        final Class<?> clazz1 = ClassLoader.getSystemClassLoader().loadClass("com.poplar.classload.ClassLoadTest15");
        System.out.println("class:" + clazz1.hashCode());
        Object object1 = clazz1.newInstance();
        System.out.println(object1);
        System.out.println(object1.getClass().getClassLoader());

    }

    public static void main(String[] args) throws Exception {

//        test();
//        test1();
//        test2();
//        test3();
//        test4();
        test5();
//        test6();
    }

}
