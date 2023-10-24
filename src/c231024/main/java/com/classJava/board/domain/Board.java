package c231024.main.java.com.classJava.board.domain;

import java.sql.Date;

import c231024.main.java.com.classJava.user.domain.User;

public class Board {

   private int id;
   private int userId;
   private String title;
   private User user;
   private String content;
   private Date createdAt;
   
   public Board() {}
   
   public Board(User user,String title,String content) {
      this.user=user;
      this.title=title;
      this.content=content;
      
   }
   public Board(int id,int userId,String title,String content,Date createdAt) {
      this.id=id;
      this.userId=userId;
      this.title=title;
      this.content=content;
      this.createdAt=createdAt;
   } 
   
   
   
   public void setId(int id) {
      this.id=id;
   }
   public int getId() {
      return id;
   }
   public void setUserId(int userId) {
      this.userId=userId;
   }
   public int getUserId() {
      return userId;
   }
   public void setUser(User user) {
      this.user=user;
   }
   public User getUser() {
      return user;
   }
   public void setTitle(String title) {
      this.title=title;
   }
   public String getTitle() {
      return title;
   }
   public void setContent(String content) {
      this.content=content;
   }
   public String getContent() {
      return content;
   }
   public void setCreatedAt(Date createdAt) {
      this.createdAt=createdAt;
   }
   public Date getCreatedAt() {
      return createdAt;
   }
}