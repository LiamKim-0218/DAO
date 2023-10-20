package c231020.board;

import java.sql.Date;

import c231019.user.UserBean;

public class BoardBean {
	private int id;
	private int userId;
	private String title;
	private String content;
	private Date createdAT;
	private UserBean user;

	public BoardBean() {
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getUserId() {
		return userId;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getTitle() {
		return title;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getContent() {
		return content;
	}

	public void setCreateAT(Date createdAT) {
		this.createdAT = createdAT;
	}

	public Date getCreateAT() {
		return createdAT;
	}
	
	public void setUser(UserBean user) {
		this.user = user;
	}

	public UserBean getUser() {
		return user;
	}
	
}

/*
 * create table boards ( "id" number generated as identity primary key, "user"
 * varchar2(10) not null, "title" varchar2(30) not null, "content" long not
 * null, "created_at" timestamp default sysdate );
 */
