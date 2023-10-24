package c231024.main.java.com.classJava.board.service;

import java.util.List;

import c231024.main.java.com.classJava.board.domain.Board;
import c231024.main.java.com.classJava.user.domain.User;


public interface BoardService {
	public void add(Board board, int userId);
	public Board get(int id);
	public List<Board> getAll();
	public void update(Board board, User user);
	public void updateAll(User user);
}
