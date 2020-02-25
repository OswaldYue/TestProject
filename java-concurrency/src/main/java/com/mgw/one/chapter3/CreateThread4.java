package com.mgw.one.chapter3;

public class CreateThread4 {

    private static int count = 1;

    public static void main(String[] args) {

        try {
            for (int i = 0 ; i < Integer.MAX_VALUE ; i++) {
                count++;
                byte[] date = new byte[1024 * 1024 * 2];
//                new Thread(() -> {
//                    try {
//                        Thread.sleep(1);
//                    }catch (Exception e) {
//                        e.printStackTrace();
//                    }
//                }).start();
            }


        }catch (Error err) {

            err.printStackTrace();

        }

        System.out.println("total created thread nums=>" + count);

    }
}
