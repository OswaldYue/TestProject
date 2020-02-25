package com.mgw.two.chapter11;

/**
 * 线程上下文设计模式
 * */
public class ExecutionTask implements Runnable {

    private QueryFromDBAction dbAction = new QueryFromDBAction();

    private QueryFromHttpAction httpAction = new QueryFromHttpAction();

    @Override
    public void run() {
        //final Context context = new Context();
        dbAction.execute();
        System.out.println("The name query successful");
        httpAction.execute();
        System.out.println("The cardID query successful");
        Context context = ActionContext.getActionContext().getContext();
        System.out.println("The name is " + context.getName() + " and cardId is " + context.getCardId());
    }
}
