/**
 * http://surenpi.com
 */
package autotest.platform;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

/**
 * @author suren
 * @date 2017年1月19日 下午11:04:57
 */
public class RefTest
{
	@Test
	public void test() throws Exception
	{
		Object hostObj = Hao.class.newInstance();
		for(Field field : Hao.class.getDeclaredFields())
		{
//			Object instance = field.getType().newInstance();
			
//			field.set(hostObj, instance);
			System.out.println(field.getType().getGenericInterfaces()[0]);
			
			Class clz = field.get(hostObj).getClass();
			Method method = clz.getMethod("add", Object.class);
			System.out.println(method);
			
			method.invoke(field.get(hostObj), new Object());
			
			method = clz.getMethod("get", int.class);
			System.out.println(method.invoke(field.get(hostObj), 0).getClass());;
		}
		System.out.println(hostObj);
	}
}

class Hao
{
	public List<String> hao = new ArrayList<String>();
}
