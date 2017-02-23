/**
 * http://surenpi.com
 */
package org.suren.autotest.platform.model;

import java.util.Date;

import org.suren.autotest.platform.schemas.suite.Suite;

/**
 * @author suren
 * @date 2017年1月21日 上午11:02:46
 */
public class SuiteRunnerInfo
{
	private String id;
	private String projectId;
	private String name;
	private String content;
	private Date createTime;
	private String remark;
	private Suite suite;
	private int tabIndex;
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
	 * @return the suite
	 */
	public Suite getSuite()
	{
		return suite;
	}
	/**
	 * @param suite the suite to set
	 */
	public void setSuite(Suite suite)
	{
		this.suite = suite;
	}
	/**
	 * @return the tabIndex
	 */
	public int getTabIndex()
	{
		return tabIndex;
	}
	/**
	 * @param tabIndex the tabIndex to set
	 */
	public void setTabIndex(int tabIndex)
	{
		this.tabIndex = tabIndex;
	}
}
