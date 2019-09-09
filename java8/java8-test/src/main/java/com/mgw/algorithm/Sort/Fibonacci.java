package com.mgw.algorithm.Sort;

import java.util.Arrays;

public class Fibonacci {

    private int n;


    public Fibonacci(int n) {
        this.n = n;
    }

    /**
     * 递归
     * */
    public int[] getFibonacci() {

        int[] arr = new int[n+1];

        for (int i = 0; i <= n; i++) {
            arr[i] = fib(i);
//            System.out.println(fib(i));
        }
        return arr;
    }

    private int fib(int n) {

        if (n <= 0)
            return 0;
        if (n == 1)
            return 1;

        return fib(n-1) + fib(n-2);
    }

    /**
     * 自顶向下的备忘录法
     * */
    public int[] getFibonacci2() {

        int[] arr = new int[n+1];

        for (int i = 0; i <= n; i++) {

            arr[i] = fib2(i);
        }

        return arr;
    }

    private int fib2(int n) {
        if(n<=0) {
            return n;
        }
        int[] memo = new int[n+1];
        for(int i=0;i<=n;i++) {
            memo[i] = -1;
        }
        return fib2(n, memo);
    }

    private int fib2(int n,int[] memo) {

        if(memo[n]!=-1) {
            return memo[n];
            //如果已经求出了fib（n）的值直接返回，否则将求出的值保存在memo备忘录中。
        }
        if(n<=2) {
            memo[n] = 1;
        }else {
            memo[n]=fib2( n-1,memo)+fib2(n-2,memo);
        }

        return memo[n];
    }

    /**
     * 自底向上的动态规划
     * */
    private int fib31(int n) {
        if(n<=0) {
            return n;
        }
        int []memo=new int[n+1];
        memo[0]=0;
        memo[1]=1;
        for(int i=2;i<=n;i++) {
            memo[i]=memo[i-1]+memo[i-2];
        }
        return memo[n];
    }
    private int fib32(int n) {
        if(n<=1)
            return n;

        int memoI2=0;
        int memoI1=1;
        int memoI=1;
        for(int i=2;i<=n;i++)
        {
            memoI=memoI2+memoI1;
            memoI2=memoI1;
            memoI1=memoI;
        }
        return memoI;
    }


    public static void main(String[] args) {

        Fibonacci fibonacci = new Fibonacci(6);
        int[] ints = fibonacci.getFibonacci2();

        System.out.println(Arrays.toString(ints));

    }
}
