package com.poplar.classload;

import java.time.LocalDateTime;

/**
 * Created By poplar on 2019/11/7
 * <p>
 * 当一个接口在初始化时，并不要求其父接口都完成了初始化,但是其父接口会被加载
 * 只有在真正使用到父接口的时候（如引用接口中定义的常量），才会初始化
 * </p>
 */
public class ClassLoadTest5 {
    public static void main(String[] args) {
        // 编译时就已经确定，所以删除Student5与MyChild文件都是ok的
//        System.out.println(MyChild.c);
        // 编译时无法确定，需要运行时确定，所以不能删Student5与MyChild文件,运行结果来看Student5.thread中的代码没有执行，
        // 故其父接口只是被加载并没有被初始化
        System.out.println(MyChild.b);

        // 编译时就已经确定，所以删除Student5与MyChild文件都是ok的
//        System.out.println(MyChild.a);

    }

}

interface Student5 {

    int a = 9; //前面省了public static final

    Thread thread = new Thread() {
        {
            System.out.println("thread 初始化了");//如果父接口初始化了这句应该输出
        }
    };
}

interface MyChild extends Student5 {     //接口属性默认是 public static final
    String b = LocalDateTime.now().toString();
    int c = 10;
}
