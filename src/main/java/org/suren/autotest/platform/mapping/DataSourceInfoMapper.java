/**
 * http://surenpi.com
 */
package org.suren.autotest.platform.mapping;

import java.util.List;

import org.suren.autotest.platform.model.DataSourceInfo;

/**
 * @author suren
 * @date 2017年1月17日 下午9:03:24
 */
public interface DataSourceInfoMapper
{
	List<DataSourceInfo> getAll();
	List<DataSourceInfo> getAllByProjectId(String projectId);
	List<DataSourceInfo> getAllIds();
	
	void save(DataSourceInfo dataSourceInfo);
	
	void update(DataSourceInfo dataSourceInfo);
	
	DataSourceInfo getById(String id);
}
