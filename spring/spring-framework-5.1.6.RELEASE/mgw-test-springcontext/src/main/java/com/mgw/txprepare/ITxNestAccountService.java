package com.mgw.txprepare;

/**
 * 嵌套事务
 * */
public interface ITxNestAccountService {
	void save() throws RuntimeException;

	void delete() throws RuntimeException;
}
