package com.toby.pract1;

import java.sql.SQLException;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

//클라이언트라고 가정
public class UserDaoTest {
	public static void main(String[] args) throws ClassNotFoundException, SQLException{
		//Factory를 이용해서 Factory에게 Dao선택권한을 넘겨줌
//		ApplicationContext context = new AnnotationConfigApplicationContext(DaoFactory.class);
		ApplicationContext context = new GenericXmlApplicationContext("applicationContext.xml");
		UserDao dao = context.getBean("userDao", UserDao.class);
		
		User user = new User();
		user.setId("whiteship");
		user.setName("백기선");
		user.setPassword("married");
		
		dao.add(user);
		
		System.out.println(user.getId() + "등록 성공");
		
		User user2 = dao.get(user.getId());
		
		if(!user.getName().equals(user2.getName())) {
			System.out.println("테스트 실패! (name)");
		}
		else if(!user.getPassword().equals(user2.getPassword())) {
			System.out.println("테스트 실패! (password)");
		}
		else {
			System.out.println("조회 테스트 성공!");
		}
		
	}
}
