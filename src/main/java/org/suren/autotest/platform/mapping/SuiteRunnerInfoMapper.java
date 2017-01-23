/**
 * http://surenpi.com
 */
package org.suren.autotest.platform.mapping;

import java.util.List;

import org.suren.autotest.platform.model.SuiteRunnerInfo;

/**
 * @author suren
 * @date 2017年1月21日 上午11:09:36
 */
public interface SuiteRunnerInfoMapper
{
	List<SuiteRunnerInfo> getAll();
	List<SuiteRunnerInfo> getAllByProjectId(String projectId);
	List<SuiteRunnerInfo> getAllWithContentByProjectId(String projectId);
	
	SuiteRunnerInfo getById(String id);
	
	void save(SuiteRunnerInfo suiteRunnerInfo);
	void update(SuiteRunnerInfo suiteRunnerInfo);
	
	void delById(String id);
}
