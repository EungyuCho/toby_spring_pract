package com.toby.pract1;

import java.util.List;

import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import Bean.User;

@Transactional
public interface UserService {
	void add(User user);
	void deleteAll();
	void update(User user);
	@Transactional(propagation = Propagation.REQUIRES_NEW, isolation = Isolation.SERIALIZABLE)
	void upgradeLevels();
	@Transactional(readOnly = true)
	User get(String id);
	@Transactional(readOnly = true)
	List<User> getAll();
}
