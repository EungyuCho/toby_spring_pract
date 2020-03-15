package sqlService;

import javax.annotation.PostConstruct;


public class BaseSqlService implements SqlService{
	protected SqlReader sqlReader;
	protected SqlRegistry sqlRegistry;
	
	public void setSqlReader(SqlReader sqlReader) {
		this.sqlReader = sqlReader;
	}
	public void setSqlRegistry(SqlRegistry sqlRegistry) {
		this.sqlRegistry = sqlRegistry;
	}
	
	@PostConstruct
	public void loadSql() {
		this.sqlReader.read(sqlRegistry);
	}
	@Override
	public String getSql(String key) throws SqlRetrievalFailureException {
		return sqlRegistry.findSql(key);
	}

}
