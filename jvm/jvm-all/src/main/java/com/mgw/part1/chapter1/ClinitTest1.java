package com.mgw.part1.chapter1;

public class ClinitTest1 {

    static class Father {

        public static int A = 10;

        static {
            A = 20;
        }

    }

    static class Son extends Father{
        public static int B = A;

    }

    public static void main(String[] args) {

        // 加载Father类，加载Son类
        System.out.println(Son.B);
    }


}
