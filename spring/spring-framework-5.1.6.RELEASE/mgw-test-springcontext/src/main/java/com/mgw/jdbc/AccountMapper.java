package com.mgw.jdbc;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * mybatis的二级缓存 Mapper上添加@CacheNamespace注解
 * 基于命名空间的二级缓存  坑很大
 *
 * 一般mybatis缓存会使用redis等框架去做
 * */
//@CacheNamespace
public interface AccountMapper {

	@Select("SELECT * FROM test_account WHERE username = #{username}")
	AccountEntity getAccountByUsername(@Param("username") String username);
}
