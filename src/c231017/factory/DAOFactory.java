package c231017.factory;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;

import c231017.test.user.TestUserDAO;
import c231017.user.UsedSpringUserDAO;

public class DAOFactory {
	@Bean
	public UsedSpringUserDAO usedSpringUserDAO() {
		return new UsedSpringUserDAO(dataSource());
	}

	@Bean
	public TestUserDAO testUserDAO() {
		return new TestUserDAO(dataSource());
	}

	@Bean
	public DataSource dataSource() {
		SimpleDriverDataSource dataSource = new SimpleDriverDataSource();
		
		dataSource.setDriverClass(oracle.jdbc.OracleDriver.class);
		dataSource.setUrl("jdbc:oracle:thin:@localhost:1521/xe");
		dataSource.setUsername("gyun"); // username
		dataSource.setPassword("1234"); // password
		
		return dataSource;
	}
}