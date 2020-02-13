package com.toby.pract1;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

public class UserDao {
	private DataSource dataSource;
	private JdbcTemplate jdbcTemplate;
	
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
		this.dataSource = dataSource;
	}
	public void add(final User user) throws ClassNotFoundException, SQLException{
		this.jdbcTemplate.update("insert into users(id, name, password) values(?,?,?)",
				user.getId(), user.getName(), user.getPassword());
	}

	
	public User get(String id) throws ClassNotFoundException, SQLException{
		return this.jdbcTemplate.queryForObject("select * from users where id =?", 
				new Object[] {id},
				new RowMapper<User> (){
					@Override
					public User mapRow(ResultSet rs, int rowNum) throws SQLException{
						User user = new User();
						user.setId(rs.getString("id"));
						user.setName(rs.getString("name"));
						user.setPassword(rs.getString("password"));
						return user;
					}
				}
			);
	}
	
	public List<User> getAll() {
		return this.jdbcTemplate.query("select * from users order by id", 
				new RowMapper<User>() {
					@Override
					public User mapRow(ResultSet rs, int rowNum) throws SQLException {
						User user = new User();
						user.setId(rs.getString("id"));
						user.setName(rs.getString("name"));
						user.setPassword(rs.getString("password"));
						return user;
					}
		});
	}
	
	public void deleteAll() throws SQLException{
		this.jdbcTemplate.update("delete from users");
	}
	public int getCount() throws SQLException{
		
		/*
		 * 이전에 작성한 템플릿 메서드 타입 query은 템플릿 안에서 동작하며 첫번째 psc는 sql을 콜백해주고 두번째 resultset은 결과데이터를 콜백함)
		return this.jdbcTemplate.query(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				return con.prepareStatement("select count(*) from users");
			}
		}, new ResultSetExtractor<Integer>() {
			public Integer extractData(ResultSet rs) throws SQLException, DataAccessException{
				rs.next();
				return rs.getInt(1);
			}
		});
		*/
		return this.jdbcTemplate.queryForInt("select count(*) from users");
	}
}
