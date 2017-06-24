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

package org.suren.autotest.platform.controller.api;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.suren.autotest.platform.mapping.UserInfoMapper;
import org.suren.autotest.platform.model.UserInfo;

/**
 * 用户登录、注销、注册
 * @author suren
 * @date 2017年1月29日 下午1:45:38
 */
@Controller
@RequestMapping("user_info")
public class UserInfoController
{
	@Autowired
	private UserInfoMapper userInfoMapper;
	
	/**
	 * 跳转到登录页面
	 * @return
	 */
	@RequestMapping("login")
	public String login(HttpServletRequest request)
	{
		Object context = request.getSession().getAttribute("SPRING_SECURITY_CONTEXT");
		if(context != null)
		{
			return "redirect:/";
		}
		
		return "/user_info/login";
	}
	
	/**
	 * 跳转到注册页面
	 * @return
	 */
	@RequestMapping("regist")
	public String regist()
	{
		return "/user_info/regist";
	}
	
	/**
	 * 用户注册
	 * @param userInfo
	 * @return
	 */
	@RequestMapping("registProcess")
	public String registProcess(UserInfo userInfo,
			Model model)
	{
		UserInfo existsUserInfo = userInfoMapper.getByNameOrMail(
				userInfo.getLoginName(), userInfo.getEmail());
		if(existsUserInfo == null)
		{
			userInfoMapper.save(userInfo);
			
			return "redirect:/user_info/login.su";
		}
		else
		{
			model.addAttribute("userInfo", existsUserInfo);
			
			return "redirect:/user_info/regist.su";
		}
	}
	
	@RequestMapping("logout")
	public void logout()
	{
	}
}
