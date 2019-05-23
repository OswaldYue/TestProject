package com.mgw.netty.grpc;

import com.mgw.proto.StreamRequest;
import com.mgw.proto.StreamResponse;
import com.mgw.proto.TeacherServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;

import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;

public class TestGrpcClient {


    private final ManagedChannel managedChannel;

    //阻塞式
    private final TeacherServiceGrpc.TeacherServiceBlockingStub blockingStub;

    //异步式
    private final TeacherServiceGrpc.TeacherServiceStub stub ;

    public TestGrpcClient(String host,int port) {

        this(ManagedChannelBuilder.forAddress(host,port).usePlaintext().build());

    }

    TestGrpcClient(ManagedChannel managedChannel) {

        this.managedChannel = managedChannel;
        this.blockingStub = TeacherServiceGrpc.newBlockingStub(managedChannel);
        this.stub = TeacherServiceGrpc.newStub(managedChannel);
    }

    public void shutdown() throws InterruptedException{

        managedChannel.shutdown().awaitTermination(5, TimeUnit.SECONDS);

    }

    public void saySth(String sth) {

//        /*
//        * 1.单一消息类型
//        * */
//
//        System.out.println("username is " + sth + " ...");
//
//        MyRequest request = MyRequest.newBuilder().setUsername(sth).build();
//
//        MyResponse response = null;
//
//        try {
//
//            response = blockingStub.getRealNameByUserName(request);
//
//        }catch (StatusRuntimeException e) {
//
//
//            System.out.println("RPC failed: " + e.getMessage());
//            return;
//        }
//
//        System.out.println("realname is " + response.getRealname() + " ..." );
//
//        System.out.println("-------------------------------------------------------------");
//
//        /*
//        * 2.客户端发送单一消息类型 服务器返回流式数据
//        * */
//        Iterator<TeacherResponse> teachers = blockingStub.getTeacherByAge(TeacherRequest.newBuilder().setAge(100).build());
//        while (teachers.hasNext()) {
//
//            TeacherResponse teacherResponse = teachers.next();
//            System.out.println(teacherResponse);
//
//        }
//
//        System.out.println("-------------------------------------------------------------");
//
//        /*
//        * 3.客户端发送流式数据 服务器端返回简单数据
//        * */
//        StreamObserver<TeacherResponseList> teacherResponseListStreamObserver = new StreamObserver<TeacherResponseList>() {
//            @Override
//            public void onNext(TeacherResponseList value) {
//
//                value.getTeacherResponseList().forEach(teacherResponse -> {
//
//                    System.out.println("teacherResponse -> " + teacherResponse);
//                });
//
//            }
//
//            @Override
//            public void onError(Throwable t) {
//
//                System.out.println(t.getMessage());
//            }
//
//            @Override
//            public void onCompleted() {
//
//                System.out.println("onCompleted -> 无");
//
//            }
//        };
//
//        //客户端发送流式数据必须异步调用
//        StreamObserver<TeacherRequest> teacherRequestStreamObserver = stub.getTeachersWrapperByAges(teacherResponseListStreamObserver);
//
//        teacherRequestStreamObserver.onNext(TeacherRequest.newBuilder().setAge(30).build());
//        teacherRequestStreamObserver.onNext(TeacherRequest.newBuilder().setAge(40).build());
//        teacherRequestStreamObserver.onNext(TeacherRequest.newBuilder().setAge(50).build());
//        teacherRequestStreamObserver.onNext(TeacherRequest.newBuilder().setAge(60).build());
//
//        teacherRequestStreamObserver.onCompleted();
//
//        try {
//            Thread.sleep(10000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }

        /*
        * 4.客户端发送流式数据 服务器端返回流式数据
        *
        * */

        System.out.println("-------------------------------------------------------------");
        StreamObserver<StreamRequest> streamRequestStreamObserver = stub.biTalk(new StreamObserver<StreamResponse>() {
            @Override
            public void onNext(StreamResponse value) {

                System.out.println("onNext() <-> " + value.getResponseInfo());

            }

            @Override
            public void onError(Throwable t) {
                System.out.println("onError() <-> " );

                t.printStackTrace();
            }

            @Override
            public void onCompleted() {
                System.out.println("onCompleted() <-> " + "completed");
            }
        });

        for (int i = 0 ; i < 10 ; i++) {

            streamRequestStreamObserver.onNext(StreamRequest.newBuilder().setRequestInfo(LocalDateTime.now().toString()).build());

            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

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
