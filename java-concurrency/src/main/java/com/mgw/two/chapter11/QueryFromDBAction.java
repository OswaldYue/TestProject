package com.mgw.two.chapter11;

public class QueryFromDBAction {

    public void execute()  {

        try {
            Thread.sleep(1_000);

            String name = "mmm-" + Thread.currentThread().getName();
            ActionContext.getActionContext().getContext().setName(name);
//            context.setName(name);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
