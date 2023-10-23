package c231023.main.java.com.classJava.board.dao;

import java.util.List;

import c231023.main.java.com.classJava.board.domain.Board;

public interface BoardDAO {
	public void add(Board board);
	public Board get(int id);
	public List<Board> getAll();
	public int getCount();
	public void update(Board board);
	public void deleteAll();
}