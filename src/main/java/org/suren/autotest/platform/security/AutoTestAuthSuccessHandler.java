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
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.suren.autotest.platform.mapping.UserBehaviorMapper;
import org.suren.autotest.platform.model.UserBehavior;

/**
 * 当认证成功后，记录用户的登陆信息
 * @author suren
 * @date 2017年1月29日 下午2:54:13
 */
public class AutoTestAuthSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler
{
	@Autowired
	private UserBehaviorMapper userBehaviorMapper;

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request,
			HttpServletResponse response, Authentication authentication)
			throws ServletException, IOException
	{
		super.onAuthenticationSuccess(request, response, authentication);
		UserDetail details = (UserDetail) authentication.getPrincipal();
		
		UserBehavior userBehavior = new UserBehavior();
		userBehavior.setUserId(details.getId());
		userBehavior.setMethod(request.getMethod());
		userBehavior.setRequestUri(request.getRequestURI());
		userBehavior.setQueryInfo(request.getQueryString());
		userBehavior.setServerIP(request.getServerName());
		userBehavior.setClientIP(request.getRemoteAddr());
		userBehavior.setVisitTime(new Date());
		
		userBehavior.setUserAgent(request.getHeader("User-Agent"));
		
		userBehaviorMapper.save(userBehavior);
	}

}
