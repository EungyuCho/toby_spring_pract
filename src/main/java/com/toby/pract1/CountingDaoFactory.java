package com.toby.pract1;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CountingDaoFactory {
	
	@Bean
	public UserDao userDao() {
		UserDao userDao = new UserDao();
//		userDao.setConnectionMaker(connectionMaker());
		return userDao;
	}
	
	//CountingConnectionMaker를 통해서 Counting 기능을 구현해뒀음 (MakeConnection Counting)
	@Bean
	public ConnectionMaker connectionMaker() {
		return new CountingConnectionMaker(realConnectionMaker());
	}
	
	//어떤 ConnectionMaker을 Return 할것인지 결정
	@Bean
	public ConnectionMaker realConnectionMaker() {
		return new DConnectionMaker();
	}
	
}
