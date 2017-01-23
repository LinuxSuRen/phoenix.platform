/**
 * http://surenpi.com
 */
package org.suren.autotest.platform.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletContext;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.suren.autotest.platform.mapping.DataSourceInfoMapper;
import org.suren.autotest.platform.mapping.PageInfoMapper;
import org.suren.autotest.platform.mapping.ProjectMapper;
import org.suren.autotest.platform.mapping.SuiteRunnerInfoMapper;
import org.suren.autotest.platform.model.DataSourceInfo;
import org.suren.autotest.platform.model.PageInfo;
import org.suren.autotest.platform.model.Project;
import org.suren.autotest.platform.model.SuiteRunnerInfo;
import org.suren.autotest.web.framework.util.StringUtils;

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
	
	@Autowired
	private PageInfoMapper pageInfoMapper;
	@Autowired
	private DataSourceInfoMapper dataSourceInfoMapper;
	@Autowired
	private SuiteRunnerInfoMapper suiteRunnerInfoMapper;
	
	@Autowired
	private ServletContext servletContext;
	
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
			projectMapper.update(project);
		}
		
		return "project_edit";
	}
	
	@RequestMapping("/del")
	public String del(Model model, String id)
	{
		projectMapper.delById(id);
		
		return "redirect:/project/list.su";
	}
	
	@RequestMapping("/deploy")
	public String projectDeploy(String id)
	{
		String projectId = id;
		File rootDir = new File(servletContext.getRealPath("/deploy"));
		rootDir.mkdirs();

		//元素定位文件部署
		List<PageInfo> pageInfoList = pageInfoMapper.getAllWithContentByProjectId(projectId);
		if(CollectionUtils.isNotEmpty(pageInfoList))
		{
			for(PageInfo pageInfo : pageInfoList)
			{
				String content = pageInfo.getContent();
				File autotestFile = new File(rootDir, pageInfo.getName() + ".xml");
				
				try
				{
					FileUtils.writeStringToFile(autotestFile, content);
				}
				catch (IOException e)
				{
					e.printStackTrace();
				}
			}
		}
		
		//数据源文件部署
		List<DataSourceInfo> dataSourceInfoList = dataSourceInfoMapper.getAllWithContentByProjectId(projectId);
		if(CollectionUtils.isNotEmpty(dataSourceInfoList))
		{
			for(DataSourceInfo dataSourceInfo : dataSourceInfoList)
			{
				String content = dataSourceInfo.getContent();
				File dataSourceFile = new File(rootDir, dataSourceInfo.getName() + ".xml");
				
				try
				{
					FileUtils.writeStringToFile(dataSourceFile, content);
				}
				catch (IOException e)
				{
					e.printStackTrace();
				}
			}
		}
		
		//测试套件文件部署
		List<SuiteRunnerInfo> suiteRunnerInfoList = suiteRunnerInfoMapper.getAllWithContentByProjectId(projectId);
		if(CollectionUtils.isNotEmpty(suiteRunnerInfoList))
		{
			for(SuiteRunnerInfo suiteRunnerInfo : suiteRunnerInfoList)
			{
				String content = suiteRunnerInfo.getContent();
				File suiteFile = new File(rootDir, suiteRunnerInfo.getName() + ".xml");
				
				try
				{
					FileUtils.writeStringToFile(suiteFile, content);
				}
				catch (IOException e)
				{
					e.printStackTrace();
				}
			}
		}
		
		return "redirect:/project/list.su";
	}
}
