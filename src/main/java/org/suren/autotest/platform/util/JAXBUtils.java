/**
 * http://surenpi.com
 */
package org.suren.autotest.platform.util;

import java.io.ByteArrayInputStream;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;


/**
 * @author suren
 * @date 2017年1月19日 上午8:21:27
 */
public abstract class JAXBUtils
{
	public static <T> T getObj(T clazz, String content)
	{
		try
		{
			JAXBContext context = JAXBContext.newInstance(clazz.getClass());
			
			ByteArrayInputStream input = new ByteArrayInputStream(content.getBytes());
			context.createUnmarshaller().unmarshal(input);
		}
		catch (JAXBException e)
		{
			e.printStackTrace();
		}
		
		return clazz;
	}
}
