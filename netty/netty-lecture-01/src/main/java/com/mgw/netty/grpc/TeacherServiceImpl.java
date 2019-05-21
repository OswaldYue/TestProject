package com.mgw.netty.grpc;

import com.mgw.proto.*;
import io.grpc.stub.StreamObserver;

public class TeacherServiceImpl extends TeacherServiceGrpc.TeacherServiceImplBase {

    /**
     *
     * 1.简单数据的互传
     *
     * */
    @Override
    public void getRealNameByUserName(MyRequest request, StreamObserver<MyResponse> responseObserver) {


        System.out.println("myRequest username : " + request.getUsername());

        responseObserver.onNext(MyResponse.newBuilder().setRealname("张三三").build());

        responseObserver.onCompleted();
    }

    /**
     * 2.客户端发送简单数据 服务器返回流式数据
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
}
