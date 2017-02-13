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
 * 附件表
 * @author suren
 * @date 2017年2月13日 下午8:26:04
 */
public class Attachment
{
	private String id;
	private String ownerId;
	private String belongId;
	private String configId;
	private String fileName;
	private String relativePath;
	private String remark;
	private String createTime;
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
	 * @return the ownerId
	 */
	public String getOwnerId()
	{
		return ownerId;
	}
	/**
	 * @param ownerId the ownerId to set
	 */
	public void setOwnerId(String ownerId)
	{
		this.ownerId = ownerId;
	}
	/**
	 * @return the belongId
	 */
	public String getBelongId()
	{
		return belongId;
	}
	/**
	 * @param belongId the belongId to set
	 */
	public void setBelongId(String belongId)
	{
		this.belongId = belongId;
	}
	/**
	 * @return the configId
	 */
	public String getConfigId()
	{
		return configId;
	}
	/**
	 * @param configId the configId to set
	 */
	public void setConfigId(String configId)
	{
		this.configId = configId;
	}
	/**
	 * @return the fileName
	 */
	public String getFileName()
	{
		return fileName;
	}
	/**
	 * @param fileName the fileName to set
	 */
	public void setFileName(String fileName)
	{
		this.fileName = fileName;
	}
	/**
	 * @return the relativePath
	 */
	public String getRelativePath()
	{
		return relativePath;
	}
	/**
	 * @param relativePath the relativePath to set
	 */
	public void setRelativePath(String relativePath)
	{
		this.relativePath = relativePath;
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
	/**
	 * @return the createTime
	 */
	public String getCreateTime()
	{
		return createTime;
	}
	/**
	 * @param createTime the createTime to set
	 */
	public void setCreateTime(String createTime)
	{
		this.createTime = createTime;
	}
}
