package c231023.test.java.com.classJava.user;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import c231023.main.java.com.classJava.user.dao.UserDAO;
import c231023.main.java.com.classJava.user.domain.User;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="applicationContext.xml")
public class UserTest {
	@Autowired
	UserDAO dao;
	
	@Before
	public void initialize() {
		dao.deleteAll();
		User user1 = new User("권원준", "kwj", "qwer");
		dao.add(user1);
		dao.add(new User("임현규", "lhg", "486"));
	}
	
	@Test
	public void get() {
		User createdUser = dao.get("kwj");
		assertThat(createdUser.getName(), is("권원준"));
		assertThat(createdUser.getPassword(), is("qwer"));
		createdUser = dao.get("lhg");
		assertThat(createdUser.getName(), is("임현규"));
		assertThat(createdUser.getPassword(), is("486"));
	}
	
	
	
}