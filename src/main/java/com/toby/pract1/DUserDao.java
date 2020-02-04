package com.toby.pract1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
//D사 DBConnection ( N사와 다르다고 가정함)
public class DUserDao extends UserDao{

	@Override
	public Connection getConnection() throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.jdbc.Driver");
		Connection c = DriverManager.getConnection(
				"jdbc:mysql://127.0.0.1:3306/mydb", "root", "1234");
		return c;
	}
	
}
