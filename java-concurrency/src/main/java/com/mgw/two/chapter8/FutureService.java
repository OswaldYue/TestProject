package com.mgw.two.chapter8;

import java.util.function.Consumer;

public class FutureService {

    public <T> Future<T> submit(final FutureTask<T> task) {

        AsynFuture<T> asynFuture = new AsynFuture();

        new Thread(() -> {
            T result = task.call();
            asynFuture.done(result);
        }).start();

        return asynFuture;


    }

    /**
     * consumer 做一个回调
     * */
    public <T> Future<T> submit(final FutureTask<T> task, final Consumer<T> consumer) {

        AsynFuture<T> asynFuture = new AsynFuture();

        new Thread(() -> {
            T result = task.call();
            asynFuture.done(result);
            consumer.accept(result);
        }).start();

        return asynFuture;


    }


}
