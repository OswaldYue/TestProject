package com.mgw.tx;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BookService {

    @Autowired BookDao bookDao;

    @Transactional(rollbackFor = Exception.class)
    public void checkout(String username,String isbn) {

        bookDao.updateStock(isbn);
        int price = bookDao.getPrice(isbn);
        bookDao.updateBalance(username,price);

    }

}
