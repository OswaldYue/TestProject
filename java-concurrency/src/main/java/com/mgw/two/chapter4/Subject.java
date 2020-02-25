package com.mgw.two.chapter4;

import java.util.ArrayList;
import java.util.List;



/**
 * 主题
 * */
public class Subject {

    private List<Observer>  observers = new ArrayList<>();

    private int state;

    public int getState() {
        return this.state;
    }

    public void setState(int state) {
        if (this.state == state) {

            return;
        }

        this.state = state;
        //通知所有的观察者
        notifyAllObserver();
    }

    public void attach(Observer observer) {
        observers.add(observer);
    }


    private void notifyAllObserver() {

        observers.stream().forEach(observer -> {
            observer.update();
        });

    }

}
