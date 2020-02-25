package com.mgw.one.chapter3;

public class CreateThread3 {

    private static int count = 1;

    public static void main(String[] args) {


        Thread thread = new Thread(null,new Runnable() {

            @Override
            public void run() {
                try {
                    add(1);
                }catch (Error err) {
                    System.out.println(count);
                }

            }

            private void add(int i) {
                count ++;
                add(i+1);
            }
        },"test",1 << 24);

        thread.start();
    }



}
