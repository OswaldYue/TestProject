package com.mgw.three.atomic;

import sun.misc.Unsafe;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.HashSet;
import java.util.Set;

public class UnsafeFooTest {

    private static Unsafe getUnsafe() {
        Unsafe unsafe = null;
        try {
            Field f = Unsafe.class.getDeclaredField("theUnsafe");
            f.setAccessible(true);
            unsafe =  (Unsafe) f.get(null);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return unsafe;
    }

    static class Simple {

        private long l = 0;

        private int i = 10;

        public Simple() {
            this.l = 1;
            System.out.println("=====================");
        }

        public long get() {
            return this.l;
        }
    }

    static class Guard {

        private int ACCESS_ALLOWED = 1;

        private boolean allow() {
            return 42 == ACCESS_ALLOWED;
        }

        public void work() {
            if (allow()) {
                System.out.println("I am working by allowed.");
            }
        }
    }

    private static void test1() throws IllegalAccessException, InstantiationException, ClassNotFoundException {

        final Simple simple = new Simple();
        System.out.println(simple.get());

        // 会导致Simple的初始化
        final Simple simple1 = Simple.class.newInstance();

        // 不会导致Simple的初始化
        Class.forName("com.mgw.three.atomic.UnsafeFooTest$Simple");

        Unsafe unsafe = getUnsafe();
        // 不会导致Simple的初始化
        Simple simple2 = (Simple)unsafe.allocateInstance(Simple.class);
        System.out.println(simple2.get()); // 0
        // class com.mgw.three.atomic.UnsafeFooTest$Simple
        System.out.println(simple2.getClass());
        // sun.misc.Launcher$AppClassLoader@18b4aac2
        System.out.println(simple2.getClass().getClassLoader());
    }

    private static void test2() throws NoSuchFieldException {
        Unsafe unsafe = getUnsafe();
        // Unsafe绕过原始变量改变字段值
        Guard guard = new Guard();
        guard.work();

        final Field f = guard.getClass().getDeclaredField("ACCESS_ALLOWED");
        // 绕过正常的程序，直接放一个值到一块内存中
        unsafe.putInt(guard,unsafe.objectFieldOffset(f),42);
        // I am working by allowed.
        guard.work();

    }

    private static byte[] loadClassContent() throws IOException {
        File file = new File("E:\\code\\githug-code\\TestProject\\java-concurrency\\target\\classes\\A.class");
        FileInputStream fis = new FileInputStream(file);
        byte[] content = new byte[(int) file.length()];
        fis.read(content);
        fis.close();

        return content;
    }

    /**
     * Unsafe充当classload去加载一个类
     * */
    private static void test3() throws Exception {

        byte[] bytes = loadClassContent();
        Unsafe unsafe = getUnsafe();
        final Class<?> aClass = unsafe.defineClass(null, bytes, 0, bytes.length,null,null);
        int v = (Integer)aClass.getMethod("get").invoke(aClass.newInstance(),null);
        System.out.println(v);
    }

    private static long sizeof(Object obj) {

        final Unsafe unsafe = getUnsafe();
        Set<Field> fields = new HashSet<>();

        Class<?> c = obj.getClass();
        while (c != Object.class) {
            final Field[] declaredFields = c.getDeclaredFields();
            for (Field f: declaredFields) {
                if ((f.getModifiers() & Modifier.STATIC) == 0) {
                    fields.add(f);
                }
            }
            c = c.getSuperclass();
        }

        long maxOffSet = 0;
        for (Field f : fields) {
            long offSet = unsafe.objectFieldOffset(f);
            if (offSet > maxOffSet) {
                maxOffSet = offSet;
            }
        }

        return ((maxOffSet/8) + 1) * 8;
    }

    /**
     * 借助于Unsafe去实现C和C++中的sizeof() 方法
     * */
    private static void test4() throws Exception {

        final long l = sizeof(new Simple());
        System.out.println(l);
    }

    public static void main(String[] args) throws Exception {

//        test1();
//        test2();
//        test3();
        test4();
    }
}
