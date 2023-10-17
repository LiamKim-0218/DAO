package c231016.user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public interface StatementStrategy {
	PreparedStatement makePstmt(Connection conn) throws SQLException;
}