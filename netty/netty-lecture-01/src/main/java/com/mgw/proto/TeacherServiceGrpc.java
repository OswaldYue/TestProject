package com.mgw.proto;

import static io.grpc.MethodDescriptor.generateFullMethodName;
import static io.grpc.stub.ClientCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ClientCalls.asyncClientStreamingCall;
import static io.grpc.stub.ClientCalls.asyncServerStreamingCall;
import static io.grpc.stub.ClientCalls.asyncUnaryCall;
import static io.grpc.stub.ClientCalls.blockingServerStreamingCall;
import static io.grpc.stub.ClientCalls.blockingUnaryCall;
import static io.grpc.stub.ClientCalls.futureUnaryCall;
import static io.grpc.stub.ServerCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ServerCalls.asyncClientStreamingCall;
import static io.grpc.stub.ServerCalls.asyncServerStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnaryCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.20.0)",
    comments = "Source: Teacher.proto")
public final class TeacherServiceGrpc {

  private TeacherServiceGrpc() {}

  public static final String SERVICE_NAME = "com.mgw.proto.TeacherService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<com.mgw.proto.MyRequest,
      com.mgw.proto.MyResponse> getGetRealNameByUserNameMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "getRealNameByUserName",
      requestType = com.mgw.proto.MyRequest.class,
      responseType = com.mgw.proto.MyResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.mgw.proto.MyRequest,
      com.mgw.proto.MyResponse> getGetRealNameByUserNameMethod() {
    io.grpc.MethodDescriptor<com.mgw.proto.MyRequest, com.mgw.proto.MyResponse> getGetRealNameByUserNameMethod;
    if ((getGetRealNameByUserNameMethod = TeacherServiceGrpc.getGetRealNameByUserNameMethod) == null) {
      synchronized (TeacherServiceGrpc.class) {
        if ((getGetRealNameByUserNameMethod = TeacherServiceGrpc.getGetRealNameByUserNameMethod) == null) {
          TeacherServiceGrpc.getGetRealNameByUserNameMethod = getGetRealNameByUserNameMethod = 
              io.grpc.MethodDescriptor.<com.mgw.proto.MyRequest, com.mgw.proto.MyResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "com.mgw.proto.TeacherService", "getRealNameByUserName"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.mgw.proto.MyRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.mgw.proto.MyResponse.getDefaultInstance()))
                  .setSchemaDescriptor(new TeacherServiceMethodDescriptorSupplier("getRealNameByUserName"))
                  .build();
          }
        }
     }
     return getGetRealNameByUserNameMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.mgw.proto.TeacherRequest,
      com.mgw.proto.TeacherResponse> getGetTeacherByAgeMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "getTeacherByAge",
      requestType = com.mgw.proto.TeacherRequest.class,
      responseType = com.mgw.proto.TeacherResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
  public static io.grpc.MethodDescriptor<com.mgw.proto.TeacherRequest,
      com.mgw.proto.TeacherResponse> getGetTeacherByAgeMethod() {
    io.grpc.MethodDescriptor<com.mgw.proto.TeacherRequest, com.mgw.proto.TeacherResponse> getGetTeacherByAgeMethod;
    if ((getGetTeacherByAgeMethod = TeacherServiceGrpc.getGetTeacherByAgeMethod) == null) {
      synchronized (TeacherServiceGrpc.class) {
        if ((getGetTeacherByAgeMethod = TeacherServiceGrpc.getGetTeacherByAgeMethod) == null) {
          TeacherServiceGrpc.getGetTeacherByAgeMethod = getGetTeacherByAgeMethod = 
              io.grpc.MethodDescriptor.<com.mgw.proto.TeacherRequest, com.mgw.proto.TeacherResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
              .setFullMethodName(generateFullMethodName(
                  "com.mgw.proto.TeacherService", "getTeacherByAge"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.mgw.proto.TeacherRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.mgw.proto.TeacherResponse.getDefaultInstance()))
                  .setSchemaDescriptor(new TeacherServiceMethodDescriptorSupplier("getTeacherByAge"))
                  .build();
          }
        }
     }
     return getGetTeacherByAgeMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.mgw.proto.TeacherRequest,
      com.mgw.proto.TeacherResponseList> getGetTeachersWrapperByAgesMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "getTeachersWrapperByAges",
      requestType = com.mgw.proto.TeacherRequest.class,
      responseType = com.mgw.proto.TeacherResponseList.class,
      methodType = io.grpc.MethodDescriptor.MethodType.CLIENT_STREAMING)
  public static io.grpc.MethodDescriptor<com.mgw.proto.TeacherRequest,
      com.mgw.proto.TeacherResponseList> getGetTeachersWrapperByAgesMethod() {
    io.grpc.MethodDescriptor<com.mgw.proto.TeacherRequest, com.mgw.proto.TeacherResponseList> getGetTeachersWrapperByAgesMethod;
    if ((getGetTeachersWrapperByAgesMethod = TeacherServiceGrpc.getGetTeachersWrapperByAgesMethod) == null) {
      synchronized (TeacherServiceGrpc.class) {
        if ((getGetTeachersWrapperByAgesMethod = TeacherServiceGrpc.getGetTeachersWrapperByAgesMethod) == null) {
          TeacherServiceGrpc.getGetTeachersWrapperByAgesMethod = getGetTeachersWrapperByAgesMethod = 
              io.grpc.MethodDescriptor.<com.mgw.proto.TeacherRequest, com.mgw.proto.TeacherResponseList>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.CLIENT_STREAMING)
              .setFullMethodName(generateFullMethodName(
                  "com.mgw.proto.TeacherService", "getTeachersWrapperByAges"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.mgw.proto.TeacherRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.mgw.proto.TeacherResponseList.getDefaultInstance()))
                  .setSchemaDescriptor(new TeacherServiceMethodDescriptorSupplier("getTeachersWrapperByAges"))
                  .build();
          }
        }
     }
     return getGetTeachersWrapperByAgesMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static TeacherServiceStub newStub(io.grpc.Channel channel) {
    return new TeacherServiceStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static TeacherServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new TeacherServiceBlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static TeacherServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new TeacherServiceFutureStub(channel);
  }

  /**
   */
  public static abstract class TeacherServiceImplBase implements io.grpc.BindableService {

    /**
     */
    public void getRealNameByUserName(com.mgw.proto.MyRequest request,
        io.grpc.stub.StreamObserver<com.mgw.proto.MyResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getGetRealNameByUserNameMethod(), responseObserver);
    }

    /**
     */
    public void getTeacherByAge(com.mgw.proto.TeacherRequest request,
        io.grpc.stub.StreamObserver<com.mgw.proto.TeacherResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getGetTeacherByAgeMethod(), responseObserver);
    }

    /**
     */
    public io.grpc.stub.StreamObserver<com.mgw.proto.TeacherRequest> getTeachersWrapperByAges(
        io.grpc.stub.StreamObserver<com.mgw.proto.TeacherResponseList> responseObserver) {
      return asyncUnimplementedStreamingCall(getGetTeachersWrapperByAgesMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getGetRealNameByUserNameMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.mgw.proto.MyRequest,
                com.mgw.proto.MyResponse>(
                  this, METHODID_GET_REAL_NAME_BY_USER_NAME)))
          .addMethod(
            getGetTeacherByAgeMethod(),
            asyncServerStreamingCall(
              new MethodHandlers<
                com.mgw.proto.TeacherRequest,
                com.mgw.proto.TeacherResponse>(
                  this, METHODID_GET_TEACHER_BY_AGE)))
          .addMethod(
            getGetTeachersWrapperByAgesMethod(),
            asyncClientStreamingCall(
              new MethodHandlers<
                com.mgw.proto.TeacherRequest,
                com.mgw.proto.TeacherResponseList>(
                  this, METHODID_GET_TEACHERS_WRAPPER_BY_AGES)))
          .build();
    }
  }

  /**
   */
  public static final class TeacherServiceStub extends io.grpc.stub.AbstractStub<TeacherServiceStub> {
    private TeacherServiceStub(io.grpc.Channel channel) {
      super(channel);
    }

    private TeacherServiceStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected TeacherServiceStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new TeacherServiceStub(channel, callOptions);
    }

    /**
     */
    public void getRealNameByUserName(com.mgw.proto.MyRequest request,
        io.grpc.stub.StreamObserver<com.mgw.proto.MyResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getGetRealNameByUserNameMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void getTeacherByAge(com.mgw.proto.TeacherRequest request,
        io.grpc.stub.StreamObserver<com.mgw.proto.TeacherResponse> responseObserver) {
      asyncServerStreamingCall(
          getChannel().newCall(getGetTeacherByAgeMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public io.grpc.stub.StreamObserver<com.mgw.proto.TeacherRequest> getTeachersWrapperByAges(
        io.grpc.stub.StreamObserver<com.mgw.proto.TeacherResponseList> responseObserver) {
      return asyncClientStreamingCall(
          getChannel().newCall(getGetTeachersWrapperByAgesMethod(), getCallOptions()), responseObserver);
    }
  }

  /**
   */
  public static final class TeacherServiceBlockingStub extends io.grpc.stub.AbstractStub<TeacherServiceBlockingStub> {
    private TeacherServiceBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private TeacherServiceBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected TeacherServiceBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new TeacherServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public com.mgw.proto.MyResponse getRealNameByUserName(com.mgw.proto.MyRequest request) {
      return blockingUnaryCall(
          getChannel(), getGetRealNameByUserNameMethod(), getCallOptions(), request);
    }

    /**
     */
    public java.util.Iterator<com.mgw.proto.TeacherResponse> getTeacherByAge(
        com.mgw.proto.TeacherRequest request) {
      return blockingServerStreamingCall(
          getChannel(), getGetTeacherByAgeMethod(), getCallOptions(), request);
    }
  }

  /**
   */
  public static final class TeacherServiceFutureStub extends io.grpc.stub.AbstractStub<TeacherServiceFutureStub> {
    private TeacherServiceFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private TeacherServiceFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected TeacherServiceFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new TeacherServiceFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.mgw.proto.MyResponse> getRealNameByUserName(
        com.mgw.proto.MyRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getGetRealNameByUserNameMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_GET_REAL_NAME_BY_USER_NAME = 0;
  private static final int METHODID_GET_TEACHER_BY_AGE = 1;
  private static final int METHODID_GET_TEACHERS_WRAPPER_BY_AGES = 2;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final TeacherServiceImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(TeacherServiceImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_GET_REAL_NAME_BY_USER_NAME:
          serviceImpl.getRealNameByUserName((com.mgw.proto.MyRequest) request,
              (io.grpc.stub.StreamObserver<com.mgw.proto.MyResponse>) responseObserver);
          break;
        case METHODID_GET_TEACHER_BY_AGE:
          serviceImpl.getTeacherByAge((com.mgw.proto.TeacherRequest) request,
              (io.grpc.stub.StreamObserver<com.mgw.proto.TeacherResponse>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_GET_TEACHERS_WRAPPER_BY_AGES:
          return (io.grpc.stub.StreamObserver<Req>) serviceImpl.getTeachersWrapperByAges(
              (io.grpc.stub.StreamObserver<com.mgw.proto.TeacherResponseList>) responseObserver);
        default:
          throw new AssertionError();
      }
    }
  }

  private static abstract class TeacherServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    TeacherServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return com.mgw.proto.TeacherProto.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("TeacherService");
    }
  }

  private static final class TeacherServiceFileDescriptorSupplier
      extends TeacherServiceBaseDescriptorSupplier {
    TeacherServiceFileDescriptorSupplier() {}
  }

  private static final class TeacherServiceMethodDescriptorSupplier
      extends TeacherServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    TeacherServiceMethodDescriptorSupplier(String methodName) {
      this.methodName = methodName;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.MethodDescriptor getMethodDescriptor() {
      return getServiceDescriptor().findMethodByName(methodName);
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (TeacherServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new TeacherServiceFileDescriptorSupplier())
              .addMethod(getGetRealNameByUserNameMethod())
              .addMethod(getGetTeacherByAgeMethod())
              .addMethod(getGetTeachersWrapperByAgesMethod())
              .build();
        }
      }
    }
    return result;
  }
}
