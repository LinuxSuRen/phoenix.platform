/**
 * http://surenpi.com
 */
package org.suren.autotest.platform.model;

import java.util.Date;

import org.suren.autotest.platform.schemas.autotest.Autotest;

/**
 * @author suren
 * @date 2017年1月18日 下午8:13:17
 */
public class PageInfo
{
	private String id;
	private String projectId;
	private String name;
	private String content;
	private Date createTime;
	private String remark;
	private Autotest autotest;
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
	 * @return the projectId
	 */
	public String getProjectId()
	{
		return projectId;
	}
	/**
	 * @param projectId the projectId to set
	 */
	public void setProjectId(String projectId)
	{
		this.projectId = projectId;
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
	 * @return the content
	 */
	public String getContent()
	{
		return content;
	}
	/**
	 * @param content the content to set
	 */
	public void setContent(String content)
	{
		this.content = content;
	}
	/**
	 * @return the createTime
	 */
	public Date getCreateTime()
	{
		return createTime;
	}
	/**
	 * @param createTime the createTime to set
	 */
	public void setCreateTime(Date createTime)
	{
		this.createTime = createTime;
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
	 * @return the autotest
	 */
	public Autotest getAutotest()
	{
		return autotest;
	}
	/**
	 * @param autotest the autotest to set
	 */
	public void setAutotest(Autotest autotest)
	{
		this.autotest = autotest;
	}
}
