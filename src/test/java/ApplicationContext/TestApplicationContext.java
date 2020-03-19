package ApplicationContext;


import javax.sql.DataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;

import com.mysql.jdbc.Driver;

@Configuration
@ImportResource("/test-applicationContext.xml")
public class TestApplicationContext {

	@Bean
	public DataSource dataSource() {
		SimpleDriverDataSource dataSource = new SimpleDriverDataSource();
		dataSource.setDriverClass(Driver.class);
		dataSource.setUrl("jdbc:mysql://127.0.0.1:3306/testdb?serverTimezone=UTC");
		dataSource.setUsername("root");
		dataSource.setPassword("5180");
		return dataSource;
	}
	
	
}
