package com.poplar.classload;

import java.lang.reflect.Method;

/**
 * 类加载的双亲委托模型的好处:
 * 1.可以确保java核心库的类型安全：所有的java应用都至少会使用java.lang.Object类，也就是说在运行期，java.lang.Object这个类
 * 会被加载到java虚拟机中；如果这个加载过程是由java应用自己的类加载器所完成的，那么很可能就会在jvm中存在多个版本的java.lang.Object类，
 * 而且这些类之间还是不兼容的，相互不可见的（正是命名空间发挥的作用）。
 * 借助于双亲委托机制，java核心类库中的类加载工作都是由启动类加载器来统一完成，从而确保了java应用所使用的都是同一个版本的核心类库，他们之间是相互兼容的。
 * 2.确保java核心类库所提供的类不会被自定义的类所替代
 * 3.不同的类加载器可以为相同名称(binary name)的类创建额外的命名空间。相同名称的类可以并存在jvm中，只需要不同的类加载器来加载它们即可。不同类加载器所加载的类
 * 之间是不兼容的，这相当于在java虚拟机内部创建了一个又一个相互隔离的java类空间，这类技术在很多的框架中都得到了实际应用
 *
 *
 * */
/**
 * 1.每个类加载器都有自己的命名空间，命名空间由该加载器及所有父加载器所加载的类构成；
 * 2.在同一个命名空间中，不会出现类的完整名字（包括类的包名）相同的两个类；
 * 3.在不同的命名空间中，有可能会出现类的完整名字（包括类的包名）相同的两个类；
 * 4.同一命名空间内的类是互相可见的，非同一命名空间内的类是不可见的；
 * 5.子加载器可以见到父加载器加载的类，父加载器也不能见到子加载器加载的类。
 */
public class ClassLoadTest21 {

    public static void main(String[] args) throws Exception {
        ClassLoadTest16 loader1 = new ClassLoadTest16("load1");
        ClassLoadTest16 loader2 = new ClassLoadTest16("load2");

        loader1.setPath("E:\\code\\AppData\\classloader4\\");
        loader2.setPath("E:\\code\\AppData\\classloader4\\");

        Class<?> clazz1 = loader1.loadClass("com.poplar.classload.MyPerson");
        Class<?> clazz2 = loader2.loadClass("com.poplar.classload.MyPerson");


        // 使用自定义加载器加载
        //由于clazz1和clazz2分别由不同的类加载器所加载，所以他们处于不同的名称空间里
        //clazz1与clazz2分别由不同的类加载器加载的
        System.out.println(clazz1 == clazz2);//false

        Object object1 = clazz1.newInstance();
        Object object2 = clazz2.newInstance();

        Method method = clazz1.getMethod("setMyPerson", Object.class);
        //此处报错，java.lang.reflect.InvocationTargetException
        // loader1和loader2所处不用的命名空间，是两个不同的类加载器，java中的对象相等，要求类全名+类加载器都相等才是对象相等
        // 因为命名空间不同，故转化自然会发生异常
        method.invoke(object1, object2);

       /*
       输出结果：
        findClass,输出这句话说明我们自己的类加载器加载了指定的类
        findClass,输出这句话说明我们自己的类加载器加载了指定的类
        false
        Exception in thread "main" java.lang.reflect.InvocationTargetException
        at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
        at sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)
        at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
        at java.lang.reflect.Method.invoke(Method.java:498)
        at com.poplar.classload.ClassLoadTest21.main(ClassLoadTest21.java:25)
        Caused by: java.lang.ClassCastException: com.poplar.classload.MyPerson cannot be cast to com.poplar.classload.MyPerson*/
    }
}
