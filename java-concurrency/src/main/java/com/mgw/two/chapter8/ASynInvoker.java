package com.mgw.two.chapter8;

/**
 * 异步调用
 *
 *  Future -> 代表的是未来的一个凭据
 *  FutureTask -> 将调用逻辑进行了隔离
 *  FutureService -> 桥接 Future和FutureTask
 *
 *
 * */
public class ASynInvoker {

    public static void main(String[] args) throws InterruptedException {

        FutureService futureService = new FutureService();
        // 提交异步任务
        // System.out::println 进行一个回调返回  直接打印出结果  可以直接存入数据库
        Future<String> future = futureService.submit(() -> {

            try {
                // 很长时间的正式工作
                Thread.sleep(10_000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "FINISH";
        },System.out::println);

        System.out.println("=================================");
        System.out.println("do other thing.");
//        Thread.sleep(10_000);
        System.out.println("=================================");
        // 等待异步任务完成 并返回最后的真正的结果
//        System.out.println(future.get());




    }

}
