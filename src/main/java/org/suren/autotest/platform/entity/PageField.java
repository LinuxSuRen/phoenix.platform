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

package org.suren.autotest.platform.entity;

import java.math.BigInteger;

import org.suren.autotest.platform.schemas.autotest.FieldTypeEnum;
import org.suren.autotest.platform.schemas.autotest.StrategyEnum;

/**
 * @author suren
 * @date 2017年7月13日 上午10:20:02
 */
public class PageField
{
	private String id;
	private String pageId;
	private String name;
    protected FieldTypeEnum type;
    protected StrategyEnum strategy;
    protected BigInteger timeout;
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
	 * @return the type
	 */
	public FieldTypeEnum getType()
	{
		return type;
	}
	/**
	 * @param type the type to set
	 */
	public void setType(FieldTypeEnum type)
	{
		this.type = type;
	}
	/**
	 * @return the strategy
	 */
	public StrategyEnum getStrategy()
	{
		return strategy;
	}
	/**
	 * @param strategy the strategy to set
	 */
	public void setStrategy(StrategyEnum strategy)
	{
		this.strategy = strategy;
	}
	/**
	 * @return the timeout
	 */
	public BigInteger getTimeout()
	{
		return timeout;
	}
	/**
	 * @param timeout the timeout to set
	 */
	public void setTimeout(BigInteger timeout)
	{
		this.timeout = timeout;
	}
	/**
	 * @return the pageId
	 */
	public String getPageId()
	{
		return pageId;
	}
	/**
	 * @param pageId the pageId to set
	 */
	public void setPageId(String pageId)
	{
		this.pageId = pageId;
	}
}
