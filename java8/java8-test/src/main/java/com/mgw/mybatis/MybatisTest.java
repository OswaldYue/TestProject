package com.mgw.mybatis;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

public class MybatisTest {


    public static void test1() {


        String resourceFile = "mybatis.xml";
        InputStream inputStream = null;
        try {
            inputStream = Resources.getResourceAsStream(resourceFile);
        } catch (IOException e) {
            e.printStackTrace();
        }

        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        sqlSession.getConfiguration().addMapper(BookMapper.class);

        BookMapper mapper = sqlSession.getMapper(BookMapper.class);
        int price = mapper.getPrice("ISBN-01");

        System.out.println(price);

    }

    public static void main(String[] args) {
        System.out.println("========================================================");
        test1();

    }
}
