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
import org.springframework.oxm.Unmarshaller;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import com.mysql.jdbc.Driver;
import sqlService.OxmSqlService;
import sqlService.SqlRegistry;
import sqlService.SqlService;
import sqlService.embeddeddb.EmbeddedDbSqlRegistry;
import user.UserDao;
import user.UserLevelUpgradePolicy;
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
	
}
