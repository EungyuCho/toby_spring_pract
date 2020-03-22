package ApplicationContext;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.oxm.Unmarshaller;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import sqlService.OxmSqlService;
import sqlService.SqlMapConfig;
import sqlService.SqlRegistry;
import sqlService.SqlService;
import sqlService.embeddeddb.EmbeddedDbSqlRegistry;
import user.UserDao;

@Configuration
public class SqlServiceContext {
	@Autowired
	SqlMapConfig sqlmapConfig;
	
	@Bean
	public SqlService sqlService() {
		OxmSqlService sqlService = new OxmSqlService();
		sqlService.setUnmarshaller(unmarshaller());
		sqlService.setSqlRegistry(sqlRegistry());
		sqlService.setSqlmap(this.sqlmapConfig.getSqlMapResource());
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
