package com.toby.pract1;

import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

public class UserServiceTx implements UserService {
	UserService userService;
	PlatformTransactionManager tranactionManager;
	
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public void setTranactionManager(PlatformTransactionManager tranactionManager) {
		this.tranactionManager = tranactionManager;
	}
	
	@Override
	public void add(User user) {
		userService.add(user);
	}

	@Override
	public void upgradeLevels() {
		TransactionStatus status = 
				this.tranactionManager.getTransaction(new DefaultTransactionDefinition());
		try {
			userService.upgradeLevels();
			tranactionManager.commit(status);
		}catch(RuntimeException e) {
			this.tranactionManager.rollback(status);
			throw e;
		}
	}

}
