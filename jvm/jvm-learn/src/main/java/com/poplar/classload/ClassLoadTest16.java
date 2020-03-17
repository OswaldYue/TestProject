package com.poplar.classload;

import java.io.*;

public class ClassLoadTest16 extends ClassLoader {

    private String classLoadName;

    private final String fileExtension = ".class";

    public ClassLoadTest16(String classLoadName) {

        super(); //将系统类加载器作为父加载器
        this.classLoadName = classLoadName;
    }

    public ClassLoadTest16(ClassLoader parent,String classLoadName) {
        super(parent); //显示指定该类的父加载器
        this.classLoadName = classLoadName;
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {

        byte[] bytes = loadClassData(name);
        return defineClass(name,bytes,0,bytes.length);

    }

    private byte[] loadClassData(String name)  {

        InputStream is = null;
        byte[] data = null;
        ByteArrayOutputStream baos = null;

        try {
            this.classLoadName = this.classLoadName.replace(".","\\");
            is = new FileInputStream(new File(name + this.fileExtension));
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

    public static void test(ClassLoader classLoader) throws Exception {
        Class<?> clazz = classLoader.loadClass("com.poplar.classload.ClassLoadTest15");
        Object instance = clazz.newInstance();

        System.out.println(instance);
    }

    public static void main(String[] args) throws Exception {
        ClassLoadTest16 myLoader1 = new ClassLoadTest16("myLoader1");
        test(myLoader1);
    }

}
