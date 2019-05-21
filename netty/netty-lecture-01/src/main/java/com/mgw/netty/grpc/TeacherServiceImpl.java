package com.mgw.netty.grpc;

import com.mgw.proto.MyRequest;
import com.mgw.proto.MyResponse;
import com.mgw.proto.TeacherServiceGrpc;
import io.grpc.stub.StreamObserver;

public class TeacherServiceImpl extends TeacherServiceGrpc.TeacherServiceImplBase {
    @Override
    public void getRealNameByUserName(MyRequest request, StreamObserver<MyResponse> responseObserver) {


        System.out.println("myRequest username : " + request.getUsername());

        responseObserver.onNext(MyResponse.newBuilder().setRealname("张三三").build());

        responseObserver.onCompleted();
    }
}
