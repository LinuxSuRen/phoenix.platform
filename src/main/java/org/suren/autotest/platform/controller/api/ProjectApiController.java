/**
 * http://surenpi.com
 */
package org.suren.autotest.platform.controller.api;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import javax.annotation.Resource;
import javax.validation.Valid;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import com.surenpi.autotest.code.Generator;
import org.apache.poi.util.IOUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.suren.autotest.platform.entity.DataSourceInfo;
import org.suren.autotest.platform.entity.PageInfo;
import org.suren.autotest.platform.mapping.DataSourceInfoMapper;
import org.suren.autotest.platform.mapping.PageInfoMapper;
import org.suren.autotest.platform.mapping.ProjectMapper;
import org.suren.autotest.platform.mapping.SuiteRunnerInfoMapper;
import org.suren.autotest.platform.model.Project;
import org.suren.autotest.platform.model.SuiteRunnerInfo;
import org.suren.autotest.platform.schemas.autotest.Autotest;
import org.suren.autotest.platform.schemas.datasource.DataSources;
import org.suren.autotest.platform.schemas.suite.Suite;
import org.suren.autotest.platform.security.UserDetail;
import org.suren.autotest.platform.util.JAXBUtils;
import org.suren.autotest.web.framework.util.StringUtils;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 项目管理
 * @author suren
 * @date 2017年1月20日 下午7:43:05
 */
@RestController
@RequestMapping("/api/projects")
@Api("项目管理")
public class ProjectApiController implements ApplicationContextAware
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
	
	@Resource(name = "xml_to_java")
	private Generator codeGenerator;
	
	@ApiOperation("项目列表")
	@RequestMapping(method = RequestMethod.GET)
	public List<Project> list()
	{
		return projectMapper.getAll();
	}
	
	@ApiOperation("项目信息")
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public Project getInfo(@PathVariable String id)
	{
		return projectMapper.getById(id);
	}
	
	@ApiOperation("项目模板")
	@RequestMapping(value = "/model", method = RequestMethod.GET)
	public Project edit()
	{
		Project proForModel = new Project();
		UserDetail userDetail = (UserDetail) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String ownerId = userDetail.getId();
		proForModel.setOwnerId(ownerId);
		
		return proForModel;
	}

	@ApiOperation("新增项目")
	@RequestMapping(method = RequestMethod.POST)
	public String save(@Valid Project project)
	{
		if(StringUtils.isBlank(project.getId()))
		{
			UserDetail userDetail = (UserDetail)
					SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			String ownerId = userDetail.getId();
			
			project.setOwnerId(ownerId);
			project.setCreateTime(new Date());
			projectMapper.save(project);
		}
		else
		{
			projectMapper.update(project);
		}
		
		return project.getId();
	}

	@ApiOperation("项目删除")
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public void del(@PathVariable String id)
	{
		projectMapper.delById(id);
	}

//	@ApiOperation("项目部署")
//	@RequestMapping(value = "/deploy/{id}", method = RequestMethod.GET)
//	public String projectDeploy(@PathVariable String id)
//	{
//		UserDetail userDetail = (UserDetail) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//		String ownerId = userDetail.getId();
//		
//		String projectId = id;
//		File rootDir = new File(servletContext.getRealPath("/deploy"), id + "/" + ownerId);
//		rootDir.mkdirs();
//		File srcOutputDir = new File(rootDir, "src");
//		JDTUtils jdtUtils = new JDTUtils(srcOutputDir);
//		
//		//附件拷贝
//		List<Attachment> attachList = attachmentMapper.getByBelongId(projectId);
//		if(CollectionUtils.isNotEmpty(attachList))
//		{
//			for(Attachment attach : attachList)
//			{
//				String name = attach.getFileName();
//				if(name.endsWith(".java"))
//				{
//					String pkgPath = attach.getRemark();
//					pkgPath = StringUtils.isBlank(pkgPath) ? "" : pkgPath.trim();
//					pkgPath = pkgPath.replace(".", "/");
//					
//					try
//					{
//						FileUtils.copyFile(new File(attach.getRelativePath(), name),
//								new File(srcOutputDir, pkgPath + "/" + name));
//					}
//					catch (IOException e)
//					{
//						e.printStackTrace();
//					}
//				}
//			}
//		}
//
//		//元素定位文件部署
//		List<PageInfo> pageInfoList = pageInfoMapper.getAllWithContentByProjectId(projectId);
//		if(CollectionUtils.isNotEmpty(pageInfoList))
//		{
//			for(PageInfo pageInfo : pageInfoList)
//			{
//				String content = pageInfo.getContent();
//				File autotestFile = new File(rootDir, pageInfo.getName() + ".xml");
//				
//				try
//				{
//					FileUtils.writeStringToFile(autotestFile, content, "UTF-8");
//					
//					//生成Java源码
//					codeGenerator.generate(autotestFile.toString(), srcOutputDir.toString());
//				}
//				catch (IOException e)
//				{
//					e.printStackTrace();
//				}
//			}
//			
//			//编译Java源码
//			List<File> result = jdtUtils.compileAllFile();
//			try
//			{
//				AutoTestClassloader cla = new AutoTestClassloader(new URL[]{srcOutputDir.toURI().toURL()},
//						Thread.currentThread().getContextClassLoader());
//				
//				Thread.currentThread().setContextClassLoader(cla);
//				
//				String rootPath = srcOutputDir.getAbsolutePath();
//				for(File javaSrcFile : result)
//				{
//					String absPath = javaSrcFile.getAbsolutePath();
//					String clsName = absPath.replace(rootPath, "").replace("/", "\\").replace("\\", ".").replace(".java", "");
//					clsName = clsName.substring(1);
//					
//					Class<?> target = cla.loadClass(clsName);
//					
//					BeanDefinitionRegistry reg = (BeanDefinitionRegistry) ((ConfigurableApplicationContext)applicationContext.getParent()).getBeanFactory();
//					
//					BeanDefinitionBuilder beanDef = BeanDefinitionBuilder.genericBeanDefinition(target);
//					
//					AbstractBeanDefinition bean = beanDef.getBeanDefinition();
//					reg.registerBeanDefinition(target.getName(), bean);
//					
//					Object targetObj = applicationContext.getBean(target.getName());
//					for(Field field : target.getDeclaredFields())
//					{
//						try
//						{
//							Object fieldObj = applicationContext.getBean(field.getType());
//							
//							field.setAccessible(true);
//							field.set(targetObj, fieldObj);;
//						}
//						catch (IllegalAccessException | IllegalArgumentException e)
//						{
//							e.printStackTrace();
//						}
//						catch(NoSuchBeanDefinitionException e)
//						{
//						}
//						
//						if(field.getAnnotation(Autowired.class) != null)
//						{
//						}
//					}
//				}
//			}
//			catch (MalformedURLException | ClassNotFoundException e)
//			{
//				e.printStackTrace();
//			}
//		}
//		
//		//数据源文件部署
//		List<DataSourceInfo> dataSourceInfoList = dataSourceInfoMapper.getAllWithContentByProjectId(projectId);
//		if(CollectionUtils.isNotEmpty(dataSourceInfoList))
//		{
//			for(DataSourceInfo dataSourceInfo : dataSourceInfoList)
//			{
//				String content = dataSourceInfo.getContent();
//				File dataSourceFile = new File(rootDir, dataSourceInfo.getName() + ".xml");
//				
//				try
//				{
//					FileUtils.writeStringToFile(dataSourceFile, content, "utf-8");
//				}
//				catch (IOException e)
//				{
//					e.printStackTrace();
//				}
//			}
//		}
//		
//		//测试套件文件部署
//		List<SuiteRunnerInfo> suiteRunnerInfoList = suiteRunnerInfoMapper.getAllWithContentByProjectId(projectId);
//		if(CollectionUtils.isNotEmpty(suiteRunnerInfoList))
//		{
//			for(SuiteRunnerInfo suiteRunnerInfo : suiteRunnerInfoList)
//			{
//				String content = suiteRunnerInfo.getContent();
//				File suiteFile = new File(rootDir, suiteRunnerInfo.getName() + ".xml");
//				
//				try
//				{
//					FileUtils.writeStringToFile(suiteFile, content, "utf-8");
//				}
//				catch (IOException e)
//				{
//					e.printStackTrace();
//				}
//			}
//		}
//		
//		return "redirect:/project/list.su";
//	}
	
	@RequestMapping(value = "/import", method = RequestMethod.POST)
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
//							pageInfo.setContent(doc.asXML());
							
							try
							{
								JAXBContext context = JAXBContext.newInstance(Autotest.class);
								Unmarshaller unmarshaller = context.createUnmarshaller();
								
								Autotest autotest = (Autotest) unmarshaller.unmarshal(
										new ByteArrayInputStream(doc.asXML().getBytes()));
								JAXBUtils.autotestTransfer(autotest);
								
								ByteArrayOutputStream autoTestByteOut = new ByteArrayOutputStream();
								context.createMarshaller().marshal(autotest, autoTestByteOut);
								
//								pageInfo.setContent(autoTestByteOut.toString("UTF-8"));
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
