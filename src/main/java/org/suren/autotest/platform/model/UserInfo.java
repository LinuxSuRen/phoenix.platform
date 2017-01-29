/**
 * http://surenpi.com
 */
package org.suren.autotest.platform.model;

import java.util.Date;

/**
 * @author suren
 * @date 2017年1月17日 下午9:02:35
 */
public class UserInfo
{
	private String id;
	private String nickName;
	private String loginName;
	private String password;
	private String email;
	private Date registTime;
	/**
	 * @return the id
	 */
	public String getId()
	{
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(String id)
	{
		this.id = id;
	}
	/**
	 * @return the nickName
	 */
	public String getNickName()
	{
		return nickName;
	}
	/**
	 * @param nickName the nickName to set
	 */
	public void setNickName(String nickName)
	{
		this.nickName = nickName;
	}
	/**
	 * @return the loginName
	 */
	public String getLoginName()
	{
		return loginName;
	}
	/**
	 * @param loginName the loginName to set
	 */
	public void setLoginName(String loginName)
	{
		this.loginName = loginName;
	}
	/**
	 * @return the password
	 */
	public String getPassword()
	{
		return password;
	}
	/**
	 * @param password the password to set
	 */
	public void setPassword(String password)
	{
		this.password = password;
	}
	/**
	 * @return the email
	 */
	public String getEmail()
	{
		return email;
	}
	/**
	 * @param email the email to set
	 */
	public void setEmail(String email)
	{
		this.email = email;
	}
	/**
	 * @return the registTime
	 */
	public Date getRegistTime()
	{
		return registTime;
	}
	/**
	 * @param registTime the registTime to set
	 */
	public void setRegistTime(Date registTime)
	{
		this.registTime = registTime;
	}
}
