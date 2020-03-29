package com.poplar.gc;

/**
 * 内存泄漏：异常处理不当
 *
 * 执行sql查询时，关闭要放入finally中
 * 执行流操作时，关闭流资源要放入finally中
 * */
public class Test2 {
    public static void main(String[] args) {

        try {

        }finally {

        }
    }

}
