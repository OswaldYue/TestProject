package com.mgw.two.chapter4;

public class BinaryObserver extends Observer{

    public BinaryObserver(Subject subject) {
        super(subject);

    }

    @Override
    public void update() {
        System.out.println("Binary String : " + Integer.toBinaryString(subject.getState()));
    }
}
