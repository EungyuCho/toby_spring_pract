package com.toby.pract1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class UserDao {
	public void add(User user) throws ClassNotFoundException, SQLException{
		Connection c = getConnection();
		PreparedStatement ps = c.prepareStatement(
				"insert into users(id, name, password) values(?,?,?)");
		ps.setString(1, user.getId());
		ps.setString(2, user.getName());
		ps.setString(3, user.getPassword());
		
		ps.executeUpdate();
		ps.close();
		c.close();
	}
	
	public User get(String id) throws ClassNotFoundException, SQLException{
		Connection c = getConnection();
		PreparedStatement ps = c.prepareStatement(
				"select * from users where id = ?");
		ps.setString(1, id);
		
		ResultSet rs = ps.executeQuery();
		rs.next();
		User user = new User();
		user.setId(rs.getString("id"));
		user.setName(rs.getString("name"));
		user.setPassword(rs.getString("password"));
		
		rs.close();
		ps.close();
		c.close();
		
		return user;
	}
	//고객사 2곳이 서로 다른 데이터베이스에 접근해야한다고 가정함
	//상속하는 경우 하위 정의 클래스에서 Connection만 재 정의해줌으로써 서로 다른 DB에서 동일한 동작을 할수있음
	public abstract Connection getConnection() throws ClassNotFoundException, SQLException;
}
