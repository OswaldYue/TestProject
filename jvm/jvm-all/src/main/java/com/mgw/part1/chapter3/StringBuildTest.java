package com.mgw.part1.chapter3;

/**
 * 问: 定义在方法中的局部变量一定是线程安全的吗？
 * 不一定，得具体问题具体对待
 *
 * 何为线程安全?
 *  如果只有一个线程操作此数据，则必是线程安全的
 *  如果有多个线程操作此数据，此数据是共享数据，如果不考虑同步机制，会出现线程安全问题
 * */
public class StringBuildTest {

    // builder的声明方式是线程安全的
    public void methodA() {
        final StringBuilder builder = new StringBuilder();
        builder.append("a");
        builder.append("b");
    }

    // builder的声明方式不是线程安全的
    public void methodB(StringBuilder builder) {
        builder.append("a");
        builder.append("b");
    }

    // builder的声明方式不是线程安全的
    public StringBuilder methodC() {
        final StringBuilder builder = new StringBuilder();
        builder.append("a");
        builder.append("b");

        return builder;
    }

    // builder的声明方式是线程安全的
    public String methodD() {
        final StringBuilder builder = new StringBuilder();
        builder.append("a");
        builder.append("b");

        return builder.toString();
    }
}

