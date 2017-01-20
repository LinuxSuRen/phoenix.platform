/**
 * http://surenpi.com
 */
package org.suren.autotest.platform.model;

import java.util.List;
import java.util.Map;

/**
 * @author suren
 * @date 2017年1月18日 下午8:13:17
 */
public class PageInfo
{
	private String id;
	private String name;
	private String content;
	private List<Map<String, String>> field;
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
	public List<Map<String, String>> getField()
	{
		return field;
	}
	public void setField(List<Map<String, String>> field)
	{
		this.field = field;
	}
}
