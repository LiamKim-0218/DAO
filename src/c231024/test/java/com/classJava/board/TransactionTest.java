package c231024.test.java.com.classJava.board;

import java.sql.Connection;
import java.sql.PreparedStatement;

import org.springframework.jdbc.datasource.SimpleDriverDataSource;

public class TransactionTest {
	public static void main(String[] args) throws Exception {
		SimpleDriverDataSource dataSource = new SimpleDriverDataSource();
		
		Connection conn = dataSource.getConnection();
		conn.setAutoCommit(false);
		try {
			PreparedStatement pstmt1 = conn.prepareStatement(""); 
			pstmt1.executeQuery(); // 성공
			PreparedStatement pstmt2 = conn.prepareStatement(""); 
			pstmt2.executeQuery(); // 실패
			conn.commit(); // auto Commit을 안할 경우 필수
		}catch(Exception e) {
			conn.rollback();
			throw e;
		}finally {conn.close();}
	}
}