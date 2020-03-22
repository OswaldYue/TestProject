package com.poplar.bytecode;

/**
 *
 * 动态分派(编译期不能确定，只有运行期才可以确定)的演示与证明：
 * 在动态分派中虚拟机是如何知道要调用那个方法的？
 */
/**
 * 方法的动态分派
 * 方法的动态分派涉及到一个重要概念:方法接收者。
 * invokevirtual字节码指令的多态查找流程:
 * 1.到操作数栈顶，去寻找栈顶元素所指向的对象的实际类型，不是静态类型
 * 2.如果在这个类型当中，它寻找到了与常量池当中的描述符和名称都相同的方法，并具有访问权限，如果通过，直接返回目标方法的直接引用，调用
 * 3.如果找不到，就按继承的层次关系，从子类往父类，依次对各个父类，重复查找的流程，直到找到为止，一直找不到就抛异常
 *
 * 比较方法重载(overload)与方法重写(overwrite) ,我们可以得到这样一个结论:
 * 方法重载是静态的,是编译期行为;
 * 方法重写是动态的,是运行期行为。
 */
/**
 * 针对于方法调用动态分派的过程，虚拟机会在类的方法区建立一个虚方法表的一种数据结构(virtual method table或者vtable)
 * 针对于invokeinterface指令来说，虚拟机会建立一个叫做接口方法表的数据结构(interface method table或者itable)
 *
 * */
public class DynamicDispatch {

    static abstract class Human {
        public abstract void hello();
    }

    static class Man extends Human {
        @Override
        public void hello() {
            System.out.println("Hello Man");
        }
    }

    static class Woman extends Human {
        @Override
        public void hello() {
            System.out.println("Hello Woman");
        }

        public void method() {}

    }

    public static void main(String[] args) {
        Human man = new Man();
        Human woMan = new Woman();
        man.hello();
        woMan.hello();

        man = new Woman();
        man.hello();

        // 从字节码角度来看为什么下面代码错误?
        // 原因如下:字节码编译后，如果如下代码要成立,则为
        // invokevirtual #xx                  // Method main/java/com/poplar/bytecode/DynamicDispatch$Human.method:()V
        // 也就是说，编译后其依然使用的是父类的method方法，直到运行时，才会去做动态寻找，然后很遗憾，method方法在父类中没有，自然编译报错
//        man.method();

    /*public static void main(java.lang.String[]);
    descriptor: ([Ljava/lang/String;)V
    flags: ACC_PUBLIC, ACC_STATIC
    Code:
      stack=2, locals=3, args_size=1
         0: new           #2                  // class main/java/com/poplar/bytecode/DynamicDispatch$Man
         3: dup
         4: invokespecial #3                  // Method main/java/com/poplar/bytecode/DynamicDispatch$Man."<init>":()V
         7: astore_1
         8: new           #4                  // class main/java/com/poplar/bytecode/DynamicDispatch$Woman
        11: dup
        12: invokespecial #5                  // Method main/java/com/poplar/bytecode/DynamicDispatch$Woman."<init>":()V
        15: astore_2
        16: aload_1 从局部变量加载一个引用 aload1是加载索引为1的引用（man），局部变量有三个（0：args; 1 :man ; 2 :woMan）
        17: invokevirtual #6                  // Method main/java/com/poplar/bytecode/DynamicDispatch$Human.hello:()V
        20: aload_2 加载引用woMan
        21: invokevirtual #6                  // Method main/java/com/poplar/bytecode/DynamicDispatch$Human.hello:()V
        24: new           #4                  // class main/java/com/poplar/bytecode/DynamicDispatch$Woman
        27: dup
        28: invokespecial #5                  // Method main/java/com/poplar/bytecode/DynamicDispatch$Woman."<init>":()V
        31: astore_1
        32: aload_1
        33: invokevirtual #6                  // Method main/java/com/poplar/bytecode/DynamicDispatch$Human.hello:()V
        36: return
      LineNumberTable:
        line 28: 0
        line 29: 8
        line 30: 16
        line 31: 20
        line 33: 24
        line 34: 32
        line 36: 36
      LocalVariableTable:
        Start  Length  Slot  Name   Signature
            0      37     0  args   [Ljava/lang/String;
            8      29     1   man   Lmain/java/com/poplar/bytecode/DynamicDispatch$Human;
           16      21     2 woMan   Lmain/java/com/poplar/bytecode/DynamicDispatch$Human;
    }
    invokevirtual 运行期执行的时候首先：
    找到操作数栈顶的第一个元素它所指向对象的实际类型，在这个类型里边，然后查找和常量里边Human的方法描述符和方法名称都一致的
    方法，如果在这个类型下，常量池里边找到了就会返回实际对象方法的直接引用。

    如果找不到，就会按照继承体系由下往上(Man–>Human–>Object)查找，查找匹配的方式就是
    上面描述的方式，一直找到位为止。如果一直找不到就会抛出异常。

    比较方法重载（overload）和方法重写（overwrite），我们可以得出这样的结论：
    方法重载是静态的，是编译器行为；方法重写是动态的，是运行期行为。
   */
    }
}
