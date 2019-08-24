package com.mgw.mybatis;

import org.apache.ibatis.annotations.Select;

public interface BookMapper {

    @Select("SELECT price FROM test_book WHERE isbn = ?")
    public int getPrice(String isbn);
}
