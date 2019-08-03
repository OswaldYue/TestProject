package com.mgw.ioc2.service;

import com.mgw.ioc2.dao.UserDao;

public class UserServiceImpl implements UserService {


	private UserDao userDao;

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	@Override
	public void test() {
		userDao.test();
	}
}
