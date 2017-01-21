/**
 * http://surenpi.com
 */
package org.suren.autotest.platform.controller;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.suren.autotest.platform.mapping.ProjectMapper;
import org.suren.autotest.platform.model.Project;

/**
 * @author suren
 * @date 2017年1月20日 下午7:43:05
 */
@Controller
@RequestMapping("/project")
public class ProjectController
{
	@Autowired
	private ProjectMapper projectMapper;
	
	@RequestMapping("/list")
	public String list(Model model)
	{
		List<Project> projects = projectMapper.getAll();
		
		model.addAttribute("projects", projects);
		
		return "project_list";
	}
	
	@RequestMapping("/edit")
	public String edit(Model model, String id)
	{
		Project proForModel = projectMapper.getById(id);
		model.addAttribute("project", proForModel);
		
		return "project_edit";
	}
	
	@RequestMapping("/save")
	public String save(Model model, Project project)
	{
		if(StringUtils.isBlank(project.getId()))
		{
			projectMapper.save(project);
		}
		else
		{
		}
		
		return "project_edit";
	}
	
	@RequestMapping("/del")
	public String del(Model model, String id)
	{
		projectMapper.delById(id);
		
		return "redirect:/project/list.su";
	}
}
