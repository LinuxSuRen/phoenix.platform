/**
 * http://surenpi.com
 */
package org.suren.autotest.platform.mapping;

import java.util.List;

import org.suren.autotest.platform.entity.DataSourceInfo;

/**
 * @author suren
 * @date 2017年1月17日 下午9:03:24
 */
public interface DataSourceInfoMapper
{
	List<DataSourceInfo> getAll();
	List<DataSourceInfo> getAllByProjectId(String projectId);
	List<DataSourceInfo> getAllByPageId(String pageId);
	List<DataSourceInfo> getAllWithContentByProjectId(String projectId);
	List<DataSourceInfo> getAllIds();
	
	DataSourceInfo getById(String id);
	int getCountByProjectId(String projectId);
	
	void save(DataSourceInfo dataSourceInfo);
	void update(DataSourceInfo dataSourceInfo);
	
	void delById(String id);
}
