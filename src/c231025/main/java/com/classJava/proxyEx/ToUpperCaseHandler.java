package c231025.main.java.com.classJava.proxyEx;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class ToUpperCaseHandler implements InvocationHandler{
	SimpleString target;
	
	public ToUpperCaseHandler(SimpleString target) {
		this.target = target;
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		// TODO Auto-generated method stub
		String ret = (String) method.invoke(target, args);
//		if(ret.equals("Hello 한상윤"))
//			return ret.toUpperCase();
		if(ret instanceof String && method.getName().equals("hello"))
			return ret.toUpperCase();
		else
			return ret;
	}
	
}
