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

package org.suren.autotest.platform.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author suren
 * @date 2017年1月29日 下午1:45:38
 */
@Controller
@RequestMapping("user_info")
public class UserInfoController
{
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@RequestMapping("login")
	public String login()
	{
		return "/user_info/login";
	}
	
	@RequestMapping("login_process")
	public String loginProcess(String username, String password,
			HttpServletRequest request)
	{
		Authentication authRequest = new UsernamePasswordAuthenticationToken(username, password);
	
		Authentication authentication = authenticationManager.authenticate(authRequest);
		SecurityContext context = SecurityContextHolder.getContext();
		context.setAuthentication(authentication);
		
		request.getSession().setAttribute("SPRING_SECURITY_CONTEXT", context);
		
		return "/project/list.su";
	}
	
	@RequestMapping("logout")
	public void logout()
	{
	}
}
