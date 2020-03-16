package com.mgw.txprepare;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * 测试嵌套事务
 * */
public class TxNestPersonServiceImpl implements ITxNestPersonService{

	private JdbcTemplate jdbcTemplate;


	private static String insert_sql = "insert into account(balance) values (100)";

	@Override
	@Transactional(propagation = Propagation.NOT_SUPPORTED, rollbackFor = Exception.class)
	public void save() throws RuntimeException {
		System.out.println("==调用PersonService的save方法\n");
		jdbcTemplate.update(insert_sql);
		//throw new RuntimeException("==PersonService手动抛出异常");
	}


	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

}
