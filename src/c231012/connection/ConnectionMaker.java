package c231012.connection;

import java.sql.Connection;

public interface ConnectionMaker {

   public Connection makeConnection() throws Exception;

}