package com.toby.pract1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

//N사에서의 데이터베이스 정의
public class NUserDao extends UserDao{

	@Override
	public Connection getConnection() throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.jdbc.Driver");
		Connection c = DriverManager.getConnection(
				"jdbc:mysql://127.0.0.1:3306/mydb", "root", "1234");
		return c;
	}
	
}
