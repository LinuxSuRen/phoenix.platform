/**
 * http://surenpi.com
 */
package org.suren.autotest.platform.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.validation.Valid;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import io.swagger.annotations.ApiParam;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.io.FileUtils;
import org.apache.poi.util.IOUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.suren.autotest.platform.AutoTestClassloader;
import org.suren.autotest.platform.mapping.AttachmentMapper;
import org.suren.autotest.platform.mapping.DataSourceInfoMapper;
import org.suren.autotest.platform.mapping.PageInfoMapper;
import org.suren.autotest.platform.mapping.ProjectMapper;
import org.suren.autotest.platform.mapping.SuiteRunnerInfoMapper;
import org.suren.autotest.platform.model.Attachment;
import org.suren.autotest.platform.model.DataSourceInfo;
import org.suren.autotest.platform.model.PageInfo;
import org.suren.autotest.platform.model.Project;
import org.suren.autotest.platform.model.SuiteRunnerInfo;
import org.suren.autotest.platform.schemas.autotest.Autotest;
import org.suren.autotest.platform.schemas.datasource.DataSources;
import org.suren.autotest.platform.schemas.suite.Suite;
import org.suren.autotest.platform.security.UserDetail;
import org.suren.autotest.platform.util.JAXBUtils;
import org.suren.autotest.web.framework.code.Generator;
import org.suren.autotest.web.framework.jdt.JDTUtils;
import org.suren.autotest.web.framework.util.StringUtils;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * 项目管理
 * @author suren
 * @date 2017年1月20日 下午7:43:05
 */
@Controller
@RequestMapping("/project")
@Api("项目管理")
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
	private AttachmentMapper attachmentMapper;
	
	@Autowired
	private ServletContext servletContext;
	
	@Resource(name = "xml_to_java")
	private Generator codeGenerator;
	
	@ApiOperation("项目列表")
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(@ApiParam(name = "model") Model model)
	{
		List<Project> projects = projectMapper.getAll();
		
		model.addAttribute("projects", projects);
		
		return "project_list";
	}
	
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public String edit(Model model, String id)
	{
		Project proForModel = projectMapper.getById(id);
		if(proForModel == null)
		{
			UserDetail userDetail = (UserDetail) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			String ownerId = userDetail.getId();
			
			proForModel = new Project();
			proForModel.setOwnerId(ownerId);
		}
		else
		{
			List<Attachment> attachList = attachmentMapper.getByBelongId(id);
			proForModel.setAttachList(attachList);
		}
		
		model.addAttribute("project", proForModel);
		
		return "project_edit";
	}
	
	@RequestMapping("/save")
	public String save(Model model, @Valid Project project)
	{
		if(StringUtils.isBlank(project.getId()))
		{
			project.setCreateTime(new Date());
			projectMapper.save(project);
		}
		else
		{
			projectMapper.update(project);
		}
		
		return "redirect:/project/edit.su?id=" + project.getId();
	}

	@ApiOperation("项目删除")
	@RequestMapping(value = "/del", method = RequestMethod.DELETE)
	public String del(@RequestParam String id)
	{
		projectMapper.delById(id);
		
		return "redirect:/project/list.su";
	}

	@ApiOperation("项目部署")
	@RequestMapping(value = "/deploy", method = RequestMethod.GET)
	public String projectDeploy(@RequestParam String id)
	{
		UserDetail userDetail = (UserDetail) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String ownerId = userDetail.getId();
		
		String projectId = id;
		File rootDir = new File(servletContext.getRealPath("/deploy"), id + "/" + ownerId);
		rootDir.mkdirs();
		File srcOutputDir = new File(rootDir, "src");
		JDTUtils jdtUtils = new JDTUtils(srcOutputDir);
		
		//附件拷贝
		List<Attachment> attachList = attachmentMapper.getByBelongId(projectId);
		if(CollectionUtils.isNotEmpty(attachList))
		{
			for(Attachment attach : attachList)
			{
				String name = attach.getFileName();
				if(name.endsWith(".java"))
				{
					String pkgPath = attach.getRemark();
					pkgPath = StringUtils.isBlank(pkgPath) ? "" : pkgPath.trim();
					pkgPath = pkgPath.replace(".", "/");
					
					try
					{
						FileUtils.copyFile(new File(attach.getRelativePath(), name),
								new File(srcOutputDir, pkgPath + "/" + name));
					}
					catch (IOException e)
					{
						e.printStackTrace();
					}
				}
			}
		}

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
					FileUtils.writeStringToFile(autotestFile, content, "UTF-8");
					
					//生成Java源码
					codeGenerator.generate(autotestFile.toString(), srcOutputDir.toString());
				}
				catch (IOException e)
				{
					e.printStackTrace();
				}
			}
			
			//编译Java源码
			List<File> result = jdtUtils.compileAllFile();
			try
			{
				AutoTestClassloader cla = new AutoTestClassloader(new URL[]{srcOutputDir.toURI().toURL()},
						Thread.currentThread().getContextClassLoader());
				
				Thread.currentThread().setContextClassLoader(cla);
				
				String rootPath = srcOutputDir.getAbsolutePath();
				for(File javaSrcFile : result)
				{
					String absPath = javaSrcFile.getAbsolutePath();
					String clsName = absPath.replace(rootPath, "").replace("/", "\\").replace("\\", ".").replace(".java", "");
					clsName = clsName.substring(1);
					
					Class<?> target = cla.loadClass(clsName);
					
					BeanDefinitionRegistry reg = (BeanDefinitionRegistry) ((ConfigurableApplicationContext)applicationContext.getParent()).getBeanFactory();
					
					BeanDefinitionBuilder beanDef = BeanDefinitionBuilder.genericBeanDefinition(target);
					
					AbstractBeanDefinition bean = beanDef.getBeanDefinition();
					reg.registerBeanDefinition(target.getName(), bean);
					
					Object targetObj = applicationContext.getBean(target.getName());
					for(Field field : target.getDeclaredFields())
					{
						try
						{
							Object fieldObj = applicationContext.getBean(field.getType());
							
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
					FileUtils.writeStringToFile(dataSourceFile, content, "utf-8");
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
					FileUtils.writeStringToFile(suiteFile, content, "utf-8");
				}
				catch (IOException e)
				{
					e.printStackTrace();
				}
			}
		}
		
		return "redirect:/project/list.su";
	}
	
	@RequestMapping("import.su")
	public String projectImport(Model model, MultipartFile file, final String id) throws IOException
	{
		final File tmpFile = File.createTempFile("autotest", "platform");
		
		try(InputStream input = file.getInputStream();
				OutputStream out = new FileOutputStream(tmpFile))
		{
			IOUtils.copy(input, out);
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		
		new Thread(){

			@Override
			public void run()
			{
				try(ZipInputStream zipIn = new ZipInputStream(new FileInputStream(tmpFile)))
				{
					ZipEntry entry = null;
					while((entry  = zipIn.getNextEntry()) != null)
					{
						String entryName = entry.getName();
						if(entry.isDirectory() || !entryName.endsWith(".xml"))
						{
							continue;
						}
						
						entryName = entryName.substring(0, entryName.length() - 4);
						
						ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
						IOUtils.copy(zipIn, byteOut);
						
						SAXReader reader = new SAXReader();
						Document doc = reader.read(new ByteArrayInputStream(byteOut.toByteArray()));
						Element rootEle = doc.getRootElement();
						
						String rootEleName = rootEle.getName();
						if("autotest".equals(rootEleName))
						{
							PageInfo pageInfo = new PageInfo();
							pageInfo.setProjectId(id);
							pageInfo.setName(entryName);
							pageInfo.setCreateTime(new Date());
							pageInfo.setContent(doc.asXML());
							
							try
							{
								JAXBContext context = JAXBContext.newInstance(Autotest.class);
								Unmarshaller unmarshaller = context.createUnmarshaller();
								
								Autotest autotest = (Autotest) unmarshaller.unmarshal(
										new ByteArrayInputStream(doc.asXML().getBytes()));
								JAXBUtils.autotestTransfer(autotest);
								
								ByteArrayOutputStream autoTestByteOut = new ByteArrayOutputStream();
								context.createMarshaller().marshal(autotest, autoTestByteOut);
								
								pageInfo.setContent(autoTestByteOut.toString("UTF-8"));
							}
							catch (JAXBException e)
							{
								e.printStackTrace();
							}
							
							pageInfoMapper.save(pageInfo);
						}
						else if("dataSources".equals(rootEleName))
						{
							DataSourceInfo dataSourceInfo = new DataSourceInfo();
							dataSourceInfo.setProjectId(id);
							dataSourceInfo.setName(entryName);
							dataSourceInfo.setCreateTime(new Date());
							dataSourceInfo.setContent(doc.asXML());
							
							try
							{
								JAXBContext context = JAXBContext.newInstance(DataSources.class);
								Unmarshaller unmarshaller = context.createUnmarshaller();
								
								DataSources dataSources = (DataSources) unmarshaller.unmarshal(
										new ByteArrayInputStream(doc.asXML().getBytes()));
								JAXBUtils.dataSourcesTransfer(dataSources);
								
								ByteArrayOutputStream dataSourceByteOut = new ByteArrayOutputStream();
								context.createMarshaller().marshal(dataSources, dataSourceByteOut);
								
								dataSourceInfo.setContent(dataSourceByteOut.toString("UTF-8"));
							}
							catch (JAXBException e)
							{
								e.printStackTrace();
							}
							
							dataSourceInfoMapper.save(dataSourceInfo);
						}
						else if("suite".equals(rootEleName))
						{
							SuiteRunnerInfo suiteRunnerInfo = new SuiteRunnerInfo();
							suiteRunnerInfo.setProjectId(id);
							suiteRunnerInfo.setName(entryName);
							suiteRunnerInfo.setCreateTime(new Date());
							suiteRunnerInfo.setContent(doc.asXML());
							
							try
							{
								JAXBContext context = JAXBContext.newInstance(Suite.class);
								Unmarshaller unmarshaller = context.createUnmarshaller();
								
								Suite suite = (Suite) unmarshaller.unmarshal(
										new ByteArrayInputStream(doc.asXML().getBytes()));
								JAXBUtils.suiteTransfer(suite);
								
								ByteArrayOutputStream suiteByteOut = new ByteArrayOutputStream();
								context.createMarshaller().marshal(suite, suiteByteOut);
								
								suiteRunnerInfo.setContent(suiteByteOut.toString("UTF-8"));
							}
							catch (JAXBException e)
							{
								e.printStackTrace();
							}
							
							suiteRunnerInfoMapper.save(suiteRunnerInfo);
						}
						else
						{
							System.err.println("Unknow type xml, root element name is : " + rootEleName);
						}
					}
				}
				catch (FileNotFoundException e)
				{
					e.printStackTrace();
				}
				catch (IOException e)
				{
					e.printStackTrace();
				}
				catch (DocumentException e)
				{
					e.printStackTrace();
				}
			}
		}.start();

		return "redirect:/project/edit.su?id=" + id;
	}

	private ApplicationContext applicationContext;
	
	@Override
	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException
	{
		this.applicationContext = applicationContext;
	}
}
