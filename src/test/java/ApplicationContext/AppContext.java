package ApplicationContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.mail.MailSender;

import com.toby.pract1.UserServiceITest.TestUserService;

import user.DummyMailSender;
import user.UserDao;
import user.UserLevelUpgradePolicy;
import user.service.UserService;

@Configuration
@Import(SqlServiceContext.class)
public class AppContext {
	
	@Bean
	public UserService testUserService() {
		return new TestUserService();
	}
	
	@Bean
	public MailSender mailSender() {
		return new DummyMailSender();
	}
}
