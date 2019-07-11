package com.mgw.tx;

import org.springframework.beans.factory.annotation.Autowired;

public class BookServiceXml {


    @Autowired
    private BookDao bookDao;


    public void checkout(String username,String isbn) {

        bookDao.updateStock(isbn);
        int price = bookDao.getPrice(isbn);
        bookDao.updateBalance(username,price);

        int i = 10 / 0;
    }

    public void updatePrice(String isbn,int price) {

        bookDao.updatePrice(isbn,price);

//        int i = 10/0;
    }

    public void mulTx() {

        checkout("Tom","ISBN-01");

        updatePrice("ISBN-02",998);

        int i = 10/0;

    }

}
