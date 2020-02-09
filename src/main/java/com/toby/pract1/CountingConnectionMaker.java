package com.toby.pract1;

import java.sql.Connection;
import java.sql.SQLException;

public class CountingConnectionMaker implements ConnectionMaker {
	
	int counter = 0;
	private ConnectionMaker realConnectionMaker;
	
	public CountingConnectionMaker(ConnectionMaker reaConnectionMaker) {
		this.realConnectionMaker = reaConnectionMaker;
	}
	@Override
	public Connection makeConnection() throws ClassNotFoundException, SQLException {
		this.counter++;
		return realConnectionMaker.makeConnection();
	}

	public int getCount() {
		return this.counter;
	}
}
