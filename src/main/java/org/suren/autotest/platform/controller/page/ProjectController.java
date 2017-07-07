/**
 * http://surenpi.com
 */
package org.suren.autotest.platform.controller.page;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 项目管理
 * @author suren
 * @date 2017年1月20日 下午7:43:05
 */
@Controller
@RequestMapping("/project")
public class ProjectController
{
	@RequestMapping("list")
	public String list()
	{
		return "project_list";
	}
	
	@RequestMapping("edit")
	public String edit()
	{
		return "project_edit";
	}
}
