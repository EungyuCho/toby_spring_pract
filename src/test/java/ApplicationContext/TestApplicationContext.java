package ApplicationContext;


import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
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
import com.toby.pract1.UserServiceITest.TestUserService;

import sqlService.OxmSqlService;
import sqlService.SqlRegistry;
import sqlService.SqlService;
import sqlService.embeddeddb.EmbeddedDbSqlRegistry;
import user.DummyMailSender;
import user.UserDao;
import user.UserLevelUpgradePolicy;
import user.UserLevelUpgradePolicyBtype;
import user.service.UserService;

@EnableTransactionManagement
@Configuration
@ComponentScan(basePackages = "user")
public class TestApplicationContext {
	@Autowired
	UserDao userDao;
	@Autowired
	UserService userService;
	@Autowired
	UserLevelUpgradePolicy userLevelUpgradePolicy;
	
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
	public UserService testUserService() {
		TestUserService testUserService = new TestUserService();
		testUserService.setUserDao(this.userDao);
		testUserService.setMailSender(mailSender());
		return testUserService;
	}
	
	@Bean
	public MailSender mailSender() {
		return new DummyMailSender();
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
