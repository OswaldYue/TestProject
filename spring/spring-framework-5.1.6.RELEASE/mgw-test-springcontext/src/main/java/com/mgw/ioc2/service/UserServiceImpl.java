package com.mgw.ioc2.service;

import com.mgw.ioc2.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;

public class UserServiceImpl implements UserService {

	/*
	* @Autowired是根据属性(by_Type)去找的,找不到再根据属性名(by_Name)去找,再找不到就报错
	*
	* */
	@Autowired
	private UserDao userDao;

	/*
	* @Resource是根据private UserDao userDao1中的userDao1这个名字去找的  与是否提供setUserDao1()方法无关
	* 若以xml的配置根据by_name去装配,则需要提供set方法
	* */
	@Resource
	private UserDao userDao1;


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
