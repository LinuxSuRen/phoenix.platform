/**
 * http://surenpi.com
 */
package org.suren.autotest.platform.controller;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.ServletContext;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.suren.autotest.platform.AutoTestClassloader;
import org.suren.autotest.platform.mapping.DataSourceInfoMapper;
import org.suren.autotest.platform.mapping.PageInfoMapper;
import org.suren.autotest.platform.mapping.ProjectMapper;
import org.suren.autotest.platform.mapping.SuiteRunnerInfoMapper;
import org.suren.autotest.platform.model.DataSourceInfo;
import org.suren.autotest.platform.model.PageInfo;
import org.suren.autotest.platform.model.Project;
import org.suren.autotest.platform.model.SuiteRunnerInfo;
import org.suren.autotest.web.framework.code.Generator;
import org.suren.autotest.web.framework.jdt.JDTUtils;
import org.suren.autotest.web.framework.util.StringUtils;

import ch.qos.logback.core.Context;

/**
 * @author suren
 * @date 2017年1月20日 下午7:43:05
 */
@Controller
@RequestMapping("/project")
public class ProjectController implements ApplicationContextAware
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
	
	@Resource(name = "xml_to_java")
	private Generator codeGenerator;
	
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
		File srcOutputDir = new File(rootDir, "src");
		JDTUtils jdtUtils = new JDTUtils(srcOutputDir);

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
					
					//生成Java源码
					codeGenerator.generate(autotestFile.toString(), srcOutputDir.toString());
				}
				catch (IOException e)
				{
					e.printStackTrace();
				}
			}
			
			//编译Java源码
			jdtUtils.compileAllFile();
			try
			{
				AutoTestClassloader cla = new AutoTestClassloader(new URL[]{srcOutputDir.toURI().toURL()},
						Thread.currentThread().getContextClassLoader());
				
				Thread.currentThread().setContextClassLoader(cla);
				
				File tmpRoot = new File("D:/Program Files (x86)/Gboat-Toolkit-Suit/workspace_surenpi/.metadata/.plugins/org.eclipse.wst.server.core/tmp0/wtpwebapps/autotest.platform/deploy/src/com/glodon/fujian/page");
				for(File a : tmpRoot.listFiles())
				{
					String name = a.getName();
					if(name.endsWith(".java"))
					{
						continue;
					}
					
					Class<?> target = cla.loadClass("com.glodon.fujian.page." + name.replace(".class", ""));
					
					BeanDefinitionRegistry reg = (BeanDefinitionRegistry) ((ConfigurableApplicationContext)applicationContext.getParent()).getBeanFactory();
					
					BeanDefinitionBuilder beanDef = BeanDefinitionBuilder.genericBeanDefinition(target);
					
					AbstractBeanDefinition bean = beanDef.getBeanDefinition();
					reg.registerBeanDefinition(target.getName(), bean);
					
					Object targetObj = applicationContext.getBean(target.getName());
					for(Field field : target.getDeclaredFields())
					{
						try
						{
//							Method method = new PropertyDescriptor(field.getName(), field.getType()).getWriteMethod();
							
							Object fieldObj = applicationContext.getBean(field.getType());
							
//							method.invoke(targetObj, fieldObj);
							field.setAccessible(true);
							field.set(targetObj, fieldObj);;
						}
						catch (IllegalAccessException | IllegalArgumentException e)
						{
							e.printStackTrace();
						}
						catch(NoSuchBeanDefinitionException e)
						{
						}
						
						if(field.getAnnotation(Autowired.class) != null)
						{
						}
					}
				}
			}
			catch (MalformedURLException | ClassNotFoundException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
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

	ApplicationContext applicationContext;
	
	@Override
	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException
	{
		this.applicationContext = applicationContext;
	}
}
