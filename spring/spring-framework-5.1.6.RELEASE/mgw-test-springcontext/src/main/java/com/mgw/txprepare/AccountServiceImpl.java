package com.mgw.txprepare;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionSynchronizationAdapter;
import org.springframework.transaction.support.TransactionSynchronizationManager;

/**
 * 声明式事务
 * */
public class AccountServiceImpl implements IAccountService {

	private JdbcTemplate jdbcTemplate;

	private static String insert_sql = "insert into account(balance) values (100)";

	@Override
	public void save() throws RuntimeException {
		System.out.println("==开始执行sql");
		jdbcTemplate.update(insert_sql);
		System.out.println("==结束执行sql");

		System.out.println("==准备抛出异常");
		throw new RuntimeException("==手动抛出一个异常");
	}

	// 测试事务同步回调接口
	// 如果为当前事物设置了回调接口，那么事物管理器会在事物执行期间调用该接口。
	// 例如：为下面的业务方法注册了TransactionSynchronizationAdapter接口，
	// 那么事物管理器会在事物执行期间调用我们已经实现的TransactionSynchronizationAdapter接口的方法
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor = RuntimeException.class,readOnly = false)
	@Override
	public void delete() {
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
		System.out.println("==调用AccountService的dele方法");
		jdbcTemplate.update(insert_sql);
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
}
