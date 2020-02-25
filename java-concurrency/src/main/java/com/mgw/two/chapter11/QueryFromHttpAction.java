package com.mgw.two.chapter11;

public class QueryFromHttpAction {


    public void execute() {

        String name = ActionContext.getActionContext().getContext().getName();
        String cardId = getCardId(name);

        ActionContext.getActionContext().getContext().setCardId(cardId);
//        context.setCardId(cardId);

    }


    private String getCardId(String name) {

        try {
            Thread.sleep(1_000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return "2312882383" + Thread.currentThread().getId();
    }

}
