package c231023.test.java.com.classJava.board;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import c231023.main.java.com.classJava.board.dao.BoardDAO;
import c231023.main.java.com.classJava.board.domain.Board;
import c231023.main.java.com.classJava.board.service.BoardService;
import c231023.main.java.com.classJava.user.dao.UserDAO;
import c231023.main.java.com.classJava.user.domain.User;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/c231023/dataSource.xml", "applicationContext.xml" })
public class BoardTest {
	@Autowired
	UserDAO userDAO;
	@Autowired
	BoardDAO boardDAO;
	@Autowired
	BoardService boardService;
	
	@Before
	public void initialize() {
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
		User user = userDAO.get("ssm");
		try {
			boardService.updateAll(user);
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void upadteAllNotTS() {
		User user = userDAO.get("ssm");
		try {
			boardService.updateAllNotTS(user);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
}