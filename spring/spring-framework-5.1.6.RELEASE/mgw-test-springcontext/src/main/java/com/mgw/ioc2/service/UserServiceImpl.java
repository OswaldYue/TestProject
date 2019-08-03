package com.mgw.ioc2.service;

import com.mgw.ioc2.dao.UserDao;

public class UserServiceImpl implements UserService {


	private UserDao userDao;

	/*
	* DI exists in two major variants: Constructor-based dependency injection and Setter-based dependency injection.
	* 依赖注入的两种方法:
	* 1.构造器注入
	* 2.set注入
	* 3.接口注入(spring4将其去掉,目前不再支持)
	*
	* */

	/**
	 * 使用构造器注入
	 *
	 * */
	public UserServiceImpl(UserDao userDao) {
		this.userDao =userDao;
	}

	/**
	 * 使用set注入
	 * 当使用xml配置依赖时 非常需要set方法,否则会无法依赖
	 * */
//	public void setUserDao(UserDao userDao) {
//		this.userDao = userDao;
//	}

	@Override
	public void test() {
		userDao.test();
	}
}
