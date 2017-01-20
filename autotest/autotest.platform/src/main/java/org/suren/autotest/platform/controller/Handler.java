/**
 * http://surenpi.com
 */
package org.suren.autotest.platform.controller;

import java.awt.List;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigInteger;
import java.util.Iterator;

import org.apache.commons.lang.StringUtils;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;
import org.suren.autotest.platform.schemas.Autotest;
import org.suren.autotest.platform.schemas.Autotest.Pages;
import org.suren.autotest.platform.schemas.EngineType;
import org.suren.autotest.platform.schemas.EngineTypeDriverEnum;
import org.suren.autotest.platform.schemas.ObjectFactory;
import org.suren.autotest.platform.schemas.PageFieldType;
import org.suren.autotest.platform.schemas.PageType;

/**
 * @author suren
 * @date 2017年1月19日 下午4:55:49
 */
public class Handler implements HandlerMethodArgumentResolver
{

	@Override
	public boolean supportsParameter(MethodParameter parameter)
	{
		if(parameter.getParameterType().equals(Autotest.class))
		{
			return true;
		}
		
		return false;
	}

	private NativeWebRequest webRequest;
	@Override
	public Object resolveArgument(MethodParameter parameter,
			ModelAndViewContainer mavContainer, NativeWebRequest webRequest,
			WebDataBinderFactory binderFactory) throws Exception
	{
		this.webRequest = webRequest;
		
		String paramName = parameter.getParameterName();
		Object obj = webRequest.getAttribute(paramName, RequestAttributes.SCOPE_REQUEST);
		if(obj == null)
		{
			ObjectFactory objFactory = new ObjectFactory();
			
			Autotest autotest = objFactory.createAutotest();
			
			fill(autotest, "");
			
			obj = autotest;
		}
		
		return obj;
	}
	
	public static void main(String[] args)
	{
		String str = "pages.page[10].content[0].name";
		
		int index = str.indexOf("[");
		
		String tmp = str.substring(index + 1);
		index = tmp.indexOf("]");
		tmp = tmp.substring(0, index);
		System.out.println(tmp);
	}
	
	private void fill(Object obj, String root)
	{
		if(obj == null)
		{
			return;
		}
		
		Class<?> atCls = obj.getClass();
		Field[] fields = atCls.getDeclaredFields();
		for(Field field : fields)
		{
			String fieldName = field.getName();
			Class<?> declCls = field.getType();
			
			try
			{
				Method readMethod = new PropertyDescriptor(
						fieldName, atCls).getReadMethod();
				Method writeMethod = new PropertyDescriptor(
						fieldName, atCls).getWriteMethod();
				
				String fieldRoot = (root + "." + fieldName);
				if(fieldRoot.startsWith("."))
				{
					fieldRoot = fieldRoot.substring(1);
				}
				
				if(declCls.equals(java.util.List.class))
				{
					Iterator<String> it = webRequest.getParameterNames();
					int i = 0;
					
					java.util.List list = (java.util.List) readMethod.invoke(obj);
					
					while(it.hasNext())
					{
						String name = it.next();
						if(name.startsWith(fieldRoot))
						{
							String value = webRequest.getParameter(name);
							System.out.println("name: " + name + "; value: " + value);
							
							if(name.startsWith("pages.page["))
							{
								if(fieldName.equals("content") && name.contains("].content["))
								{
									//pages.page[0].content[0].name
									int lastIndex = name.lastIndexOf(".");
									String fiName = name.substring(lastIndex + 1);
									int index = name.indexOf("[");
									name = name.substring(index + 1);
									index = name.indexOf("]");
									String aNum = name.substring(0, index);
									
									PageFieldType pageFieldType = new PageFieldType();
									
									Class<?> pftClz = pageFieldType.getClass();
									Method ptwriteMethod = new PropertyDescriptor(
											fiName, pftClz).getWriteMethod();
									ptwriteMethod.invoke(pageFieldType, value);
									try
									{
										list.getClass().getMethod("add", Object.class).invoke(list, pageFieldType);
									}
									catch (NoSuchMethodException
											| SecurityException e)
									{
										e.printStackTrace();
									}
								}
								else if(fieldName.equals("page") && !name.contains("content"))
								{
									PageType pageType = new PageType();
									
									int index = name.lastIndexOf(".");
									String tmpName = name.substring(index + 1);
									
									Class<?> ptClz = pageType.getClass();
									Method ptwriteMethod = new PropertyDescriptor(
											tmpName, ptClz).getWriteMethod();
									ptwriteMethod.invoke(pageType, value);
									try
									{
										list.getClass().getMethod("add", Object.class).invoke(list, pageType);
									}
									catch (NoSuchMethodException
											| SecurityException e)
									{
										e.printStackTrace();
									}
								}
							}
						}
					}
				}
				
				String value = webRequest.getParameter(fieldRoot);
				System.out.println("name: " + fieldRoot + "; value: " + value + "; --" + declCls);
				
				Object result = readMethod.invoke(obj);
				if(result == null)
				{
					Object instance = null;
					if(declCls.equals(BigInteger.class))
					{
						instance = (StringUtils.isEmpty(value)) ? BigInteger.ZERO : new BigInteger(value);
					}
					else if(declCls.equals(Boolean.class))
					{
						instance = (StringUtils.isEmpty(value)) ? Boolean.FALSE : new Boolean(value);
					}
					else if(declCls.equals(String.class))
					{
						instance = value;
					}
					else
					{
						instance = declCls.newInstance();
						
						fill(instance, fieldRoot);
					}
					
					writeMethod.invoke(obj, instance);
				}
				else
				{
					if(declCls.equals(EngineTypeDriverEnum.class) && !StringUtils.isEmpty(value))
					{
						result = EngineTypeDriverEnum.fromValue(value);
					}
					
					if(!declCls.equals(java.util.List.class))
					{
						writeMethod.invoke(obj, result);
					}
					else
					{
						System.out.println("d");
					}
				}
			}
			catch(java.beans.IntrospectionException e)
			{
				e.printStackTrace();
			}
			catch (IllegalAccessException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			catch (IllegalArgumentException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			catch (InvocationTargetException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			catch (InstantiationException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
