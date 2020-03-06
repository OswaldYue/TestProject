package com.mgw.part1.chapter3;

import java.util.Date;

public class LocalVariablesTest {

    private int count = 1;

    public LocalVariablesTest() {
        this.count = 10;
    }

    public static void main(String[] args) {
        final LocalVariablesTest localVariablesTest = new LocalVariablesTest();
        int num = 10;
        localVariablesTest.test1();
    }

    public void test1() {
        Date date = new Date();
        String name1 = "AAAA";
        String info = test2(date,name1);
        System.out.println(date+name1);
    }

    public static void testStatic() {

        // 从字节码的角度看，static方法中不能使用this的原因?
        // 因为this不存在与当前的局部变量表中
//        System.out.println(this.count);
    }

    public String test2(Date pDate, String name2) {
        pDate = null;
        name2 = "BBB";
        // weight在局部变量表中占两个slot
        double weight = 130.0;
        char gender = '男';

        return pDate+name2;

    }

    public void test3() {
        this.count++;
    }

    public void test4() {
        int a = 0;
        {
            int b = 0;
            b = a + 1;
        }
        //c在局部变量表中的slot使用的是前面已经销毁了的b的slot
        int c = a + 1;
    }

    /**
     * 变量的分类；
     *  按数据类型分 : 基本数据类型  引用数据类型
     *  按在类中声明的位置来分: 成员变量  : 在使用前都经过默认初始化赋值
     *                          类变量 : 在link的prepare时给变量赋默认值  ---> 初始化阶段给类变量显示赋值，即静态代码块赋值
     *                          实例变量 : 随着对象的创建，会在堆空间分配实例变量空间，并进行默认赋值
     *                      局部变量； 在使用前必须显示赋值
     * */
}
