package com.poplar.memory;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;

/**
 *
 * 元空间内存溢出测试
 * 设置元空间大小：-XX:MaxMetaspaceSize=100m
 * 关于元空间参考：https://www.infoq.cn/article/java-permgen-Removed
 */
public class OutOfMataSpaceErrorTest {

    public static void main(String[] args) throws InterruptedException {
        //使用动态代理动态生成类，不断放入元空间
        while (true) {
            Enhancer enhancer = new Enhancer();
            enhancer.setSuperclass(OutOfMataSpaceErrorTest.class);
            enhancer.setUseCache(false);
            enhancer.setCallback((MethodInterceptor) (obj, method, ags, proxy) ->
                    proxy.invokeSuper(obj, ags));

            Thread.sleep(100);

            System.out.println("Hello World");

            enhancer.create();// java.lang.OutOfMemoryError: Metaspace
        }
    }

}
