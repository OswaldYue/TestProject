package com.mgw.tx;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class BookDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     * 1.减余额
     * */
    public void updateBalance(String userName,int price) {

        String sql = "UPDATE test_account SET balance=? WHERE username=?";

        jdbcTemplate.update(sql,price,userName);

    }

    /**
     * 2.获取某本图书的价格
     * */
    public int getPrice(String isbn) {

        String sql = "SELECT price FROM test_book WHERE isbn = ?";

        return jdbcTemplate.queryForObject(sql,Integer.class,isbn);
    }

    /**
     * 3.减库存
     *
     * */
    public void updateStock(String isbn) {

        String sql = "UPDATE test_stock set stock=stock-1 WHERE isbn=?";

        jdbcTemplate.update(sql,isbn);
    }


}
