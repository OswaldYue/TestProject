package com.mgw.txprepare;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * 测试嵌套事务
 * 新建三个Service接口及其实现类，AccountService、BankService、PersonService。
 * 然后在BankService的save方法中调用了AccountService、PersonService的方法
 * */
public class TxNestBankServiceImpl implements ITxNestBankService {

	private ITxNestPersonService personService;
	private ITxNestAccountService accountService;

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void save() throws RuntimeException {
		System.out.println("==调用BankService的save方法\n");
		personService.save();
		accountService.save();
	}

	public void setPersonService(ITxNestPersonService personService) {
		this.personService = personService;
	}

	public void setAccountService(ITxNestAccountService accountService) {
		this.accountService = accountService;
	}
}
