package c231020.test.board;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;

public class BoardTestDAO {
	private JdbcTemplate jdbcTemplate;

	public void setDataSource(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}

	
	public void drop() {
		jdbcTemplate.execute("BEGIN\r\n" + "   EXECUTE IMMEDIATE 'DROP TABLE boards';\r\n" + "EXCEPTION\r\n"
				+ "   WHEN OTHERS THEN\r\n" + "      IF SQLCODE != -942 THEN\r\n" + "         RAISE;\r\n"
				+ "      END IF;\r\n" + "END;");
	}

	public void create() {
		int maxCount = 3;
		while (maxCount-- > 0) {
			try {
				jdbcTemplate.execute("create table boards ("
						+ "		\"id\" number generated as identity primary key,\r\n"
						+ "		\"title\" varchar2(30) not null,\r\n"
						+ "		\"content\" long not null,\r\n"
						+ "		\"created_at\" timestamp default sysdate,\r\n"
						+ "		\"user_id\" number not null\r\n"
						+ "	)");
				return;
			} catch (Exception e) {
				System.out.println(maxCount + "번 남았어");
				e.printStackTrace();
			}
		}
	}
}