package com.mgw.two.chapter18;

public class DisplayClientThread extends Thread{

    private final ActiveObject activeObject;

    public DisplayClientThread(String name,ActiveObject activeObject) {
        super(name);
        this.activeObject = activeObject;
    }

    @Override
    public void run() {
        try {
            for (int i = 0;true;i++) {
                String text = Thread.currentThread().getName() + "=>" + i;
                activeObject.displayString(text);
                Thread.sleep(200);
            }
        }catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
