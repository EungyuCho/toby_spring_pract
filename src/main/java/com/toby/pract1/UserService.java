package com.toby.pract1;

import java.util.List;

import Bean.User;

public interface UserService {
	void add(User user);
	User get(String id);
	List<User> getAll();
	void deleteAll();
	void update(User user);
	
	void upgradeLevels();
}
