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
 * 测试计划
 * @author suren
 * @date 2017年3月1日 下午6:09:41
 */
public class TestPlan
{
	private String id;
	private String name;
	private String suiteRunnerId;
	private String cronExp;
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
	 * @return the name
	 */
	public String getName()
	{
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name)
	{
		this.name = name;
	}
	/**
	 * @return the suiteRunnerId
	 */
	public String getSuiteRunnerId()
	{
		return suiteRunnerId;
	}
	/**
	 * @param suiteRunnerId the suiteRunnerId to set
	 */
	public void setSuiteRunnerId(String suiteRunnerId)
	{
		this.suiteRunnerId = suiteRunnerId;
	}
	/**
	 * @return the cronExp
	 */
	public String getCronExp()
	{
		return cronExp;
	}
	/**
	 * @param cronExp the cronExp to set
	 */
	public void setCronExp(String cronExp)
	{
		this.cronExp = cronExp;
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
