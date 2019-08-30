package com.mgw.tx;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@Configuration
@PropertySource(value = "classpath:/jdbc.properties")
//相当于<tx:annotation-driven></tx:annotation-driven>这个注解
@EnableTransactionManagement
@ComponentScan("com.mgw.tx")
public class TxConfig {

    @Autowired
    private Environment env;

    public static final String JDBC_URL = "jdbc_url";
    public static final String JDBC_USERNAME = "jdbc_username";
    public static final String JDBC_PASSWORD = "jdbc_password";
    public static final String JDBC_DRIVER = "jdbc_driver";


    @Bean
    public DataSource dataSource() {

        DruidDataSource druidDataSource = new DruidDataSource();

        druidDataSource.setUrl(env.getProperty(JDBC_URL));
        druidDataSource.setUsername(env.getProperty(JDBC_USERNAME));
        druidDataSource.setPassword(env.getProperty(JDBC_PASSWORD));
        druidDataSource.setDriverClassName(env.getProperty(JDBC_DRIVER));

        return druidDataSource;
    }

    /**
     * 配置事务管理器,控住数据源
     *
     * */
    @Bean
    public DataSourceTransactionManager dataSourceTransactionManager() {

        return new DataSourceTransactionManager(dataSource());
    }


    /**
     * JdbcTemplate注入到容器中
     *
     * */
    @Bean
    public JdbcTemplate jdbcTemplate() {

        return new JdbcTemplate(dataSource());
    }


}
