package c231019.user;

import java.sql.ResultSet;

import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import c231020.board.BoardBean;


public class UserDAO {
	private JdbcTemplate jdbcTemplate;

	public void setDataSource(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}

	private RowMapper<UserBean> mapper = new RowMapper<UserBean>() {
		@Override
		public UserBean mapRow(ResultSet rs, int rowNum) throws SQLException {
			// TODO Auto-generated method stub
			UserBean user = new UserBean();
			user.setId(rs.getInt("id"));
			user.setName(rs.getString("name"));
			user.setUserId(rs.getString("user_id"));
			user.setPassword(rs.getString("password"));
			
			
			return user;
		}
	};
	
	public void add(UserBean user) {
		jdbcTemplate.update("insert into users (name, user_id, password) values (?, ?, ?)", user.getName(),
				user.getUserId(), user.getPassword());
	}
	
	public UserBean get(int id) {
		return jdbcTemplate.queryForObject("select * from users where id=?", 
				new Object[] { id }, mapper);
	}
	
	public UserBean get(String userid) {
		return jdbcTemplate.queryForObject("select * from users where user_id=?", 
				new Object[] { userid }, 
				mapper);
	}

	/*
	 * public UserBean get(String userId) { return
	 * jdbcTemplate.queryForObject("select * from users where user_id=?", new
	 * Object[] { userId }, new RowMapper<UserBean>() {
	 * 
	 * @Override public UserBean mapRow(ResultSet rs, int rowNum) throws
	 * SQLException { // TODO Auto-generated method stub UserBean user = new
	 * UserBean(); user.setId(rs.getInt("id")); user.setName(rs.getString("name"));
	 * user.setUserId(rs.getString("user_id"));
	 * user.setPassword(rs.getString("password"));
	 * 
	 * return user; } }); }
	 */
}