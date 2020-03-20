package ApplicationContext;


import javax.annotation.Resource;
import javax.sql.DataSource;

import org.hsqldb.Database;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.mail.MailSender;
import org.springframework.oxm.Unmarshaller;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.mysql.jdbc.Driver;
import com.toby.pract1.DummyMailSender;
import com.toby.pract1.UserDao;
import com.toby.pract1.UserDaoJdbc;
import com.toby.pract1.UserLevelUpgradePolicy;
import com.toby.pract1.UserLevelUpgradePolicyBtype;
import com.toby.pract1.UserService;
import com.toby.pract1.UserServiceITest.TestUserService;
import com.toby.pract1.UserServiceImpl;

import sqlService.OxmSqlService;
import sqlService.SqlRegistry;
import sqlService.SqlService;
import sqlService.embeddeddb.EmbeddedDbSqlRegistry;

@EnableTransactionManagement
@Configuration
public class TestApplicationContext {
	@Autowired
	SqlService sqlService;
	@Bean
	public DataSource dataSource() {
		SimpleDriverDataSource dataSource = new SimpleDriverDataSource();
		dataSource.setDriverClass(Driver.class);
		dataSource.setUrl("jdbc:mysql://127.0.0.1:3306/testdb?serverTimezone=UTC");
		dataSource.setUsername("root");
		dataSource.setPassword("5180");
		return dataSource;
	}
	
	@Bean
	public PlatformTransactionManager transactionManager() {
		DataSourceTransactionManager tm = new DataSourceTransactionManager();
		tm.setDataSource(dataSource());
		return tm;
	}


	@Bean
	public UserDao userDao() {
		UserDaoJdbc dao = new UserDaoJdbc();
		dao.setDataSource(dataSource());
		dao.setSqlService(this.sqlService);
		return dao;
	}
	

	@Bean
	public UserService userService() {
		UserServiceImpl service = new UserServiceImpl();
		service.setUserDao(userDao());
		service.setUserLevelUpgradePolicy(userLevelUpgradePolicy());
		service.setMailSender(mailSender());
		return service;
	}
	
	@Bean
	public UserService testUserService() {
		TestUserService testUserService = new TestUserService();
		testUserService.setUserDao(userDao());
		testUserService.setUserLevelUpgradePolicy(userLevelUpgradePolicy());
		testUserService.setMailSender(mailSender());
		return testUserService;
	}
	
	@Bean 
	public MailSender mailSender() {
		return new DummyMailSender();
	}
	
	@Bean
	public UserLevelUpgradePolicy userLevelUpgradePolicy() {
		UserLevelUpgradePolicyBtype policy = new UserLevelUpgradePolicyBtype();
		policy.setUserDao(userDao());
		return policy;
	}
	
	@Bean
	public SqlService sqlService() {
		OxmSqlService sqlService = new OxmSqlService();
		sqlService.setUnmarshaller(unmarshaller());
		sqlService.setSqlRegistry(sqlRegistry());
		return sqlService;
	}
	
	@Bean
	public SqlRegistry sqlRegistry() {
		EmbeddedDbSqlRegistry sqlRegistry = new EmbeddedDbSqlRegistry();
		sqlRegistry.setDataSource(embeddedDatabase());
		return sqlRegistry;
	}
	
	@Bean
	public Unmarshaller unmarshaller() {
		Jaxb2Marshaller unmarshaller = new Jaxb2Marshaller();
		unmarshaller.setContextPath("sqlService.jaxb");
		return unmarshaller;
		
	}
	
	@Bean
	public DataSource embeddedDatabase() {
		return new EmbeddedDatabaseBuilder()
				.setName("embeddedDatabase")
				.setType(EmbeddedDatabaseType.HSQL)
				.addScript("classpath:sqlService/embeddeddb/sqlRegistryschema.sql")
				.build();
	}
}
