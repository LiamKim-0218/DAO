package c231012.factory;

import org.springframework.context.annotation.Bean;

import c231012.connection.*;
import c231012.user.*;

// 전체적인 모든 DAO를 관리(생성 삭제 등등)
public class DAOFactory {
//   private UserDAO USERDAOINSTANCE;
//   private ConnectionMaker CONNECTIONMAKERINSTANCE;

   @Bean
   public UserDAO userDAO() {
      return new UserDAO(connectionMaker());
//      if (USERDAOINSTANCE == null)
//         USERDAOINSTANCE = new UserDAO(connectionMaker());
//      return USERDAOINSTANCE;
   }

   @Bean
   public ConnectionMaker connectionMaker() {
//      if (CONNECTIONMAKERINSTANCE == null)
//         CONNECTIONMAKERINSTANCE = new OracleCM();
      return new OracleCM();
   }
}