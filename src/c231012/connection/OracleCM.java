package c231012.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import c231012.connection.ConnectionMaker;
// Oracle DB와 통신하는 Connection 을 생성
public class OracleCM implements ConnectionMaker{
   
   public Connection makeConnection() throws Exception {
      Class.forName("oracle.jdbc.OracleDriver");

      return DriverManager.getConnection("" + "jdbc:oracle:thin:@localhost:1521/xe", "gyu", "1234");
   }

}