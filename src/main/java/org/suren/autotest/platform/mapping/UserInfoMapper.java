/**
 * http://surenpi.com
 */
package org.suren.autotest.platform.mapping;

import java.util.List;

import org.suren.autotest.platform.model.UserInfo;

/**
 * @author suren
 * @date 2017年1月17日 下午9:03:24
 */
public interface UserInfoMapper
{
	List<UserInfo> getAll();
	
	UserInfo getByName(String loginName);
	
	/**
	 * 根据登录名或者邮件地址来查找
	 * @param loginName
	 * @param email
	 * @return
	 */
	UserInfo getByNameOrMail(String loginName, String email);

	/**
	 * @param userInfo
	 */
	void save(UserInfo userInfo);
}
