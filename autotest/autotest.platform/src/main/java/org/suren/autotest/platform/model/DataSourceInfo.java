/**
 * http://surenpi.com
 */
package org.suren.autotest.platform.model;

import org.suren.autotest.platform.schemas.datasource.DataSources;


/**
 * @author suren
 * @date 2017年1月21日 上午11:01:50
 */
public class DataSourceInfo
{
	private String id;
	private String projectId;
	private String name;
	private String content;
	private DataSources dataSources;
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
	 * @return the dataSources
	 */
	public DataSources getDataSources()
	{
		return dataSources;
	}
	/**
	 * @param dataSources the dataSources to set
	 */
	public void setDataSources(DataSources dataSources)
	{
		this.dataSources = dataSources;
	}
}
