package com.mgw.proxy.proxyCoustom;

import com.mgw.proxy.proxyCoustom.dao.UserDao;
import com.mgw.proxy.proxyCoustom.dao.UserDaoImpl;
import com.mgw.proxy.proxyCoustom.utils.ProxyInvocationHandlerImpl;
import com.mgw.proxy.proxyCoustom.utils.ProxyUtil1;

public class Test {

    public static void test1() {

        UserDao userDao = new UserDaoImpl();

        try {
            UserDao proxy = (UserDao) ProxyUtil1.newProxyInstance(UserDao.class,new ProxyInvocationHandlerImpl(userDao));
            proxy.query();
//            System.out.println(aa);
            System.out.println(proxy);
        } catch (Exception e) {
            e.printStackTrace();
        }


//        Proxy.newProxyInstance();


    }

    public static void main(String[] args) {

        System.out.println("-----------------------------------------------");
        test1();
    }
}
