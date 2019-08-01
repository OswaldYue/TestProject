package com.mgw.jdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AccountDao {

	@Autowired
	private AccountMapper accountMapper;


	public AccountEntity getAccountByUsername(String username) {

		//class com.sun.proxy.$Proxy20
		System.out.println(accountMapper.getClass());
		return accountMapper.getAccountByUsername(username);
	}


}


