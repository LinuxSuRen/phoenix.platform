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

import org.suren.autotest.platform.schemas.autotest.PageFieldLocatorTypeEnum;

/**
 * @author suren
 * @date 2017年7月14日 上午9:26:21
 */
public class FieldLocator
{
	private String id;
	private String fieldId;
	private PageFieldLocatorTypeEnum name;
	private String value;
	private int locatorOrder;
	private Long timeout;
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
	 * @return the fieldId
	 */
	public String getFieldId()
	{
		return fieldId;
	}
	/**
	 * @param fieldId the fieldId to set
	 */
	public void setFieldId(String fieldId)
	{
		this.fieldId = fieldId;
	}
	/**
	 * @return the name
	 */
	public PageFieldLocatorTypeEnum getName()
	{
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(PageFieldLocatorTypeEnum name)
	{
		this.name = name;
	}
	/**
	 * @return the value
	 */
	public String getValue()
	{
		return value;
	}
	/**
	 * @param value the value to set
	 */
	public void setValue(String value)
	{
		this.value = value;
	}
	/**
	 * @return the locatorOrder
	 */
	public int getLocatorOrder()
	{
		return locatorOrder;
	}
	/**
	 * @param locatorOrder the locatorOrder to set
	 */
	public void setLocatorOrder(int locatorOrder)
	{
		this.locatorOrder = locatorOrder;
	}
	/**
	 * @return the timeout
	 */
	public Long getTimeout()
	{
		return timeout;
	}
	/**
	 * @param timeout the timeout to set
	 */
	public void setTimeout(Long timeout)
	{
		this.timeout = timeout;
	}
}
