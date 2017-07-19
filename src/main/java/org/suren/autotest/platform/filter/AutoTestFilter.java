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

package org.suren.autotest.platform.filter;

import java.io.File;
import java.io.IOException;
import java.net.URL;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.suren.autotest.web.framework.util.PathUtil;

/**
 * @author suren
 * @date 2017年1月30日 下午7:28:11
 */
public class AutoTestFilter implements Filter
{

	@Override
	public void init(FilterConfig filterConfig) throws ServletException
	{
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException
	{
		File file = PathUtil.getFile("data_init");
		if(!file.isFile())
		{
			if(((HttpServletRequest) request).getSession().getAttribute("data_init") != null)
			{
				file.createNewFile();
			}
			else if(!((HttpServletRequest) request).getServletPath().contains("/data_base/")
					&& !((HttpServletRequest) request).getServletPath().endsWith(".js")
					&& !((HttpServletRequest) request).getServletPath().endsWith(".css"))
			{
				String context = ((HttpServletRequest) request).getContextPath();
				
				((HttpServletResponse) response).sendRedirect(context + "/data_base/init_schema");
				
				return;
			}
		}
		
		chain.doFilter(request, response);
	}

	@Override
	public void destroy()
	{
	}

}
