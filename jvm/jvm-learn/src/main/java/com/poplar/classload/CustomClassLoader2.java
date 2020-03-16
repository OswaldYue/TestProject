package com.poplar.classload;

import java.io.*;

/**
 * Created By poplar on 2019/11/7
 * 自定义类加载器
 */
public class CustomClassLoader2 extends ClassLoader {

    private String classLoaderName;

    private String path;

    public void setPath(String path) {
        this.path = path;
    }

    private static final String filePost = ".class";

    public CustomClassLoader2(ClassLoader parent, String classLoaderName) {
        super(parent);//显示指定该类的父类加载器
        this.classLoaderName = classLoaderName;
    }

    public CustomClassLoader2(String classLoaderName) {
        super();//将系统类加载器当作该类的父类加载器
        this.classLoaderName = classLoaderName;
    }

    public CustomClassLoader2(ClassLoader parent) {
        super(parent);//显示指定该类的父类加载器
    }

    @Override
    public Class findClass(String name) {
        System.out.println("findClass,输出这句话说明我们自己的类加载器加载了指定的类");
        byte[] b = loadClassData(name);
        return defineClass(name, b, 0, b.length);
    }

    private byte[] loadClassData(String name) {
        InputStream is = null;
        byte[] data = null;
        ByteArrayOutputStream byteArrayOutputStream = null;

        try {
            name = name.replace(".", File.separator);//File.separator根据操作系统而变化
            is = new FileInputStream(new File(path + name + filePost));
            byteArrayOutputStream = new ByteArrayOutputStream();
            int len = 0;
            while (-1 != (len = is.read())) {
                byteArrayOutputStream.write(len);
            }
            data = byteArrayOutputStream.toByteArray();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
                byteArrayOutputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return data;
        }
    }


    public static void main(String[] args) throws Exception {
        //类的卸载
        CustomClassLoader2 Loader2 = new CustomClassLoader2("load2");
        test1(Loader2);
        Loader2 = null;
        System.gc();
        Thread.sleep(10000); //jvisualvm 查看当前java进程 -XX:+TraceClassUnloading这个用于追踪类卸载的信息
        CustomClassLoader2 Loader3 = new CustomClassLoader2("load2");
        test1(Loader3);
        /*
        执行结果：
        findClass,输出这句话说明我们自己的类加载器加载了指定的类
        com.poplar.classload.CustomClassLoader2@15db9742
        2018699554
        -------------------------------------
        findClass,输出这句话说明我们自己的类加载器加载了指定的类
        com.poplar.classload.CustomClassLoader2@4e25154f
        1550089733*/
    }

    private static void test1(CustomClassLoader2 loader2) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        loader2.setPath("C:\\Users\\poplar\\Desktop\\");
        Class<?> clazz = loader2.loadClass("com.poplar.classload.ClassLoadTest");
        Object instance = clazz.newInstance();
        System.out.println(instance.getClass().getClassLoader());
        System.out.println(instance.hashCode());
        System.out.println("-------------------------------------");
        //运行结果：（此处测试建议把源码文件先删掉，不然idea会重新生成classes,还是会导致系统类加载器加载）
    }
}
