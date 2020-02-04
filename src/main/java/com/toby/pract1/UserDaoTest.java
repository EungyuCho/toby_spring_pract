package com.toby.pract1;

import java.sql.SQLException;
//클라이언트라고 가정
public class UserDaoTest {
	public static void main(String[] args) throws ClassNotFoundException, SQLException{
		//구현하는 Client에서 책임을 가짐
		ConnectionMaker connetionMaker = new DConnectionMaker();
		UserDao dao = new UserDao(connetionMaker);
		
	}
}
