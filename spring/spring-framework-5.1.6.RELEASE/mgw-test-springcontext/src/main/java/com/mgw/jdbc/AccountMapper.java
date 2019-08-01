package com.mgw.jdbc;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface AccountMapper {

	@Select("SELECT * FROM test_account WHERE username = #{username}")
	AccountEntity getAccountByUsername(@Param("username") String username);
}
