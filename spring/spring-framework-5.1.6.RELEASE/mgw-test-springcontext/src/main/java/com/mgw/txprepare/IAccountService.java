package com.mgw.txprepare;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Transactional(propagation = Propagation.REQUIRED)
public interface IAccountService {
	void save() throws RuntimeException;
	void delete();
}
