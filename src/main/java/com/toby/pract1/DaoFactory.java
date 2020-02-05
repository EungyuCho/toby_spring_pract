package com.toby.pract1;

//Factory를 분리함으로써 어플리케이션 컴포넌트 역할의 오브젝트와 어플리케이션 구조를 결정하는 오브젝트를 분리할수있음.
public class DaoFactory {
	public UserDao userDao() {
		return new UserDao(connectionMaker());
	}
	//오브젝트 생성도 메소드로 분리해줌 (DAO가 많아질 경우를 대비해서 메소드 분리)
	public ConnectionMaker connectionMaker() {
		return new DConnectionMaker();
	}
}
