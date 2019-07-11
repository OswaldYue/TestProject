package com.mgw.tx;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MulService {

    @Autowired
    private BookService bookService;

    @Transactional(rollbackFor = Exception.class)
    public void mulTx() {

        bookService.checkout2("Tom","ISBN-01");

        bookService.updatePrice("ISBN-02",998);

        int i = 10/0;

    }


}
