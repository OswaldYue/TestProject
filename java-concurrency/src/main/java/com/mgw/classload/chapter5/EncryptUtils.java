package com.mgw.classload.chapter5;

import java.io.FileInputStream;
import java.io.FileOutputStream;

public final class EncryptUtils {

    public static final byte ENCRYPT_FACTOR = (byte) 0xff;

    private EncryptUtils(){

    }

    public static void doEncrypt(String source,String target) {

        try(final FileInputStream fis = new FileInputStream(source);
            final FileOutputStream fos = new FileOutputStream(target);) {

            int data;
            while ((data = fis.read()) != -1) {
                fos.write(data ^ ENCRYPT_FACTOR);
            }
            fos.flush();
        }catch (Exception e) {
            
        }

    }

    public static void main(String[] args) {

        doEncrypt("E:\\code\\AppData\\classloader3\\aaaa.txt","E:\\code\\AppData\\classloader3\\bbbb.txt");
        doEncrypt("E:\\code\\AppData\\classloader3\\bbbb.txt","E:\\code\\AppData\\classloader3\\cccc.txt");
    }

}
