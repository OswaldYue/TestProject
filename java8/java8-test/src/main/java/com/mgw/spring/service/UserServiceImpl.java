package com.mgw.spring.service;

import com.mgw.spring.dao.UserDao;

public class UserServiceImpl implements UserService {

    UserDao userDao;

    @Override
    public void find() {

        System.out.println("---UserServiceImpl---find()---");
        userDao.query();
    }
}
