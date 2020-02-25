package com.mgw.classload.chapter5;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class DecryptClassLoader extends ClassLoader {

    private final static String DEFAULT_DIR = "E:\\code\\AppData\\classloader3";

    private String dir = DEFAULT_DIR;

    public DecryptClassLoader() {
        super();
    }

    public DecryptClassLoader(ClassLoader parent) {
        super(parent);
    }

    public void setDir(String dir) {
        this.dir = dir;
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {

        String classPath = name.replace(".", "/");
        File classFile = new File(dir,classPath + ".class");
        if (!classFile.exists()) {
            throw new ClassNotFoundException("The class " + name + "not fond under "+ dir);
        }

        byte[] classBytes = loadClassByte(classFile);
        if (null == classBytes || classBytes.length == 0) {
            throw new ClassNotFoundException("load the class " + name + " failed");
        }

        return this.defineClass(name,classBytes,0,classBytes.length);
    }

    private byte[] loadClassByte(File classFile) {

        try (ByteArrayOutputStream baos = new ByteArrayOutputStream();
             FileInputStream fis = new FileInputStream(classFile)){
            int data;
            while ((data = fis.read()) != -1) {
                baos.write(data ^ EncryptUtils.ENCRYPT_FACTOR);
            }
            baos.flush();
            return baos.toByteArray();
        }catch (IOException e) {
            e.printStackTrace();
            return null;
        }

    }
}
