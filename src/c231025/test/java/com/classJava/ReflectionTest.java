package c231025.test.java.com.classJava;

import java.lang.reflect.Method;

import org.junit.Test;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class ReflectionTest {
	@Test
	public void test() throws Exception {
		String name = "박정완";
		assertThat(name.length(), is(3));
		Method method = String.class.getMethod("length");
		assertThat(method.invoke(name), is(3));
		
		assertThat(name.indexOf("정"), is(1));
		method = String.class.getMethod("indexOf",String.class);
		assertThat(method.invoke(name, "정"), is(1));
	
	}
}
