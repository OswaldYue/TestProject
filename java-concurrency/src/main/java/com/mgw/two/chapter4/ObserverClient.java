package com.mgw.two.chapter4;

public class ObserverClient {

    public static void main(String[] args) {

        Subject subject = new Subject();

        new BinaryObserver(subject);
        new OctalObserver(subject);

        System.out.println("=============================================");
        subject.setState(10);
        System.out.println("=============================================");
        subject.setState(20);
        System.out.println("=============================================");
        subject.setState(20);
    }
}
