package c231023.main.java.com.classJava.board.service;

import java.sql.Connection;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import c231023.main.java.com.classJava.board.dao.BoardDAO;
import c231023.main.java.com.classJava.board.domain.Board;
import c231023.main.java.com.classJava.user.dao.UserDAO;
import c231023.main.java.com.classJava.user.domain.User;

public class BoardService {
	private UserDAO userDAO;
	private BoardDAO boardDAO;
	private DataSource dataSource;
	private PlatformTransactionManager transactionManager;

	public void setTransactionManager(PlatformTransactionManager transactionManager) {
		this.transactionManager = transactionManager;
	}
	
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	public void setUserDAO(UserDAO userDAO) {
		this.userDAO = userDAO;
	}

	public void setBoardDAO(BoardDAO boardDAO) {
		this.boardDAO = boardDAO;
	}

	public void add(Board board) {
		boardDAO.add(board);
	}

	public Board get(int id) {
		Board board = boardDAO.get(id);
		User user = userDAO.get(board.getUserId());
		board.setUser(user);

		return board;
	}

	public List<Board> getAll() {
		List<Board> list = boardDAO.getAll();
		for (int i = 0; i < list.size(); ++i) {
			int userId = list.get(i).getUserId();
			User user = userDAO.get(userId);
			list.get(i).setUser(user);
		}
		return list;
	}

//	public int getCount() {}

	public void updateBefore(Board board, User user) throws Exception {
		// 어떠한 유저(user)가 자신의 글(board)를 수정한다.
		if (board.getUserId() == user.getId()) {
			boardDAO.update(board);
		} else {
			throw new Exception("wrong user");
		}
	}
	
	public void update(Board board, User user) throws Exception {
//		TransactionSynchronizationManager.initSynchronization();
//		Connection conn = DataSourceUtils.getConnection(dataSource);
//		conn.setAutoCommit(false);
		PlatformTransactionManager tm = new DataSourceTransactionManager(dataSource);
		TransactionStatus status = tm.getTransaction(new DefaultTransactionDefinition());
		
		try {
			User writer = board.getUser();
			if (writer.getId() == user.getId()) {
				boardDAO.update(board);
			} else {
				throw new Exception("wrong user");
			}
//			conn.commit();
			tm.commit(status);
		}catch(Exception e) {
//			conn.rollback();
			tm.rollback(status);
			throw e;
		}finally {
//			DataSourceUtils.releaseConnection(conn, dataSource);
//			TransactionSynchronizationManager.unbindResource(dataSource);
//			TransactionSynchronizationManager.clearSynchronization();
			
		}
	}
	
	
	public void updateAll(User user)  throws Exception {
//		TransactionSynchronizationManager.initSynchronization();
//		Connection conn = DataSourceUtils.getConnection(dataSource);
//		conn.setAutoCommit(false);
//		PlatformTransactionManager tm = new DataSourceTransactionManager(dataSource);
		TransactionStatus status = transactionManager.getTransaction(new DefaultTransactionDefinition());
		
		try {
			List<Board> list = getAll();
			for(int i = 0; i<list.size();i++) {
				if(i == 2)user = new User("김남균", "knk", "1234");
				Board board = list.get(i);
				board.setContent("삭제된 컨텐츠");
				User writer = board.getUser();
				if (writer.getId() == user.getId()) {
					boardDAO.update(board);
				} else {
					throw new Exception("wrong user");
				}
			}
//			conn.commit();
			transactionManager.commit(status);
		}catch(Exception e) {
//			conn.rollback();
			transactionManager.rollback(status);
			throw e;
		}finally {
//			DataSourceUtils.releaseConnection(conn, dataSource);
//			TransactionSynchronizationManager.unbindResource(dataSource);
//			TransactionSynchronizationManager.clearSynchronization();
			
		}
	}
	
	
	public void updateAllNotTS(User user)  throws Exception {
		try {
			List<Board> list = getAll();
			for(int i = 0; i<list.size();i++) {
				if(i == 2)user = new User("김남균", "knk", "1234");
				Board board = list.get(i);
				board.setContent("삭제된 컨텐츠");
				User writer = board.getUser();
				if (writer.getId() == user.getId()) {
					boardDAO.update(board);
				} else {
					throw new Exception("wrong user");
				}
			}
		}catch(Exception e) {
			throw e;
		}
	}
	
	
	
}