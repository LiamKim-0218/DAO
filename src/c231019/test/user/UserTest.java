package c231019.test.user;

import static org.junit.Assert.assertThat;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;

import java.sql.SQLException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import c231019.user.UserBean;
import c231019.user.UserDAO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"applicationContext.xml"})
public class UserTest {
	@Autowired 
	UserDAO dao;
	@Autowired 
	UserTestDAO testDao;
	
	private UserBean user1 = new UserBean();
	
	@Before
	public void initialize() {
		try {
			testDao.drop();
			testDao.create();
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
	
		user1.setName("정경훈");
		user1.setUserId("jkh4");
		user1.setPassword("1234");
		dao.add(user1);
	}

	@After
	public void dropTable() {
//		testDao.drop();
	}

	@Test
	public void add() throws SQLException {
		
		UserBean user = new UserBean();
		user.setName("이민규");
		user.setUserId("lmg");
		user.setPassword("1234");
		dao.add(user);
	}

	@Test
	public void get() throws SQLException {
	
		UserBean createdUser = dao.get(user1.getUserId());
		assertThat(createdUser.getId(), is(1));
		assertThat(createdUser.getName(), is(user1.getName()));
		assertThat(createdUser.getUserId(), is(user1.getUserId()));
		assertThat(createdUser.getPassword(), is(user1.getPassword()));
	}

	@Test
	public void addAndGet() throws SQLException {
	

		UserBean user = new UserBean();
		user.setName("정경훈");
		user.setUserId("jkh2");
		user.setPassword("1234");
		dao.add(user);

		UserBean createdUser = dao.get(user.getUserId());
		assertThat(createdUser.getName(), is(user.getName()));
		assertThat(createdUser.getUserId(), is(user.getUserId()));
		assertThat(createdUser.getPassword(), is(user.getPassword()));
	}
	
	@Test(expected=DuplicateKeyException.class)
	public void duplicate() {
		
		UserBean user2 = new UserBean();
		user2.setUserId("asdf");
		user2.setName("asdf");
		user2.setPassword("asdf");
		dao.add(user2);
		
		UserBean user3 = new UserBean();
		user3.setUserId("asdf");
		user3.setName("asdf");
		user3.setPassword("asdf");
		dao.add(user3);
	}

}