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
 * @date 2017年2月15日 下午8:48:01
 */
public class Options
{
	private String id;
	private String optKey;
	private String optValue;
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
	 * @return the optKey
	 */
	public String getOptKey()
	{
		return optKey;
	}
	/**
	 * @param optKey the optKey to set
	 */
	public void setOptKey(String optKey)
	{
		this.optKey = optKey;
	}
	/**
	 * @return the optValue
	 */
	public String getOptValue()
	{
		return optValue;
	}
	/**
	 * @param optValue the optValue to set
	 */
	public void setOptValue(String optValue)
	{
		this.optValue = optValue;
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
