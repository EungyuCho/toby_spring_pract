package sqlService;

import java.util.Map;

public interface SqlRegistry {
	void registerSql(String key, String sql);
	
	void registerSql(Map<String, String> sqlmap);

	String findSql(String key) throws SqlNotFoundException;
}
