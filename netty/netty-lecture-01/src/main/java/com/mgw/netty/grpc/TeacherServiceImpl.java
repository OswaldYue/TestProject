package com.mgw.netty.grpc;

import com.mgw.proto.*;
import io.grpc.stub.StreamObserver;

import java.util.UUID;

public class TeacherServiceImpl extends TeacherServiceGrpc.TeacherServiceImplBase {

    /**
     *
     * 1.单一消息类型
     *
     * */
    @Override
    public void getRealNameByUserName(MyRequest request, StreamObserver<MyResponse> responseObserver) {


        System.out.println("myRequest username : " + request.getUsername());

        responseObserver.onNext(MyResponse.newBuilder().setRealname("张三三").build());

        responseObserver.onCompleted();
    }

    /**
     * 2.客户端发送单一消息类型 服务器返回流式数据
     *
     * */

    @Override
    public void getTeacherByAge(TeacherRequest request, StreamObserver<TeacherResponse> responseObserver) {

        System.out.println("getTeacherByAge() : teacher age " + request.getAge());

        responseObserver.onNext(TeacherResponse.newBuilder().setName("王三三").setAge(30).setCity("SZ").build());
        responseObserver.onNext(TeacherResponse.newBuilder().setName("李三三").setAge(40).setCity("GZ").build());
        responseObserver.onNext(TeacherResponse.newBuilder().setName("赵三三").setAge(50).setCity("BJ").build());
        responseObserver.onNext(TeacherResponse.newBuilder().setName("张三三").setAge(60).setCity("SH").build());

        responseObserver.onCompleted();

    }

    /**
     * 3.客户端发送流式数据 服务器端返回简单数据
     *
     * */
    @Override
    public StreamObserver<TeacherRequest> getTeachersWrapperByAges(StreamObserver<TeacherResponseList> responseObserver) {

        //通过回调完成
        return new StreamObserver<TeacherRequest>() {

            @Override
            public void onNext(TeacherRequest value) {
                System.out.println("onNext : " + value.getAge());
            }

            @Override
            public void onError(Throwable t) {

                System.out.println("onError : " + t.getMessage());


            }

            @Override
            public void onCompleted() {

                TeacherResponse teacherResponse = TeacherResponse.newBuilder().
                        setName("张三").setAge(20).setCity("SZ").build();
                TeacherResponse teacherResponse2 = TeacherResponse.newBuilder().
                        setName("李四").setAge(30).setCity("GZ").build();

                TeacherResponseList teacherResponseList = TeacherResponseList.newBuilder().addTeacherResponse(teacherResponse).
                        addTeacherResponse(teacherResponse2).build();

                responseObserver.onNext(teacherResponseList);
                responseObserver.onCompleted();

            }
        };

    }


    /**
     * 4.客户端发送流式数据 服务器端返回流式数据
     *
     * */
    @Override
    public StreamObserver<StreamRequest> biTalk(StreamObserver<StreamResponse> responseObserver) {

        return new StreamObserver<StreamRequest>() {
            @Override
            public void onNext(StreamRequest value) {
                System.out.println("biTalk onNext : " + value.getRequestInfo() );

                responseObserver.onNext(StreamResponse.newBuilder().setResponseInfo(UUID.randomUUID().toString()).build());

            }

            @Override
            public void onError(Throwable t) {
                System.out.println("biTalk onNext : " + t.getMessage());
                t.printStackTrace();
            }

            @Override
            public void onCompleted() {
                responseObserver.onCompleted();
            }
        };


    }
}
