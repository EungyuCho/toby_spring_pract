package com.toby.pract1;

public interface UserLevelUpgradePolicy {
	boolean canUpgradeLevel(User user);
	void upgradeLevel(User user);
	int getMinLogcountForSilver();
	int getMinRecommendCountForGold();
}
