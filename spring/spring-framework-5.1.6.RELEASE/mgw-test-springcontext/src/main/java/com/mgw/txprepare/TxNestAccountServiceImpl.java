package com.mgw.txprepare;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionSynchronizationAdapter;
import org.springframework.transaction.support.TransactionSynchronizationManager;

/**
 * 测试嵌套事务
 * */
public class TxNestAccountServiceImpl implements ITxNestAccountService {

	private JdbcTemplate jdbcTemplate;

	private static String insert_sql = "insert into account(balance) values (100)";


	@Override
	@Transactional(propagation = Propagation.NOT_SUPPORTED, rollbackFor = Exception.class)
	public void save() throws RuntimeException {
		System.out.println("==调用AccountService的save方法\n");
		jdbcTemplate.update(insert_sql);

		throw new RuntimeException("==AccountService的save方法手动抛出异常");
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void delete() throws RuntimeException {

		TransactionSynchronizationManager.registerSynchronization(new TransactionSynchronizationAdapter() {
			@Override
			public void beforeCommit(boolean readOnly) {
				System.out.println("==回调,事物提交之前");
				super.beforeCommit(readOnly);
			}

			@Override
			public void afterCommit() {
				System.out.println("==回调,事物提交之后");
				super.afterCommit();
			}

			@Override
			public void beforeCompletion() {
				super.beforeCompletion();
				System.out.println("==回调,事物完成之前");
			}

			@Override
			public void afterCompletion(int status) {
				super.afterCompletion(status);
				System.out.println("==回调,事物完成之后");
			}
		});

		System.out.println("==调用AccountService的dele方法\n");
		jdbcTemplate.update(insert_sql);

		throw new RuntimeException("==AccountService的delete方法手动抛出异常");
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
}
