package sqlService.issuetracker;

import java.util.Map;

import sqlService.SqlRegistry;

public interface UpdatableSqlRegistry extends SqlRegistry{
	public void updateSql(String key, String sql) throws SqlUpdateFailException;
	public void updateSql(Map<String, String> sqlmap) throws SqlUpdateFailException;
}
