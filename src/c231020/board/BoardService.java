package c231020.board;

import c231019.user.UserBean;
import c231019.user.UserDAO;

public class BoardService {

	private BoardDAO boardDAO;
	private UserDAO userDAO;
	
	public void setBoardDAO(BoardDAO boardDAO) {
		this.boardDAO = boardDAO;
	}
	
	public void setUserDAO(UserDAO userDAO) {
		this.userDAO = userDAO;
	}
	
	public void add(BoardBean board, UserBean user) {
		board.setUserId(user.getId());
		boardDAO.add(board);
	}
	
	public BoardBean get(int id) {
		BoardBean board = boardDAO.get(id);
		//유저 정보 매치
		UserBean user = userDAO.get(board.getUserId());
		board.setUser(user);
		
		return board;
	}
	
}
