package com.mgw.two.chapter18;

public final class ActiveObjectFactory {

    private ActiveObjectFactory() {

    }

    public static ActiveObject createActiveObject() {
        Servant servant = new Servant();
        ActivationQueue queue = new ActivationQueue();

        SchedulerThread schedulerThread = new SchedulerThread(queue);
        // 往queue中放
        ActiveObjectProxy proxy = new ActiveObjectProxy(schedulerThread, servant);
        // 从queue中拿
        schedulerThread.start();

        return proxy;
    }
}
