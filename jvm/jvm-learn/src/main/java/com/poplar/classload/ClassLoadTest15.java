package com.poplar.classload;

public class ClassLoadTest15 {

    public static void main(String[] args) {
        String[] strings = new String[1];
        //class [Ljava.lang.String;
        System.out.println(strings.getClass());

        /*
         * 说明:
         * Class</tt> objects for array classes are not created by class
         * loaders, but are created automatically as required by the Java runtime.
         * The class loader for an array class, as returned by {@link
         * Class#getClassLoader()} is the same as the class loader for its element
         * type 数组的classloader是与数据中元素类型的类加载一致
         * */
        //null
        System.out.println(strings.getClass().getClassLoader());

        ClassLoadTest14[] classLoadTest14s = new ClassLoadTest14[1];
        // sun.misc.Launcher$AppClassLoader@18b4aac2
        System.out.println(classLoadTest14s.getClass().getClassLoader());

        /*
         * if the element type is a primitive type, then the array class has no
         * class loader 如果数组元素是基本数据类型，则其classload是null
         * */
        int[] ints = new int[1];
        // null
        System.out.println(ints.getClass().getClassLoader());
    }
}
