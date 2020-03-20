package user.service;

import java.util.List;

import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import Bean.User;
import user.Level;
import user.UserDao;
import user.UserLevelUpgradePolicy;

@Service("userService")
public class UserServiceImpl implements UserService{
	@Autowired
	UserDao userDao;
	UserLevelUpgradePolicy userLevelUpgradePolicy;
	@Autowired
	MailSender mailSender;
	
	public static int MIN_LOGCOUNT_FOR_SILVER;
	public static int MIN_RECCOMEND_FOR_GOLD;
	
	
	public void setMailSender(MailSender mailSender) {
		this.mailSender = mailSender;
	}
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	@Autowired
	public void setUserLevelUpgradePolicy(UserLevelUpgradePolicy userLevelUpgradePolicy){
		this.userLevelUpgradePolicy = userLevelUpgradePolicy;
		MIN_LOGCOUNT_FOR_SILVER = userLevelUpgradePolicy.getMinLogcountForSilver();
		MIN_RECCOMEND_FOR_GOLD = userLevelUpgradePolicy.getMinRecommendCountForGold();
	}
	
	public void add(User user) {
		if(user.getLevel() == null) user.setLevel(Level.BASIC);
		userDao.add(user);
	}
	
	
	public void upgradeLevels(){
		List<User> users = userDao.getAll();
		for(User user : users) {
			if(canUpgradeLevel(user)) {
				upgradeLevel(user);
			}
		}
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
		sendUpgradeEMail(user);
	}
	
	private void sendUpgradeEMail(User user) {
		SimpleMailMessage mailMessage = new SimpleMailMessage();
		mailMessage.setTo(user.getEmail());
		mailMessage.setFrom("gameclow2@gmail.com");
		mailMessage.setSubject("Upgrade 안내");
		mailMessage.setText("사용자님의 등급이 " + user.getLevel().name());
		
		this.mailSender.send(mailMessage);
	}
	@Override
	public User get(String id) {
		return userDao.get(id);
	}
	@Override
	public List<User> getAll() {		
		return userDao.getAll();
	}
	@Override
	public void deleteAll() {
		userDao.deleteAll();
	}
	@Override
	public void update(User user) {
		userDao.update(user);
	}
	

}
