package user;

import java.util.List;

import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import Bean.User;

@TransactionConfiguration(defaultRollback = false)
@Transactional()
public interface UserDao {
	void add(User user);
	User get(String id);
	List<User> getAll();
	void deleteAll();
	int getCount();
	public void update(User user);
}
