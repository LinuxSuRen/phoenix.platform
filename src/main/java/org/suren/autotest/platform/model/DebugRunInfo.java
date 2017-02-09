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

/**
 * @author suren
 * @date 2017年2月9日 下午10:39:06
 */
public class DebugRunInfo
{
	private String id;
	private int normalTimes;
	private int retryTimes;
	private int concurrentTimes;
	private String remark;
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
	 * @return the normalTimes
	 */
	public int getNormalTimes()
	{
		return normalTimes;
	}
	/**
	 * @param normalTimes the normalTimes to set
	 */
	public void setNormalTimes(int normalTimes)
	{
		this.normalTimes = normalTimes;
	}
	/**
	 * @return the retryTimes
	 */
	public int getRetryTimes()
	{
		return retryTimes;
	}
	/**
	 * @param retryTimes the retryTimes to set
	 */
	public void setRetryTimes(int retryTimes)
	{
		this.retryTimes = retryTimes;
	}
	public int getConcurrentTimes()
	{
		return concurrentTimes;
	}
	public void setConcurrentTimes(int concurrentTimes)
	{
		this.concurrentTimes = concurrentTimes;
	}
	/**
	 * @return the remark
	 */
	public String getRemark()
	{
		return remark;
	}
	/**
	 * @param remark the remark to set
	 */
	public void setRemark(String remark)
	{
		this.remark = remark;
	}
}
