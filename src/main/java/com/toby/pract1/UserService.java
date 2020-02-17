package com.toby.pract1;

import java.sql.Connection;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.transaction.support.TransactionSynchronizationManager;

public class UserService {
	UserDao userDao;
	UserLevelUpgradePolicy userLevelUpgradePolicy;
	
	public static int MIN_LOGCOUNT_FOR_SILVER;
	public static int MIN_RECCOMEND_FOR_GOLD;
	private DataSource dataSource;
	private PlatformTransactionManager transactionManager;
	
	public void setTransactionManager(PlatformTransactionManager transactionManager) {
		this.transactionManager = transactionManager;
	}
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
	public void setUserLevelUpgradePolicy(UserLevelUpgradePolicy userLevelUpgradePolicy) {
		this.userLevelUpgradePolicy = userLevelUpgradePolicy;
		MIN_LOGCOUNT_FOR_SILVER = userLevelUpgradePolicy.getMinLogcountForSilver();
		MIN_RECCOMEND_FOR_GOLD = userLevelUpgradePolicy.getMinRecommendCountForGold();
	}
	public void upgradeLevels() throws Exception{
		TransactionStatus status =
				this.transactionManager.getTransaction(
						new DefaultTransactionDefinition());
		try {
			List<User> users = userDao.getAll();
			for(User user : users) {
				if(canUpgradeLevel(user)) {
					upgradeLevel(user);
				}
			}
			this.transactionManager.commit(status);
		}catch(Exception e) {
			this.transactionManager.rollback(status);
			throw e;
		}
	}
	
	public void add(User user) {
		if(user.getLevel() == null) user.setLevel(Level.BASIC);
		userDao.add(user);
	}

	private boolean canUpgradeLevel(User user) {
		Level currentLevel = user.getLevel();
		switch (currentLevel) {
			case BASIC: return (user.getLogin() >= MIN_LOGCOUNT_FOR_SILVER);
			case SILVER: return (user.getRecommend() >=MIN_RECCOMEND_FOR_GOLD);
			case GOLD: return false;
			default: throw new IllegalArgumentException("Unknown level: " + currentLevel);
		}
	}
	
	protected void upgradeLevel(User user) {
		user.upgradeLevel();
		userDao.update(user);
	}
}
