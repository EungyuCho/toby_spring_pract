package sqlService;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import sqlService.jaxb.SqlType;
import sqlService.jaxb.Sqlmap;
import user.UserDao;

public class XmlSqlService implements SqlService, SqlRegistry, SqlReader{
	private Map<String, String> sqlMap = new HashMap<String, String>();
	private String sqlmapFile;
	private SqlReader sqlReader;
	private SqlRegistry sqlRegistry;
	

	public void setSqlReader(SqlReader sqlReader) {
		this.sqlReader = sqlReader;
	}
	
	public void setSqlRegistry(SqlRegistry sqlRegistry) {
		this.sqlRegistry = sqlRegistry;
	}
	
	public void setSqlmapFile(String sqlmapFile) {
		this.sqlmapFile = sqlmapFile;
	}
	@PostConstruct
	public void loadSql() {
		this.sqlReader.read(this.sqlRegistry);
	}
	@Override
	public String getSql(String key) throws SqlRetrievalFailureException {
		try {
			return this.sqlRegistry.findSql(key);
		}catch(SqlNotFoundException e) {
			throw new SqlRetrievalFailureException(e);
		}
	}

	@Override
	public void registerSql(String key, String sql) {
		this.sqlMap.put(key, sql);
	}
	

	@Override
	public void registerSql(Map<String, String> sqlmap) {
		this.sqlMap.putAll(sqlmap);
	}

	@Override
	public String findSql(String key) throws SqlNotFoundException {
		String sql = sqlMap.get(key);
		if (sql == null) 
			throw new SqlRetrievalFailureException(key + "를 이용해서 찾을 수 없습니다.");
		else 
			return sql;
	}

	@Override
	public void read(SqlRegistry sqlRegistry) {
		String contextPath = Sqlmap.class.getPackage().getName();
		try {
			JAXBContext context = JAXBContext.newInstance(contextPath);
			Unmarshaller unmarshaller = context.createUnmarshaller();
			InputStream is = UserDao.class.getResourceAsStream(this.sqlmapFile);
			Sqlmap sqlmap = (Sqlmap)unmarshaller.unmarshal(is);
			
			for(SqlType sql : sqlmap.getSql()) {
				sqlRegistry.registerSql(sql.getKey(), sql.getValue());
			}
		} catch (JAXBException e) {
			throw new RuntimeException(e);
		}		
	}


}
