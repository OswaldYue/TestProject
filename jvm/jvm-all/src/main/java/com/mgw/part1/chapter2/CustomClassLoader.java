package com.mgw.part1.chapter2;

import java.io.FileNotFoundException;

public class CustomClassLoader extends ClassLoader {

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        try {
            byte[] result = getClassFromCustomPath(name);
            if (result == null) {
                throw new FileNotFoundException();
            }
            return defineClass(name,result,0,result.length);
        }catch (FileNotFoundException e) {

        }throw new ClassNotFoundException();
    }

    private byte[] getClassFromCustomPath(String name) {

        // 根据类名加载指定类 细节略
        // 如果对类文件进行加密了，此处就需要自定义解密


        return null;
    }
}
