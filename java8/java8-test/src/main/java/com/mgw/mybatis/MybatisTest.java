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
        //将配置文件解析  并将解析的信息全部封装到Configuration这个类中
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        //从SqlSessionFactory中拿到一个SqlSession对象 这个对象中有缓存等等
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //注册一个Mapper
        sqlSession.getConfiguration().addMapper(BookMapper.class);
        //放进去一个原生的Mapper对象 但是拿出时已经创建了一个代理对象了 代理对象的处理器InvocationHandler是MapperProxy
        BookMapper mapper = sqlSession.getMapper(BookMapper.class);
        int price = mapper.getPrice("ISBN-01");

        System.out.println(price);

    }

    public static void main(String[] args) {
        System.out.println("========================================================");
        test1();

    }
}
