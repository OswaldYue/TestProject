package com.mgw.netty.nio;

import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;

/**
 *  NIO中的文件锁：共享锁 、排他锁
 *
 * */
public class TestNio10 {

    public static void main(String[] args) throws Exception{

        RandomAccessFile accessFile = new RandomAccessFile("TestNio10.txt", "rw");
        FileChannel channel = accessFile.getChannel();

        FileLock lock = channel.lock(3, 6, true);

        System.out.println("valid :" + lock.isValid());
        System.out.println("lock type :" + lock.isShared());

        lock.release();

        accessFile.close();


    }
}
