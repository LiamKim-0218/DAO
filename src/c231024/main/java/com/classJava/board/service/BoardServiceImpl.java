package c231024.main.java.com.classJava.board.service;

import java.util.List;

import c231024.main.java.com.classJava.board.dao.BoardDAO;
import c231024.main.java.com.classJava.board.domain.Board;
import c231024.main.java.com.classJava.user.dao.UserDAO;
import c231024.main.java.com.classJava.user.domain.User;
import c231024.main.java.com.classJava.user.service.UserService;

public class BoardServiceImpl implements BoardService {
	private UserDAO userDAO;
	private BoardDAO boardDAO;
	private UserService userService;
	
	public void setUserDAO(UserDAO userDAO) {
		this.userDAO = userDAO;
	}

	public void setBoardDAO(BoardDAO boardDAO) {
		this.boardDAO = boardDAO;
	}


	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	
	public void add(Board board, int userId) {
		if(userService.isLogIn(userId)) boardDAO.add(board);
		else new RuntimeException("로그인 필요");
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


	public void updateBefore(Board board, User user) throws Exception {
		if (board.getUserId() == user.getId()) {
			boardDAO.update(board);
		} else {
			throw new Exception("wrong user");
		}
	}
	
	
	
	public void update(Board board, User user) {
		User writer = board.getUser();
		if (writer.getId() == user.getId()) {
			boardDAO.update(board);
		} else {
			throw new RuntimeException("wrong user");
		}
		
	}
	
	
	
	
	public void updateAll(User user) {
		List<Board> list = getAll();
		for(int i = 0; i<list.size();i++) {
//			if(i == 2)user = new User("김남균", "knk", "1234");
			Board board = list.get(i);
			board.setContent("삭제된 컨텐츠");
			User writer = board.getUser();
			if (writer.getId() == user.getId()) {
				boardDAO.update(board);
			} else {
				throw new RuntimeException("wrong user");
			}
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