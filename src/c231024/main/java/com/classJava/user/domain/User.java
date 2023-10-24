package c231024.main.java.com.classJava.user.domain;

import java.sql.Date;

public class User {
	private int id;
	private String name;
	private String userId;
	private String password;
	private Date createdAt;

	public User() {
	}

	public User(String name, String userId, String password) {
		this.name = name;
		this.userId = userId;
		this.password = password;
	}

	public User(int id, String name, String userId, String password, Date createdAt) {
		this.id = id;
		this.name = name;
		this.userId = userId;
		this.password = password;
		this.createdAt = createdAt;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserId() {
		return userId;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPassword() {
		return password;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getCreatedAt() {
		return createdAt;
	}
}