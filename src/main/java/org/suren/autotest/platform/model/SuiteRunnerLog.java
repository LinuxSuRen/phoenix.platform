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
 * @author suren
 * @date 2017年1月31日 下午7:34:27
 */
public class SuiteRunnerLog
{
	private String id;
	private String suiteRunnerInfoId;
	private String triggerUserId;
	private String message;
	private Date beginTime;
	private Date endTime;
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
	 * @return the suiteRunnerInfoId
	 */
	public String getSuiteRunnerInfoId()
	{
		return suiteRunnerInfoId;
	}
	/**
	 * @param suiteRunnerInfoId the suiteRunnerInfoId to set
	 */
	public void setSuiteRunnerInfoId(String suiteRunnerInfoId)
	{
		this.suiteRunnerInfoId = suiteRunnerInfoId;
	}
	/**
	 * @return the triggerUserId
	 */
	public String getTriggerUserId()
	{
		return triggerUserId;
	}
	/**
	 * @param triggerUserId the triggerUserId to set
	 */
	public void setTriggerUserId(String triggerUserId)
	{
		this.triggerUserId = triggerUserId;
	}
	/**
	 * @return the message
	 */
	public String getMessage()
	{
		return message;
	}
	/**
	 * @param message the message to set
	 */
	public void setMessage(String message)
	{
		this.message = message;
	}
	/**
	 * @return the beginTime
	 */
	public Date getBeginTime()
	{
		return beginTime;
	}
	/**
	 * @param beginTime the beginTime to set
	 */
	public void setBeginTime(Date beginTime)
	{
		this.beginTime = beginTime;
	}
	/**
	 * @return the endTime
	 */
	public Date getEndTime()
	{
		return endTime;
	}
	/**
	 * @param endTime the endTime to set
	 */
	public void setEndTime(Date endTime)
	{
		this.endTime = endTime;
	}
}
