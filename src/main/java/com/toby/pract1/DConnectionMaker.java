package com.toby.pract1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DConnectionMaker implements ConnectionMaker{

	@Override
	public Connection makeConnection() throws ClassNotFoundException, SQLException {
		//D사일 경우의 DB라고 가정
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection c = DriverManager.getConnection(
//				"jdbc:mysql://127.0.0.1:3306/mydb", "root", "1234");
				"jdbc:mysql://127.0.0.1:3306/mydb?serverTimezone=UTC", "root", "5180");
		return c;
	}

}
