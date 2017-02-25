/**
 * http://surenpi.com
 */
package org.suren.autotest.platform.util;

import java.io.ByteArrayInputStream;
import java.io.UnsupportedEncodingException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;

import org.suren.autotest.platform.schemas.datasource.DataSourcePageFieldType;
import org.suren.autotest.platform.schemas.datasource.DataSourcePageType;
import org.suren.autotest.platform.schemas.datasource.DataSources;
import org.suren.autotest.platform.schemas.datasource.DataSources.DataSource;
import org.suren.autotest.platform.schemas.datasource.DataTypeEnum;


/**
 * @author suren
 * @date 2017年1月19日 上午8:21:27
 */
public abstract class JAXBUtils
{
	public static void clearEncryptData(DataSources dataSources)
	{
		if(dataSources == null || dataSources.getDataSource() == null)
		{
			return;
		}
		
		for(DataSource dataSource : dataSources.getDataSource())
		{
			if(dataSource == null || dataSource.getPage() == null)
			{
				continue;
			}
			
			for(DataSourcePageType page : dataSource.getPage())
			{
				if(page == null || page.getField() == null)
				{
					continue;
				}
				
				for(DataSourcePageFieldType field : page.getField())
				{
					if(field.getType() == DataTypeEnum.ENCRYPT)
					{
						field.setData("");
					}
				}
			}
		}
	}
	
	public static <T> T getObj(T clazz, String content)
	{
		try
		{
			JAXBContext context = JAXBContext.newInstance(clazz.getClass());
			
			ByteArrayInputStream input = new ByteArrayInputStream(content.getBytes("utf-8"));
			context.createUnmarshaller().unmarshal(input);
		}
		catch (JAXBException e)
		{
			e.printStackTrace();
		}
		catch (UnsupportedEncodingException e)
		{
			e.printStackTrace();
		}
		
		return clazz;
	}
}
