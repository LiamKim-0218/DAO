package c231025.test.java.com.classJava.board;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.lang.reflect.Proxy;
import java.sql.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.PlatformTransactionManager;

import c231024.main.java.com.classJava.board.dao.BoardDAO;
import c231024.main.java.com.classJava.board.domain.Board;
import c231024.main.java.com.classJava.board.service.BoardService;
import c231024.main.java.com.classJava.board.service.BoardServiceImpl;
import c231024.main.java.com.classJava.board.service.BoardServiceTX;
import c231024.main.java.com.classJava.user.dao.UserDAO;
import c231024.main.java.com.classJava.user.domain.User;
import c231024.main.java.com.classJava.user.service.UserService;
import c231025.main.java.com.classJava.board.service.TransactionHandler;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/c231023/dataSource.xml", "applicationContext.xml" })
public class BoardTest {
	@Autowired
	UserDAO userDAO;
	@Autowired
	BoardDAO boardDAO;
	@Autowired
	BoardService boardService;
	@Autowired
	BoardServiceImpl boardServiceImpl;
	@Autowired
	PlatformTransactionManager transactionManager;
	
	
	@Before
	public void initialize() {
		boardServiceImpl.setUserService(new MockUserServiceTest());
		((BoardServiceTX)boardService).setBoardService(boardServiceImpl);
		
		boardDAO.deleteAll();
		User user = userDAO.get("ssm");
		boardService.add(new Board(user, "테스트1", "테스트1 내용"));
		boardService.add(new Board(user, "테스트2", "테스트2 내용"));
		boardService.add(new Board(user, "테스트3", "테스트3 내용"));
	}
	
	@Test
	public void getAll() {
		List<Board> list = boardService.getAll();
		User user = userDAO.get("ssm");
		for(int i = 0;i<list.size();++i) {
			assertThat(list.get(i).getTitle(), is("테스트" + (i + 1)));
			assertThat(list.get(i).getContent(), is("테스트" + (i + 1) + " 내용"));
			assertThat(list.get(i).getUser().getId(), is(user.getId()));
			assertThat(list.get(i).getUser().getName(), is(user.getName()));
			assertThat(list.get(i).getUser().getPassword(), is(user.getPassword()));
		}
	}
	
	@Test
	public void upadteAll() {
		TransactionHandler txHandler = new TransactionHandler();
		txHandler.setTarget(boardServiceImpl);
		txHandler.setPattern("update");
		txHandler.setTransactionManager(transactionManager);
		BoardService txBoardService = (BoardService) Proxy.newProxyInstance(
			getClass().getClassLoader(),
			new Class[] {BoardService.class},
			txHandler);
					
		User user = userDAO.get("ssm");
		txBoardService.updateAll(user);
//		try {
//			boardService.updateAll(user);
//			
//		}catch(Exception e) {
//			e.printStackTrace();
//		}
	}
	
	@Test
	public void add() {
		Board board = new Board(userDAO.get("ssm"), "테스트중입니다.", "23년 10월 24일 테스트");
		boardService.add(board);
	}
	
	@Test
	public void test() {
		MockUserServiceTest userService = new MockUserServiceTest();
		User user = userService.get(1);
		Board board = new Board(user, "임시 데이터 테스트", "테스트중입니다.");
		boardService.add(board);
	}
	
	public static class MockUserServiceTest implements UserService{

		@Override
		public void add(User user) {
			// TODO Auto-generated method stub
			
		}	
		public User get(int id) {
			return new User(1, "이민규", "lmg", "123", new Date(new java.util.Date().getTime()));
		}
		@Override
		public boolean isLogIn(int id) {
			// TODO Auto-generated method stub
			if(id == 1)return true;
			return false;
		}
		
	}
	
}