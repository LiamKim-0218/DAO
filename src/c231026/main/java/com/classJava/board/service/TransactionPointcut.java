package c231026.main.java.com.classJava.board.service;

import org.springframework.aop.ClassFilter;
import org.springframework.aop.support.NameMatchMethodPointcut;
import org.springframework.util.PatternMatchUtils;

public class TransactionPointcut extends NameMatchMethodPointcut {
	public void setMappedClassName(String mappedClassName) {
		this.setClassFilter(new SimpleClassFilter(mappedClassName));
	}
	
	public static class SimpleClassFilter implements ClassFilter{
		private String mappedClassName;
		public SimpleClassFilter(String mappedClassName) {
			this.mappedClassName = mappedClassName;
			
		}
		
		@Override
		public boolean matches(Class<?> targetClass) {
			return PatternMatchUtils.simpleMatch(
					mappedClassName, targetClass.getSimpleName());
		}
		
	}
}
