package com.toby.pract1;

import java.sql.SQLException;
//클라이언트라고 가정
public class UserDaoTest {
	public static void main(String[] args) throws ClassNotFoundException, SQLException{
		//Factory를 이용해서 Factory에게 Dao선택권한을 넘겨줌
		UserDao dao = new DaoFactory().userDao();

		User user = new User();
		user.setId("whiteship");
		user.setName("백기선");
		user.setPassword("married");
		
		dao.add(user);
		
		System.out.println(user.getId() + "등록 성공");
		
		User user2 = dao.get(user.getId());
		System.out.println(user2.getId());
		System.out.println(user2.getName());
		System.out.println(user2.getPassword());
		System.out.println(user2.getId() + "조회 성공");
		
	}
}
