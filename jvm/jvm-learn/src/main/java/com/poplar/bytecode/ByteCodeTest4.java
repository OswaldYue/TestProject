package com.poplar.bytecode;

/**
 * 方法的静态分派。
 * Grandpa g1 = new Father();
 * 以上代码, g1的静态类型是Grandpa,而g1的实际类型(真正指向的类型)是Father.
 * 我们可以得出这样一个结论:变量的静态类型是不会发生变化的,而变量的实际类型则是可以发生变化的(多态的一种体现)，实际类型是在运行期方可确定
 *
 */
public class ByteCodeTest4 {

    public void test(Grandpa grandpa) {
        System.out.println("Grandpa");
    }

    public void test(Father father) {
        System.out.println("father");
    }

    public void test(Son son) {
        System.out.println("Son");
    }

    public static void main(String[] args) {
        ByteCodeTest4 byteCodeTest4 = new ByteCodeTest4();
        //方法重载,对于jvm来说，是一种静态的行为，这种所谓的静态行为，指的是在执行方法重载时，
        // 去调用这个方法，jvm唯一判断的依据，就是根据此方法本身它接受的参数的静态类型，而不是根据方法参数的实际类型去判断，
        // 当传入g1时，是根据静态类型去判断
        // 这种静态行为是在编译期就可以完全确定
        // 查看字节码即可一目了然
        Grandpa g1 = new Father();
        Grandpa g2 = new Son();

        byteCodeTest4.test(g1);//Grandpa
        byteCodeTest4.test(g2);//Grandpa

        byteCodeTest4.test((Father) g1);// Father
        byteCodeTest4.test((Son) g2);// Son
    }
}

class Grandpa {

}

class Father extends Grandpa {

}

class Son extends Father {

}