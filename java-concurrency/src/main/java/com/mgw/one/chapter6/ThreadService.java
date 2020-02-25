package com.mgw.one.chapter6;

public class ThreadService {

    private Thread execteThread;

    private boolean finished = false;

    public void execte(Runnable task) {

        execteThread = new Thread() {

            @Override
            public void run() {

                Thread runner = new Thread(task);
                runner.setDaemon(true);

                runner.start();
                try {
                    //目的是让runner线程执行到死亡为止，避免因为execteThread线程执行完结束而runner线程还未起来
                    runner.join();
                    finished = true;
                }catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        execteThread.start();
    }

    public void shutdown(long mills) {

        long currentTime = System.currentTimeMillis();

        //表示execteThread线程还未执行结束,如果已经执行结束，则不理睬
        while (!finished) {

            if (System.currentTimeMillis()-currentTime >= mills ) {
                System.out.println("任务超时，需要结束");
                // 打断execteThread线程，execteThread中捕捉到后抛异常结束后，runner线程跟着结束
                execteThread.interrupt();

                break;
            }

            try {
                // 此处休眠的目的是让cpu切换同步缓存中的finished,可以在finished前加volatile，那么此处可不用休眠
                // 但cpu维护volatile需要的资源更甚
                execteThread.sleep(1);
            }catch (InterruptedException e) {
                System.out.println("执行线程被打断");
                break;
            }

        }

        finished = false;
    }


}
