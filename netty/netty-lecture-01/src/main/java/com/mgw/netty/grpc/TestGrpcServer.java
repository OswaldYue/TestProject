package com.mgw.netty.grpc;

import io.grpc.Server;
import io.grpc.ServerBuilder;

import java.io.IOException;

/**
 * gRpc案例
 * 1.简单数据的互传
 * 2.客户端发送简单数据 服务器返回流式数据
 *
 * */
public class TestGrpcServer {

    private Server server;

    public void start() throws IOException {

        int port = 8899;

        server = ServerBuilder.forPort(port).addService(new TeacherServiceImpl()).
                build().start();
        System.out.println("Server started, listening on " + port);

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {


            System.out.println("关闭JVM");
            TestGrpcServer.this.stop();

        }));

        System.out.println("***  server shutdown ***");

    }

    private void blockUntilShutdown() throws InterruptedException{

        if (null != server) {
            server.awaitTermination();
        }
    }

    private void stop() {

        if (null != server) {
            server.shutdown();
        }
    }


    public static void main(String[] args) throws Exception{

        TestGrpcServer testGrpcServer = new TestGrpcServer();

        testGrpcServer.start();
        testGrpcServer.blockUntilShutdown();


    }

}
