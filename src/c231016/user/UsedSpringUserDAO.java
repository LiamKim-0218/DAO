package c231016.user;

import java.sql.ResultSet;
import java.sql.SQLException;
import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

public class UsedSpringUserDAO {
   private JdbcTemplate jdbcTemplate;

   public UsedSpringUserDAO(DataSource dataSource) {
      jdbcTemplate = new JdbcTemplate(dataSource);
   }

   public void add(UserInterface user) throws SQLException {
      jdbcTemplate.update("insert into users (name, user_id, password) values (?, ?, ?)", user.getName(),
            user.getUserId(), user.getPassword());
   }

   public UserInterface get(String userId) throws SQLException {

//      jdbcTemplate.queryForList("qeury",UserBean.class,"? 1","? 2");

      UserInterface user = jdbcTemplate.queryForObject("select * from users where user_id=?", new Object[] { userId },
            new RowMapper<UserBean>() {
               @Override
               public UserBean mapRow(ResultSet rs, int rowNum) throws SQLException {
                  UserBean user = new UserBean();
                  user.setId(rs.getInt("Id"));
                  user.setName(rs.getString("name"));
                  user.setUserId(rs.getString("user_id"));
                  user.setPassword(rs.getString("password"));
                  return user;

               }
            });
      return user;
   }
}