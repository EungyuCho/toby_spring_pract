package com.toby.pract1;

import Bean.User;

public class UserLevelUpgradePolicyBtype implements UserLevelUpgradePolicy{
	
	public static int MIN_LOGCOUNT_FOR_SILVER = 40;
	public static int MIN_RECCOMEND_FOR_GOLD = 20;
	UserDao userDao;
	
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
	@Override
	public boolean canUpgradeLevel(User user) {
		Level currentLevel = user.getLevel();
		switch (currentLevel) {
			case BASIC: return (user.getLogin() >= MIN_LOGCOUNT_FOR_SILVER);
			case SILVER: return (user.getRecommend() >=MIN_RECCOMEND_FOR_GOLD);
			case GOLD: return false;
			default: throw new IllegalArgumentException("Unknown level: " + currentLevel);
		}
	}

	@Override
	public void upgradeLevel(User user) {
		user.upgradeLevel();
		userDao.update(user);
	}

	@Override
	public int getMinLogcountForSilver() {
		return MIN_LOGCOUNT_FOR_SILVER;
	}

	@Override
	public int getMinRecommendCountForGold() {
		return MIN_RECCOMEND_FOR_GOLD;
	}
}
