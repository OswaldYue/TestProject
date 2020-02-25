package com.mgw.two.chapter4;

public  abstract class ObservableRunnable implements Runnable {


    protected final LifeCycleListener listener;

    public ObservableRunnable(LifeCycleListener listener) {
        this.listener = listener;
    }

    protected void notifyChange(RunnableEvent event) {

        listener.onEvent(event);
    }

    public enum RunnableState {
        RUNNING,ERROR,DONE
    }

    public static class RunnableEvent {
        private final RunnableState state;
        private final Thread thread;
        private final Throwable cause;

        public RunnableEvent(RunnableState state, Thread thread, Throwable cause) {
            this.state = state;
            this.thread = thread;
            this.cause = cause;
        }

        public RunnableState getState() {
            return state;
        }

        public Thread getThread() {
            return thread;
        }

        public Throwable getCause() {
            return cause;
        }
    }

}
