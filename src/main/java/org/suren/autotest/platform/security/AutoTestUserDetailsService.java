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

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.suren.autotest.platform.mapping.UserBehaviorMapper;
import org.suren.autotest.platform.mapping.UserInfoMapper;
import org.suren.autotest.platform.model.UserInfo;

/**
 * @author suren
 * @date 2017年1月29日 上午9:44:51
 */
@Service
public class AutoTestUserDetailsService implements UserDetailsService
{
	@Autowired
	private UserInfoMapper userInfoMapper;
	@Autowired
	private UserBehaviorMapper userBehaviorMapper;

	@Override
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException
	{
		UserInfo userInfo = userInfoMapper.getByName(username);
		if(userInfo == null)
		{
			throw new UsernameNotFoundException(username);
		}
		
		String password = userInfo.getPassword();
		boolean enabled = true;
		boolean accountNonExpired = true;
		boolean credentialsNonExpired = true;
		boolean accountNonLocked = true;
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		
		authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
		authorities.add(new SimpleGrantedAuthority("SCOPE_READ"));
		
		UserDetail userDetail = new UserDetail(username, password, enabled, accountNonExpired,
				credentialsNonExpired, accountNonLocked, authorities);
		userDetail.setId(userInfo.getId());
		
		return userDetail;
	}

}
