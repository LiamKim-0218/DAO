package c231026.test.java.com.classJava.board;

import java.sql.Date;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.aop.framework.ProxyFactoryBean;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import c231024.main.java.com.classJava.board.dao.BoardDAO;
import c231024.main.java.com.classJava.board.domain.Board;
import c231024.main.java.com.classJava.board.service.BoardService;
import c231024.main.java.com.classJava.user.dao.UserDAO;
import c231024.main.java.com.classJava.user.domain.User;
import c231026.main.java.com.classJava.board.service.TxProxyFactoryBean;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
public class BoardTest {
	@Autowired
	BoardDAO boardDAO;
	@Autowired
	UserDAO userDAO;
	@Autowired
//	ApplicationContext context;
	BoardService boardService;
	
	
	@Before
	public void initialize() throws BeansException, Exception {
//		BoardService boardService = (BoardService) context.getBean("&boardService",ProxyFactoryBean.class).getObject();
		
		boardDAO.deleteAll();
		User user = userDAO.get("ssm");
		boardService.add(new Board(user, "테스트1", "테스트1 내용"));
		boardService.add(new Board(user, "테스트2", "테스트2 내용"));
		boardService.add(new Board(user, "테스트3", "테스트3 내용"));
	}
	
	@Test
	public void upadteAll() throws BeansException, Exception {
//		TransactionHandler txHandler = new TransactionHandler();
//		txHandler.setTarget(boardServiceImpl);
//		txHandler.setPattern("update");
//		txHandler.setTransactionManager(transactionManager);
//		BoardService txBoardService = (BoardService) Proxy.newProxyInstance(
//			getClass().getClassLoader(),
//			new Class[] {BoardService.class},
//			txHandler);
//					
		User user = userDAO.get("ssm");
//		BoardService boardService = (BoardService)
//				context.getBean(
//						"&boardService",
//						ProxyFactoryBean.class)
//				.getObject();
		
//		txBoardService.updateAll(user);
		try {
			boardService.updateAll(user);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
//	@Test
//	public void test() {
//		boardDAO.get(1);
//	}
	
}
