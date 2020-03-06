package com.mgw.part1.chapter3;

public class OperandStackTest {

    public void testAdd() {
        byte i = 10;
        int j = 20;
        int k = i + j;
    }

    public int getSum() {
        byte i = 10;
        int j = 20;
        int k = i + j;
        return k;
    }

    public void testGetSum() {
        int i = getSum();
        int j = 20;

    }

    /**
     * iinc 指令函数，对指定的int类型的变量进行+1或+2操作，如i++,i–-
     * 根据描述可知该指令是需要指定参数的，1.指定变量，2.指定步帧
     * 因此它的结构应该是 iinc slot_n , number ，即，对指定slot_n的变量进行+=number的操作或i+=2
     * */
    public void add() {

        int i1 = 10;
        i1 = i1++;
//        System.out.println(i1); // 10

        int i2 = 10;
        i2 = ++i2;
//        System.out.println(i2); // 11

        int i3 = 10;
        int i4 = i3++ + ++i3;
//        System.out.println(i3); // 12
//        System.out.println(i4); // 22
    }

    public static void main(String[] args) {
        final OperandStackTest operandStackTest = new OperandStackTest();
        operandStackTest.add();
    }

}
