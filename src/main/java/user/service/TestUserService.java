package user.service;

import java.util.List;
import Bean.User;
import Exception.TestUserServiceException;

public class TestUserService extends UserServiceImpl{
	private String id = "madnite1";
	
	@Override
	protected void upgradeLevel(User user) {
		if(user.getId().equals(this.id)) throw new TestUserServiceException();
		super.upgradeLevel(user);
	}
	@Override
	public List<User> getAll(){
		for(User user: super.getAll()) {
			super.update(user);
		}
		return null;
	}
}
