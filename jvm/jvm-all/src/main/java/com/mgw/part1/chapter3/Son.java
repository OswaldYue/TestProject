package com.mgw.part1.chapter3;

class Father {

    public Father() {
        System.out.println("Father的构造方法");
    }

    public static void showStatic(String str) {
        System.out.println("father " + str);
    }

    public final void showFinal() {
        System.out.println("father show final");
    }

    public void showCommon() {
        System.out.println("father 的普通方法");
    }
}

/**
 * 虚方法与非虚方法
 * */
public class Son extends Father{

    public Son() {
        super();
    }

    // 不是重写的父类的方法
    public static void showStatic(String str) {
        System.out.println("son " + str);
    }

    private void showPrivate(String str) {
        System.out.println("son private " + str);
    }

    public void show() {
        // invokestatic
        showStatic("AAA");
        // invokestatic
        super.showStatic("BBB");
        // invokespecial
        showPrivate("CCC");
        // invokespecial
        super.showCommon();
        // invokevirtual
        showFinal();
        // invokevirtual
        showCommon();
        // invokevirtual
        info();
    }

    public void info() {

    }

    public static void main(String[] args) {
        final Son son = new Son();
        son.show();
    }
}
