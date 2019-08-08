package com.mgw.proxy.proxyCoustom.dao;

public  class UserDaoImpl implements UserDao {

    @Override
    public void query() {

        System.out.println("---UserDaoImpl---query()---操作数据库---");

    }

    @Override
    public String proxy() {
        System.out.println("---UserDaoImpl---proxy()---操作数据库---");
        return "mmmmmmmmmmmmmmmmm";
    }

//    @Override
//    public void query(String p) {
//        System.out.println(p);
//    }
//
//    @Override
//    public String query(String p1, Integer p2) {
//        System.out.println( p1 + p2);
//        return p1 + p2;
//    }
}
