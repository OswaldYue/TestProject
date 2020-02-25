package com.mgw.two.chapter4;

public class OctalObserver extends Observer{

    public OctalObserver(Subject subject) {
        super(subject);

    }

    @Override
    public void update() {
        System.out.println("Octal String : " + Integer.toOctalString(subject.getState()));
    }
}
