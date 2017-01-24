/*
 * Copyright 2002-2007 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.suren.autotest.platform;

import java.lang.reflect.Field;
import java.net.URLClassLoader;

import javax.servlet.ServletContext;

import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.WebApplicationContext;

/**
 * @author suren
 * @date 2017年1月24日 下午2:05:33
 */
public class AutoTestContextLoaderListener extends ContextLoaderListener
{

	@Override
	public WebApplicationContext initWebApplicationContext(
			ServletContext servletContext)
	{
		ClassLoader classLoader = this.getClass().getClassLoader().getParent();
		URLClassLoader urlClassLoader = (URLClassLoader) classLoader;
		
		AutoTestURLClassloader abc = new AutoTestURLClassloader(urlClassLoader.getURLs(), urlClassLoader);
		
		try
		{
			Field field = ClassLoader.class.getDeclaredField("parent");
			field.setAccessible(true);
			field.set(classLoader, abc);
		}
		catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException e)
		{
			e.printStackTrace();
		}
		
		return super.initWebApplicationContext(servletContext);
	}

}
