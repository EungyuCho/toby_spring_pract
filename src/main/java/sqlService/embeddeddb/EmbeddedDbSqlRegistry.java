package sqlService.embeddeddb;

import java.util.Map;

import javax.sql.DataSource;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;

import sqlService.SqlNotFoundException;
import sqlService.issuetracker.SqlUpdateFailException;
import sqlService.issuetracker.UpdatableSqlRegistry;

public class EmbeddedDbSqlRegistry implements UpdatableSqlRegistry{

	SimpleJdbcTemplate jdbc;
	
	public void setDataSource(DataSource dataSource) {
		jdbc = new SimpleJdbcTemplate(dataSource);
	}
	
	@Override
	public void registerSql(String key, String sql) {
		jdbc.update("insert into sqlmap(key_,sql_) values(?,?)", key, sql);
	}

	@Override
	public void registerSql(Map<String, String> sqlmap) {
		for(Map.Entry<String, String> entry : sqlmap.entrySet()) {
			registerSql(entry.getKey(), entry.getValue());
		}
	}

	@Override
	public String findSql(String key) throws SqlNotFoundException {
		try {
			return jdbc.queryForObject("select sql_ from sqlmap where key_= ?", String.class, key);
		}catch (EmptyResultDataAccessException e) {
			throw new SqlNotFoundException(key + "에 해당하는 SQL을 찾을 수 없습니다", e);
		}
	}

	@Override
	public void updateSql(String key, String sql) throws SqlUpdateFailException {
		int affected = jdbc.update("update sqlmap set sql_ = ? where key_ = ?", sql, key);
		if(affected == 0) 
			throw new SqlUpdateFailException(key + "에 해당하는 SQL을 찾을 수 없습니다");
	}

	@Override
	public void updateSql(Map<String, String> sqlmap) throws SqlUpdateFailException {
		for(Map.Entry<String, String> entry : sqlmap.entrySet()) {
			updateSql(entry.getKey(), entry.getValue());
		}
	}

}
