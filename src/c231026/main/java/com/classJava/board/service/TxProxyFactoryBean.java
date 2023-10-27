package c231026.main.java.com.classJava.board.service;

import java.lang.reflect.Proxy;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

import c231024.main.java.com.classJava.board.service.BoardService;
import c231025.main.java.com.classJava.board.service.TransactionHandler;

public class TxProxyFactoryBean implements FactoryBean<Object>{
	private Object target;
	private String pattern;
	private PlatformTransactionManager transactionManager;
	private Class<?> serviceInterface;
	
	public void setTarget(Object target) {
		this.target = target;
	}
	public void setPattern(String pattern) {
		this.pattern = pattern;
	}
	public void setTransactionManager(PlatformTransactionManager transactionManager) {
		this.transactionManager = transactionManager;
	}
	public void setServiceInterface(Class<?> serviceInterface) {
		this.serviceInterface = serviceInterface;
	}
	
	@Override
	public Object getObject() throws Exception {
		// TODO Auto-generated method stub
		TransactionHandler txHandler = new TransactionHandler();
		txHandler.setTarget(target);
		txHandler.setPattern(pattern);
		txHandler.setTransactionManager(transactionManager);
		return Proxy.newProxyInstance(getClass().getClassLoader(),
			new Class[] {serviceInterface},
			txHandler);
	}

	@Override
	public Class<?> getObjectType() {
		// TODO Auto-generated method stub
		return serviceInterface;
	}

	@Override
	public boolean isSingleton() {
		// TODO Auto-generated method stub
		return false;
	}

}
