package c231026.main.java.com.classJava.board.service;

import java.lang.reflect.InvocationTargetException;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

public class TransactionAdvice implements MethodInterceptor{
	
	private PlatformTransactionManager transactionManager;
	
	public void setTransactionManager(PlatformTransactionManager transactionManager) {
		this.transactionManager = transactionManager;
	}
   @Override
   public Object invoke(MethodInvocation invocation) throws Throwable {
      // TODO Auto-generated method stub
      TransactionStatus status = transactionManager.getTransaction(new DefaultTransactionDefinition());
      
      try {
         Object ret = invocation.proceed();
         transactionManager.commit(status);
         return ret;
      }catch(InvocationTargetException e) {
         transactionManager.rollback(status);
         throw e.getTargetException();
      }
   }
}