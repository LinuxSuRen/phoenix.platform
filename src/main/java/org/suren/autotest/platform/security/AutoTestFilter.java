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

package org.suren.autotest.platform.security;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.springframework.security.access.SecurityMetadataSource;
import org.springframework.security.access.intercept.AbstractSecurityInterceptor;

/**
 * @author suren
 * @date 2017年1月29日 上午9:47:20
 */
public class AutoTestFilter extends AbstractSecurityInterceptor implements
		Filter
{

	@Override
	public void init(FilterConfig filterConfig) throws ServletException
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void destroy()
	{
		// TODO Auto-generated method stub

	}

	@Override
	public Class<?> getSecureObjectClass()
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SecurityMetadataSource obtainSecurityMetadataSource()
	{
		// TODO Auto-generated method stub
		return null;
	}

}
