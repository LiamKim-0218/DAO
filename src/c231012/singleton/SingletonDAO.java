package c231012.singleton;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import c231012.connection.*;
import c231012.user.*;

public class SingletonDAO {

   private ConnectionMaker maker;

   public static SingletonDAO INSTANCE;

   //싱글톤 구현은 private로 한다.
   
   public SingletonDAO(ConnectionMaker maker) {
      this.maker = maker;
   }

   public static SingletonDAO getInstance() {
      return INSTANCE;
   }

   public static SingletonDAO getInstance(ConnectionMaker maker) {
      if (INSTANCE == null)
         INSTANCE = new SingletonDAO(maker);
      return INSTANCE;
   }

   public void add(UserBean user) throws Exception {

      Connection conn = maker.makeConnection();
      String query = "insert into users (name, user_id,password) values (?,?,?)";
      PreparedStatement pstmt = conn.prepareStatement(query);

      pstmt.setString(1, user.getName());
      pstmt.setString(2, user.getUserId());
      pstmt.setString(3, user.getPassword());
      pstmt.executeUpdate();

      pstmt.close();
      conn.close();
   }

   public UserBean get(String userId) throws Exception {
      Connection conn = maker.makeConnection();

      String query = "select * from users where user_id=?";
      PreparedStatement pstmt = conn.prepareStatement(query);

      pstmt.setString(1, userId);
      ResultSet rs = pstmt.executeQuery();

      UserBean user = null;

      if (rs.next()) {
         user = new UserBean();
         user.setId(rs.getInt("id"));
         user.setName(rs.getString("name"));
         user.setUserId(rs.getString("user_id"));
         user.setPassword(rs.getString("password"));
      }

      pstmt.close();
      conn.close();
      return user;
   }
}