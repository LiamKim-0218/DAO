package c231013.factory;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;

import c231012.connection.*;
import c231013.user.UserDAO;

// 전체적인 모든 DAO를 관리(생성 등등)
public class DAOFactory {
//	private UserDAO USERDAOINSTANCE;
//	private ConnectionMaker CONNECTIONMAKERINSTANCE;
	@Bean
	public UserDAO userDAO() {
		return new UserDAO(dataSource());
//		if(USERDAOINSTANCE == null) USERDAOINSTANCE = new UserDAO(connectionMaker());
//		return USERDAOINSTANCE;
	}

	@Bean
	public ConnectionMaker connectionMaker() {
		return new OracleCM();
//		if(CONNECTIONMAKERINSTANCE == null) CONNECTIONMAKERINSTANCE = new OracleCM();
//		return CONNECTIONMAKERINSTANCE;
	}

	@Bean
	public DataSource dataSource() {
		SimpleDriverDataSource dataSource = new SimpleDriverDataSource();
		
		dataSource.setDriverClass(oracle.jdbc.OracleDriver.class);
		dataSource.setUrl("jdbc:oracle:thin:@localhost:1521/xe");
		dataSource.setUsername("gyu"); // username
		dataSource.setPassword("1234"); // password
		
		return dataSource;
	}
}