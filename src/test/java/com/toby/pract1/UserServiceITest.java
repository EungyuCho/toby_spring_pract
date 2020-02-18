package com.toby.pract1;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

import java.util.Arrays;
import java.util.List;

import javax.sql.DataSource;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailMessage;
import org.springframework.mail.MailSender;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.PlatformTransactionManager;

import com.toby.pract1.UserServiceITest.TestUserService.TestUserServiceException;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "/test-applicationContext.xml")
public class UserServiceITest {
	@Autowired
	UserService userService;
	@Autowired
	UserServiceImpl userServiceImpl;
	@Autowired
	private UserDaoJdbc userDao;
	@Autowired
	DataSource dataSource;
	@Autowired
	PlatformTransactionManager transactionManager;
	@Autowired
	MailSender mailSender;
	List<User> users;
	User user;
	@Autowired
	UserLevelUpgradePolicyBtype userLevelUpgradePolicy;
	private static int MIN_LOGCOUNT_FOR_SILVER;
	private static int MIN_RECCOMEND_FOR_GOLD;
	public void setMailSender(MailSender mailSender) {
		this.mailSender = mailSender;
	}
	static class TestUserService extends UserServiceImpl{
		private String id;
		
		static class TestUserServiceException extends RuntimeException{
			
		}
		
		private TestUserService(String id) {
			this.id = id;
		}
		
		@Override
		protected void upgradeLevel(User user) {
			if(user.getId().equals(this.id)) throw new TestUserServiceException();
			super.upgradeLevel(user);
		}
	}
	
	
	@Before
	public void setUp() {
		MIN_LOGCOUNT_FOR_SILVER = userLevelUpgradePolicy.getMinLogcountForSilver();
		MIN_RECCOMEND_FOR_GOLD = userLevelUpgradePolicy.getMinRecommendCountForGold();
		users = Arrays.asList(
				new User("bumjin", "박범진", "p1", "gameclow2@gmail.com",  Level.BASIC, MIN_LOGCOUNT_FOR_SILVER-1, 0),
				new User("joytouch", "강명성", "p2","gameclow3@gmail.com",  Level.BASIC, MIN_LOGCOUNT_FOR_SILVER, 0),
				new User("erwins", "신승한", "p3", "gameclow4@gmail.com", Level.SILVER, 60, MIN_RECCOMEND_FOR_GOLD-1),
				new User("madnite1", "이상호", "p4", "gameclow5@gmail.com", Level.SILVER, 60, MIN_RECCOMEND_FOR_GOLD),
				new User("green", "오민규", "p5",  "gameclow3@gmail.com", Level.GOLD, 100, Integer.MAX_VALUE)
			);
	}

	
	@Test
	@DirtiesContext
	public void upgradeLevels() throws Exception {
		userDao.deleteAll();
		
		for(User user: users) userDao.add(user);

		MockMailSender mockMailsender = new MockMailSender();
		userServiceImpl.setMailSender(mockMailsender);
		
		userService.upgradeLevels();
		
		checkLevelUpgrade(users.get(0), false);
		checkLevelUpgrade(users.get(1), true);
		checkLevelUpgrade(users.get(2), false);
		checkLevelUpgrade(users.get(3), true);
		checkLevelUpgrade(users.get(4), false);
		
		List<String> request = mockMailsender.getRequests();
		assertThat(request.size(), is(2));
		assertThat(request.get(0), is(users.get(1).getEmail()));
		assertThat(request.get(1), is(users.get(3).getEmail()));
		
	}
	@Test
	public void add() {
		userDao.deleteAll();

		User userWithLevel = users.get(4);
		User userWithoutLevel = users.get(0);
		userWithoutLevel.setLevel(null);

		userService.add(userWithLevel);
		userService.add(userWithoutLevel);
		
		User userWithLevelRead = userDao.get(userWithLevel.getId());
		User userWithoutLevelRead = userDao.get(userWithoutLevel.getId());

		assertThat(userWithLevelRead.getLevel(), is(userWithLevel.getLevel()));
		assertThat(userWithoutLevelRead.getLevel(), is(userWithoutLevel.getLevel()));
		
		
	}
	
	private void checkLevelUpgrade(User user, boolean upgraded) {
		User userUpdate = userDao.get(user.getId());
		if(upgraded) {
			assertThat(userUpdate.getLevel(), is(user.getLevel().nextLevel()));
		}else {
			assertThat(userUpdate.getLevel(), is(user.getLevel()));
		}
	}
	@Test
	public void upgradeAllOrNothing() throws Exception {
		UserServiceImpl testUserService = new TestUserService(users.get(3).getId());
		testUserService.setUserDao(userDao);
		testUserService.setMailSender(mailSender);
		
		UserServiceTx txUserService = new UserServiceTx();
		txUserService.setTranactionManager(transactionManager);
		txUserService.setUserService(testUserService);
		
		userDao.deleteAll();
		for(User user : users) userDao.add(user);
		
		try {
			txUserService.upgradeLevels();
			fail("TestUserServiceException expected");
		}catch(TestUserServiceException e) {
			
		}
		checkLevelUpgrade(users.get(1), false);
	}
}
