package com.poplar.gc;

/**
 * 内存泄漏：对象定义在错误的范围
 * */
public class Test1 {
}

/**
 * 如果Foo1实例对象的生命较长，会导致临时性内存泄漏(这里的names变量其实只有临时作用)
 * */
class Foo1 {

    private String[] names;

    public void doIt(int length) {

        if( names == null || names.length < length) {
            names = new String[length];

            // do names
        }
    }
}


/**
 * jvm喜欢生命周期短的对象，下面这样做已经足够高效
 * */
class Foo2 {

    public void doIt(int length) {

        String[] names = new String[length];

        // do names

    }
}