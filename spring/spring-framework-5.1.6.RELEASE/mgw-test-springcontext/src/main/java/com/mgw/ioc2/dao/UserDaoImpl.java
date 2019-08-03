package com.mgw.ioc2.dao;

import org.springframework.stereotype.Component;

@Component("userDao")
public class UserDaoImpl implements UserDao {
	@Override
	public void test() {
		System.out.println("---UserDaoImpl---test()---");
	}
}
