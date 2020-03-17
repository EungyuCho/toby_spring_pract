package sqlService;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

public class HashMapSqlRegistry implements SqlRegistry{
	private Map<String, String> sqlMap = new HashMap<String, String>();
	
	@Override
	public void registerSql(String key, String sql) {
		sqlMap.put(key, sql);
	}

	public void registerSql(Map<String, String> sqlmap) {
		sqlMap.putAll(sqlmap);
	}
	
	@Override
	public String findSql(String key) throws SqlNotFoundException {
		String sql = sqlMap.get(key);
		if(sql == null)
			throw new SqlNotFoundException(key + "를 이용해서 SQL을 찾을 수 없습니다");
		else 
			return sql;
	}

}
