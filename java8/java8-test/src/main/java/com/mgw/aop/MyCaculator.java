package com.mgw.aop;

import org.springframework.stereotype.Component;

@Component
public class MyCaculator implements Caculator {

    @Override
    public int add(int i, int j) {
        System.out.println("add 执行");
        return i+j;
    }

    @Override
    public float div(int i, int j) {
        System.out.println("div 执行");
        return i/j;
    }

}
