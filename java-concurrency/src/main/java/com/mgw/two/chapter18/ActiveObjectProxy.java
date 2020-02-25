package com.mgw.two.chapter18;

class ActiveObjectProxy implements ActiveObject{

    private final SchedulerThread schedulerThread;

    private final Servant servant;

    public ActiveObjectProxy(SchedulerThread schedulerThread, Servant servant) {
        this.schedulerThread = schedulerThread;
        this.servant = servant;
    }

    @Override
    public Result makeString(int count, char fileChar) {

        FutureResult future = new FutureResult();
        this.schedulerThread.invoke(new MakeStringRequest(servant,future,count,fileChar));
        return future;
    }

    @Override
    public void displayString(String text) {
        schedulerThread.invoke(new DisplayStringRequest(servant,text));
    }
}
