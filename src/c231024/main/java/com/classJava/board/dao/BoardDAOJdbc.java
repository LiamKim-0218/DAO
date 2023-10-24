package c231024.main.java.com.classJava.board.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import c231024.main.java.com.classJava.board.domain.Board;

public class BoardDAOJdbc implements BoardDAO {
	private JdbcTemplate jdbcTemplate;

	public void setDataSource(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}

	private RowMapper<Board> mapper = new RowMapper<Board>() {
		@Override
		public Board mapRow(ResultSet rs, int rowNum) throws SQLException {
			// TODO Auto-generated method stub
			return new Board(rs.getInt("id"), rs.getInt("user_id"), rs.getString("title"), rs.getString("content"),
					rs.getDate("created_at"));
		}
	};

	public void add(Board board) {
		jdbcTemplate.update("insert into boards (\"title\", \"user_id\", \"content\") values (?, ?, ?)",
				board.getTitle(), board.getUser().getId(), board.getContent());
	}

	public Board get(int id) {
		return jdbcTemplate.queryForObject("select * from boards where \"id\"=?", new Object[] { id }, mapper);
	}

	public List<Board> getAll() {
		return jdbcTemplate.query("select * from boards order by \"id\"", mapper);

	}

	public int getCount() {
		return jdbcTemplate.queryForInt("select count(*) from boards");

	}

	public void update(Board board) {
		jdbcTemplate.update("update boards set \"title\"=?, \"content\"=? where \"id\"=?", board.getTitle(),
				board.getContent(), board.getId());

	}

	public void deleteAll() {
		jdbcTemplate.update("delete from boards");
	}
}