package com.poplar.classload;

import com.sun.crypto.provider.AESKeyGenerator;

/**
 * 修改java的系统属性的方法:
 *  java -Djava.ext.dirs=./ com.poplar.classload.ClassLoadTest19
 *
 * 各加载器的路径是可以修改的，修改后会导致运行失败，ClassNotFoundExeception
 */
public class ClassLoadTest19 {
    public static void main(String[] args) {
        //该类默认有扩展类加载器加载的,但是如果我们把该类默认的加载路劲修改后，就会报错
        AESKeyGenerator aesKeyGenerator = new AESKeyGenerator();

        // sun.misc.Launcher$ExtClassLoader@610455d6
        System.out.println(aesKeyGenerator.getClass().getClassLoader());
        // sun.misc.Launcher$AppClassLoader@18b4aac2
        System.out.println(ClassLoadTest19.class.getClassLoader());
    }
}
