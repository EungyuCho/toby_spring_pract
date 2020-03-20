package user;

import Bean.User;

public interface UserLevelUpgradePolicy {
	boolean canUpgradeLevel(User user);
	void upgradeLevel(User user);
	int getMinLogcountForSilver();
	int getMinRecommendCountForGold();
}
