package c231024.main.java.com.classJava.user.service;

import c231024.main.java.com.classJava.user.dao.UserDAO;
import c231024.main.java.com.classJava.user.domain.User;

public class UserServiceImpl implements UserService {
	private UserDAO userDAO;
	public void setUserDAO(UserDAO userDAO) {
		this.userDAO = userDAO;
	}
	
	public void add(User user) {
		userDAO.add(user);
	}
	
	public boolean isLogIn(int id) {
		return false;
	}
}