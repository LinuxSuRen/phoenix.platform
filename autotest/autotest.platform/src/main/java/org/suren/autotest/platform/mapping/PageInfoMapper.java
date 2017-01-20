/**
 * http://surenpi.com
 */
package org.suren.autotest.platform.mapping;

import java.util.List;

import org.suren.autotest.platform.mode.PageInfo;

/**
 * @author suren
 * @date 2017年1月18日 下午8:12:51
 */
public interface PageInfoMapper
{
	void insert(PageInfo pageInfo);
	
	int getTotalNum();
	
	PageInfo getById(String id);
	
	List<PageInfo> getAllIds();
	
	void update(PageInfo pageInfo);
}
