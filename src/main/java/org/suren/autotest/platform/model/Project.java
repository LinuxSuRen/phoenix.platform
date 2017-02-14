/**
 * http://surenpi.com
 */
package org.suren.autotest.platform.model;

import java.util.Date;
import java.util.List;

/**
 * @author suren
 * @date 2017年1月20日 下午7:46:46
 */
public class Project
{
	private String id;
	private String ownerId;
	private String name;
	private String pkgName;
	private String remark;
	private Date createTime;
	/** 以下是非DB相关属性 **/
	private List<Attachment> attachList;
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
	 * @return the pkgName
	 */
	public String getPkgName()
	{
		return pkgName;
	}
	/**
	 * @param pkgName the pkgName to set
	 */
	public void setPkgName(String pkgName)
	{
		this.pkgName = pkgName;
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
	 * @return the attachList
	 */
	public List<Attachment> getAttachList()
	{
		return attachList;
	}
	/**
	 * @param attachList the attachList to set
	 */
	public void setAttachList(List<Attachment> attachList)
	{
		this.attachList = attachList;
	}
}
