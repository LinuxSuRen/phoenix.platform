/**
 * http://surenpi.com
 */
package org.suren.autotest.platform.mapping;

import java.util.List;

import org.suren.autotest.platform.model.Project;

/**
 * @author suren
 * @date 2017年1月20日 下午7:56:07
 */
public interface ProjectMapper
{
	Project getById(String id);
	List<Project> getAll();
	void save(Project project);
	void delById(String id);
}
