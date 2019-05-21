package com.mgw.netty.grpc;

import com.mgw.proto.MyRequest;
import com.mgw.proto.MyResponse;
import com.mgw.proto.TeacherServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.StatusRuntimeException;

import java.util.concurrent.TimeUnit;

public class TestGrpcClient {


    private final ManagedChannel managedChannel;
    private final TeacherServiceGrpc.TeacherServiceBlockingStub blockingStub;


    public TestGrpcClient(String host,int port) {

        this(ManagedChannelBuilder.forAddress(host,port).usePlaintext().build());



    }

    TestGrpcClient(ManagedChannel managedChannel) {

        this.managedChannel = managedChannel;
        this.blockingStub = TeacherServiceGrpc.newBlockingStub(managedChannel);

    }

    public void shutdown() throws InterruptedException{

        managedChannel.shutdown().awaitTermination(5, TimeUnit.SECONDS);

    }

    public void saySth(String sth) {

        System.out.println("username is " + sth + " ...");

        MyRequest request = MyRequest.newBuilder().setUsername(sth).build();

        MyResponse response = null;

        try {

            response = blockingStub.getRealNameByUserName(request);

        }catch (StatusRuntimeException e) {


            System.out.println("RPC failed: " + e.getMessage());
            return;
        }

        System.out.println("realname is " + response.getRealname() + " ..." );


    }



    public static void main(String[] args) throws Exception{

        TestGrpcClient testGrpcClient = new TestGrpcClient("localhost", 8899);

        try {

            testGrpcClient.saySth("ls");

        }finally {
            testGrpcClient.shutdown();
        }





    }



}
