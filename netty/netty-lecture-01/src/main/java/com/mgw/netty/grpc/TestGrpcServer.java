package com.mgw.netty.grpc;

import io.grpc.Server;
import io.grpc.ServerBuilder;

import java.io.IOException;

/**
 * gRpc案例
 *
 *
 * */
public class TestGrpcServer {

    private Server server;

    public void start() throws IOException {

        int port = 8899;

        server = ServerBuilder.forPort(port).addService(new TeacherServiceImpl()).
                build().start();
        System.out.println("Server started, listening on " + port);

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
