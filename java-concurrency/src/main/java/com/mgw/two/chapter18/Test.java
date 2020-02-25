package com.mgw.two.chapter18;

/**
 * Active Object 设计模式
 * */
public class Test {

    public static void main(String[] args) {

//        System.gc();

        ActiveObject activeObject = ActiveObjectFactory.createActiveObject();
        new MakeClientThread(activeObject,"MMM").start();
        new MakeClientThread(activeObject,"NNN").start();

        new DisplayClientThread("AAA",activeObject).start();
    }

}
