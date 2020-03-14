package com.toby.pract1;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.IOError;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;

import javax.sql.DataSource;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.dao.TransientDataAccessResourceException;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.toby.pract1.UserServiceITest.TestUserService.TestUserServiceException;
import Bean.Bean;
import Bean.User;
import Pointcut.Target;
import sqlService.jaxb.SqlType;
import sqlService.jaxb.Sqlmap;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "/test-applicationContext.xml")
@Transactional
@TransactionConfiguration(defaultRollback = false)
public class UserServiceITest {
	@Autowired
	UserService userService;
	@Autowired
	UserService testUserService;
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
	@Autowired
	ApplicationContext context;
	private static int MIN_LOGCOUNT_FOR_SILVER;
	private static int MIN_RECCOMEND_FOR_GOLD;
	public void setMailSender(MailSender mailSender) {
		this.mailSender = mailSender;
	}
	static class TestUserService extends UserServiceImpl{
		private String id = "madnite1";
		
		static class TestUserServiceException extends RuntimeException{
			
		}
		@Override
		protected void upgradeLevel(User user) {
			if(user.getId().equals(this.id)) throw new TestUserServiceException();
			super.upgradeLevel(user);
		}
		@Override
		public List<User> getAll(){
			for(User user: super.getAll()) {
				super.update(user);
			}
			return null;
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
	@Rollback
	public void mockUpgradeLevels() throws Exception{
		UserServiceImpl userServiceImpl = new UserServiceImpl();
		
		UserDao mockUserDao = mock(UserDao.class);
		when(mockUserDao.getAll()).thenReturn(this.users);
		userServiceImpl.setUserDao(mockUserDao);
		
		MailSender mockMailSender = mock(MailSender.class);
		userServiceImpl.setMailSender(mockMailSender);
		
		userServiceImpl.upgradeLevels();

		verify(mockUserDao, times(2)).update(any(User.class));
		verify(mockUserDao, times(2)).update(any(User.class));
		verify(mockUserDao).update(users.get(1));
		assertThat(users.get(1).getLevel(), is(Level.SILVER));
		verify(mockUserDao).update(users.get(3));
		assertThat(users.get(3).getLevel(), is(Level.GOLD));
		
		ArgumentCaptor<SimpleMailMessage> mailMessageArg = 
				ArgumentCaptor.forClass(SimpleMailMessage.class);
		verify(mockMailSender, times(2)).send(mailMessageArg.capture());
		List<SimpleMailMessage> mailMessages = mailMessageArg.getAllValues();
		assertThat(mailMessages.get(0).getTo()[0], is(users.get(1).getEmail()));
		assertThat(mailMessages.get(1).getTo()[0], is(users.get(3).getEmail()));
		
	}
	
	@Test
	@Rollback
	public void add() {
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

	private void checkUserAndLevel(User updated, String experctedId, Level expectedLevel) {
		assertThat(updated.getId(), is(experctedId));
		assertThat(updated.getLevel(), is(expectedLevel));
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
	@DirtiesContext
	@Transactional(propagation = Propagation.NEVER, isolation = Isolation.SERIALIZABLE)
	public void upgradeAllOrNothing() throws Exception {
		assertThat(userService.getAll().size(), is(0));
		for(User user : users) userService.add(user);
		assertThat(userService.getAll().size(), is(5));
		try {
			this.testUserService.upgradeLevels();
			fail("TestUserServiceException expected");
		}catch(TestUserServiceException e) {
			
		}
		checkLevelUpgrade(userDao.get("madnite1"), false);
		checkLevelUpgrade(users.get(3), false);
		userService.deleteAll();
	}
	
	public void pointcutMatches(String expression, Boolean expected, Class<?> clazz, String methodName, Class<?>... args) throws Exception{
		AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
		pointcut.setExpression(expression);
		
		assertThat(pointcut.getClassFilter().matches(clazz) &&
				pointcut.getMethodMatcher().matches(clazz.getMethod(methodName, args), null), is(expected));
	}
	
	public void targetClassPointcutMatches(String expression, boolean... expected) throws Exception{
		pointcutMatches(expression, expected[0], Target.class, "hello");
		pointcutMatches(expression, expected[1], Target.class, "hello", String.class);
		pointcutMatches(expression, expected[2], Target.class, "plus", int.class, int.class);
		pointcutMatches(expression, expected[3], Target.class, "minus", int.class, int.class);
		pointcutMatches(expression, expected[4], Target.class, "method");
		pointcutMatches(expression, expected[5], Bean.class, "method");
	}
	
	@Test
	public void methodSignaturePointcut() throws SecurityException, NoSuchMethodException{
		AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
		pointcut.setExpression("execution(public int " +
		"Pointcut.Target.minus(int,int)" +
		"throws java.lang.RuntimeException)");
		
		assertThat(pointcut.getClassFilter().matches(Target.class) &&
				pointcut.getMethodMatcher().matches(Target.class.getMethod("minus", int.class, int.class), null), is(true));

		assertThat(pointcut.getClassFilter().matches(Target.class) &&
				pointcut.getMethodMatcher().matches(Target.class.getMethod("plus", int.class, int.class), null), is(false));

		assertThat(pointcut.getClassFilter().matches(Bean.class) &&
				pointcut.getMethodMatcher().matches(Target.class.getMethod("method"), null), is(false));
	}
	@Test
	public void pointcut() throws Exception{
		targetClassPointcutMatches("execution(* * (..))", true, true, true, true, true, true);
		targetClassPointcutMatches("execution(int *(..))", false, false, true, true, false, false);
		targetClassPointcutMatches("execution(void *(..))", true, true, false, false, true, true);
		targetClassPointcutMatches("execution(* Pointcut..*.*(..))", true, true, true, true, true, false);
		targetClassPointcutMatches("execution(* *oint*..*(..))",  true, true, true, true, true, false);
		targetClassPointcutMatches("execution(* *oint*..he*(..))",  true, true, false, false, false, false);
		targetClassPointcutMatches("execution(* *..Tar*.*(..))",  true, true, true, true, true, false);
		targetClassPointcutMatches("execution(* *..Target.*(..))", true, true, true, true, true, false);
		targetClassPointcutMatches("execution(* *..B*.*(..))", false, false, false, false, false, true);
		targetClassPointcutMatches("execution(* hello (..))", true, true, false, false, false, false);
		targetClassPointcutMatches("execution(* meth* (..))", false, false, false, false, true, true);
		targetClassPointcutMatches("execution(* * (..) throws Runtime*)", false, false, false, true, false, true);
	}
	// transactional 동작 순서 : Target Method -> Target class -> Type Method -> Type Interface
	@Test(expected = TransientDataAccessResourceException.class)
	@Transactional(propagation = Propagation.NEVER)
	public void readOnlyTransactionAttribute() {
		for(User user: users) 
			testUserService.add(user);
		
		testUserService.getAll();
		assertThat(userService.getAll().size(), is(5));
		userService.deleteAll();
		assertThat(userService.getAll().size(), is(0));
		
	}
	
	@Test
	@Ignore
	public void deleteTest() {
		for(User user: users) 
			testUserService.add(user);
		assertThat(userService.getAll().size(), is(5));
		testUserService.deleteAll();
		assertThat(userService.getAll().size(), is(0));
		
	}
	
	@Test
	@Rollback
	public void transactionSync() {
		userService.deleteAll();
		userService.add(users.get(0));
		userService.add(users.get(1));
	}
	
	@Test
	public void readSqlmap() throws JAXBException, IOException{
		String contextPath = Sqlmap.class.getPackage().getName();
		JAXBContext context = JAXBContext.newInstance(contextPath);
		InputStream is = UserDao.class.getResourceAsStream("sqlmap.xml");
		Unmarshaller unmarshaller = context.createUnmarshaller();
		
		
		Sqlmap sqlmap = (Sqlmap) unmarshaller.unmarshal(is);
		
		List<SqlType> sqlList = sqlmap.getSql();
		
		assertThat(sqlList.size(), is(6));
		assertThat(sqlList.get(0).getKey(), is("userAdd"));
		assertThat(sqlList.get(3).getKey(), is("userDeleteAll"));
		assertThat(sqlList.get(5).getKey(), is("userUpdate"));
		assertThat(sqlList.get(5).getValue(), is("update users set name = ?, password = ?, level = ?, login = ?, recommend = ? where id = ?"));
		
	}
	
}
