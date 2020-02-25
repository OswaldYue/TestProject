package com.mgw.classload.chapter5;

public class SimpleEncrypt {

    private static final String plain = "Hello ClassLoader";

    private static final byte ENCRYPT_FACTOR = (byte) 0xff;

    public static void main(String[] args) {
        final byte[] bytes = plain.getBytes();
        final byte[] encrypt = new byte[bytes.length];
        final byte[] decrypt = new byte[bytes.length];

        // 加密
        for (int i = 0; i < bytes.length; i++) {
            encrypt[i] = (byte) (bytes[i] ^ ENCRYPT_FACTOR);
        }
        System.out.println(new String(encrypt));

        // 解密
        for (int i = 0; i < encrypt.length; i++) {
            decrypt[i] = (byte) (encrypt[i] ^ ENCRYPT_FACTOR);
        }
        System.out.println(new String(decrypt));

        
    }

}
