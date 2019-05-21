package com.mgw.protobuf;

import com.google.protobuf.InvalidProtocolBufferException;

/**
 * 测试protobuf的使用
 *
 * */
public class ProtoBufTest {

    public static void main(String[] args) throws InvalidProtocolBufferException {

        MyDataInfo.Student zs = MyDataInfo.Student.newBuilder().setName("zs").
                setAge(20).setAddress("xxx.xxx.xxx").build();

        byte[] student2ByteArray = zs.toByteArray();

        MyDataInfo.Student student = MyDataInfo.Student.parseFrom(student2ByteArray);

        System.out.println(student);
    }

}
