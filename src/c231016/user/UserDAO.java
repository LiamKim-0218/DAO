package c231016.user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

public class UserDAO {
	private DataSource dataSource;
	// ()->{}	

	public UserDAO(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public void add(UserBean user) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = dataSource.getConnection();

			String query = "insert into users (name, user_id, password) values (?, ?, ?)";
			pstmt = conn.prepareStatement(query);

			pstmt.setString(1, user.getName());
			pstmt.setString(2, user.getUserId());
			pstmt.setString(3, user.getPassword());
			pstmt.executeUpdate();

		} catch (SQLException e) {
			throw e;
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {

				}
			}
		}
	}

	public void delete(int id) throws Exception {
		Connection conn = dataSource.getConnection();
		String query = "delete from users where id=?";
		PreparedStatement pstmt = conn.prepareStatement(query);

		pstmt.setInt(1, id);
		pstmt.executeUpdate();

		pstmt.close();
		conn.close();
	}

	public UserInterface get(String userId) throws Exception {
		Connection conn = dataSource.getConnection();

		String query = "select * from users where user_id=?";
		PreparedStatement pstmt = conn.prepareStatement(query);

		pstmt.setString(1, userId);
		ResultSet rs = pstmt.executeQuery();

		UserInterface user = null;

		if (rs.next()) {
			user = new UserBean();
			user.setId(rs.getInt("id"));
			user.setName(rs.getString("name"));
			user.setUserId(rs.getString("user_id"));
			user.setPassword(rs.getString("password"));
		}

		rs.close();
		pstmt.close();
		conn.close();
		return user;
	}

	/*
	private PreparedStatement makepstmt(Connection conn, UserInterface user) throws SQLException {
		String query = "insert into users (name, user_id, password) values (?, ?, ?)";
		PreparedStatement pstmt = conn.prepareStatement(query);

		pstmt.setString(1, user.getName());
		pstmt.setString(2, user.getUserId());
		pstmt.setString(3, user.getPassword());

		return pstmt;
	}
	*/
}