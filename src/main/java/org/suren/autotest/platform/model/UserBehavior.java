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

package org.suren.autotest.platform.model;

import java.util.Date;

/**
 * 用户行为
 * @author suren
 * @date 2017年2月11日 下午8:37:48
 */
public class UserBehavior
{
	private String id;
	private String userId;
	private String method;
	private String userAgent;
	private String serverIP;
	private String clientIP;
	private String operatingSystem;
	private String requestUri;
	private String queryInfo;
	private Date visitTime;
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
	 * @return the userId
	 */
	public String getUserId()
	{
		return userId;
	}
	/**
	 * @param userId the userId to set
	 */
	public void setUserId(String userId)
	{
		this.userId = userId;
	}
	/**
	 * @return the method
	 */
	public String getMethod()
	{
		return method;
	}
	/**
	 * @param method the method to set
	 */
	public void setMethod(String method)
	{
		this.method = method;
	}
	/**
	 * @return the userAgent
	 */
	public String getUserAgent()
	{
		return userAgent;
	}
	/**
	 * @param userAgent the userAgent to set
	 */
	public void setUserAgent(String userAgent)
	{
		this.userAgent = userAgent;
	}
	/**
	 * @return the serverIP
	 */
	public String getServerIP()
	{
		return serverIP;
	}
	/**
	 * @param serverIP the serverIP to set
	 */
	public void setServerIP(String serverIP)
	{
		this.serverIP = serverIP;
	}
	/**
	 * @return the clientIP
	 */
	public String getClientIP()
	{
		return clientIP;
	}
	/**
	 * @param clientIP the clientIP to set
	 */
	public void setClientIP(String clientIP)
	{
		this.clientIP = clientIP;
	}
	/**
	 * @return the operatingSystem
	 */
	public String getOperatingSystem()
	{
		return operatingSystem;
	}
	/**
	 * @param operatingSystem the operatingSystem to set
	 */
	public void setOperatingSystem(String operatingSystem)
	{
		this.operatingSystem = operatingSystem;
	}
	/**
	 * @return the requestUri
	 */
	public String getRequestUri()
	{
		return requestUri;
	}
	/**
	 * @param requestUri the requestUri to set
	 */
	public void setRequestUri(String requestUri)
	{
		this.requestUri = requestUri;
	}
	public String getQueryInfo()
	{
		return queryInfo;
	}
	public void setQueryInfo(String queryInfo)
	{
		this.queryInfo = queryInfo;
	}
	/**
	 * @return the visitTime
	 */
	public Date getVisitTime()
	{
		return visitTime;
	}
	/**
	 * @param visitTime the visitTime to set
	 */
	public void setVisitTime(Date visitTime)
	{
		this.visitTime = visitTime;
	}
}
