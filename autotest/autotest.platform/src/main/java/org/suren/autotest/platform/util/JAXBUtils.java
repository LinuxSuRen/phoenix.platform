/**
 * http://surenpi.com
 */
package org.suren.autotest.platform.util;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBElement;

/**
 * @author suren
 * @date 2017年1月19日 上午8:21:27
 */
public class JAXBUtils
{
	@SuppressWarnings("unchecked")
	public static void transform(List<Serializable> content)
	{
		List<Serializable> tmpContent = new ArrayList<Serializable>();
		for(Serializable ser : content)
		{
			if(ser instanceof JAXBElement)
			{
				JAXBElement<?> jaxbEle = (JAXBElement<?>) ser;
				
				Serializable value = (Serializable) jaxbEle.getValue();
				
				try
				{
					Method getContentMethod = value.getClass().getMethod("getContent");
					
					Object result = getContentMethod.invoke(value);
					if(result instanceof List<?>)
					{
						transform((List<Serializable>) result);
					}
				}
				catch (NoSuchMethodException | SecurityException | IllegalAccessException
						| IllegalArgumentException | InvocationTargetException e)
				{
				}
				
				tmpContent.add(value);
			}
		}
		
		content.clear();
		content.addAll(tmpContent);
	}
}
