/**
 * http://surenpi.com
 */
package org.suren.autotest.platform.controller.api;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.validation.Valid;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.UnmarshalException;
import javax.xml.bind.Unmarshaller;

import org.dom4j.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.suren.autotest.platform.mapping.DataSourceInfoMapper;
import org.suren.autotest.platform.mapping.PageInfoMapper;
import org.suren.autotest.platform.mapping.ProjectMapper;
import org.suren.autotest.platform.mapping.SuiteRunnerInfoMapper;
import org.suren.autotest.platform.mapping.UserInfoMapper;
import org.suren.autotest.platform.model.DataSourceInfo;
import org.suren.autotest.platform.model.PageInfo;
import org.suren.autotest.platform.model.Project;
import org.suren.autotest.platform.model.SuiteRunnerInfo;
import org.suren.autotest.platform.schemas.autotest.Autotest;
import org.suren.autotest.platform.schemas.autotest.Autotest.DataSources;
import org.suren.autotest.platform.schemas.autotest.Autotest.Pages;
import org.suren.autotest.platform.schemas.autotest.DataSourceType;
import org.suren.autotest.platform.schemas.autotest.DataSourceTypeEnum;
import org.suren.autotest.platform.schemas.autotest.EngineTypeDriverEnum;
import org.suren.autotest.platform.schemas.autotest.FieldTypeEnum;
import org.suren.autotest.platform.schemas.autotest.PageFieldLocatorTypeEnum;
import org.suren.autotest.platform.schemas.autotest.PageFieldType;
import org.suren.autotest.platform.schemas.autotest.PageType;
import org.suren.autotest.platform.schemas.autotest.StrategyEnum;
import org.suren.autotest.platform.schemas.suite.Suite;
import org.suren.autotest.platform.util.DomUtils;
import org.suren.autotest.platform.util.JAXBUtils;
import org.suren.autotest.web.framework.code.Generator;
import org.suren.autotest.web.framework.core.Callback;
import org.suren.autotest.web.framework.util.StringUtils;
import org.suren.autotest.webdriver.downloader.PathUtil;
import org.xml.sax.SAXException;

import io.swagger.annotations.ApiOperation;

/**
 * 项目集
 * @author suren
 * @date 2017年1月17日 下午8:40:45
 */
@RestController
@RequestMapping("/api/pages_info/{projectId}")
public class PageInfoApiController
{
	@Autowired
	private UserInfoMapper userMapper;
	@Autowired
	private ProjectMapper projectMapper;
	@Autowired
	private PageInfoMapper pageInfoMapper;
	@Autowired
	private DataSourceInfoMapper dataSourceInfoMapper;
	@Autowired
	private SuiteRunnerInfoMapper suiteRunnerInfoMapper;
	
	@Resource(name = "xml_to_datasource")
	private Generator dataSourceGenerator;
	@Resource(name = "xml_to_suite_runner")
	private Generator suiteRunnerGenerator;

	@RequestMapping(value = "add.su", method = RequestMethod.GET)
	public String pageInfoAdd(Model model, String projectId)
	{
		PageInfo pageInfo = new PageInfo();
		pageInfo.setAutotest(initAutotest());
		pageInfo.setCreateTime(new Date());
		
		Project project = projectMapper.getById(projectId);
		if(project != null)
		{
			pageInfo.setProjectId(projectId);
			pageInfo.getAutotest().getPages().setPagePackage(project.getPkgName());
		}
		
		model.addAttribute("pageInfo", pageInfo);
		model.addAttribute("fieldType", FieldTypeEnum.values());
		model.addAttribute("strategyType", StrategyEnum.values());
		model.addAttribute("locatorType", PageFieldLocatorTypeEnum.values());
		model.addAttribute("engineType", EngineTypeDriverEnum.values());
		
		return "/page_info/test";
	}
	
	@ApiOperation("导入")
	@RequestMapping(value = "/import", method = RequestMethod.POST)
	public void autotestImport(MultipartFile file, @PathVariable String projectId)
	{
		String originalFileName = file.getOriginalFilename();
		if(originalFileName.endsWith(".xml"))
		{
			originalFileName = originalFileName.substring(0, originalFileName.length() - ".xml".length());
		}

		PageInfo pageInfo = new PageInfo();
		pageInfo.setProjectId(projectId);
		pageInfo.setName(originalFileName);
		pageInfo.setCreateTime(new Date());
		
		try
		{
			JAXBContext context = JAXBContext.newInstance(Autotest.class);
			Unmarshaller unmarshaller = context.createUnmarshaller();

			Autotest autotest = (Autotest) unmarshaller.unmarshal(file.getInputStream());
			JAXBUtils.autotestTransfer(autotest);
			
			pageInfo.setAutotest(autotest);
		}
		catch (JAXBException e)
		{
			e.printStackTrace();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public List<PageInfo> list(@PathVariable String projectId)
	{
		return pageInfoMapper.getAllByProjectId(projectId);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public void del(@PathVariable String id)
	{
		pageInfoMapper.delById(id);
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public String save(@Valid PageInfo pageInfo)
	{
		String id = pageInfo.getId();
		int tabIndex = pageInfo.getTabIndex();
		pageInfo = pageInfoMapper.getById(id);
		if(pageInfo == null)
		{
			pageInfo = new PageInfo();
		}
		else
		{
			pageInfo.setTabIndex(tabIndex);
		}
		
		try
		{
			JAXBContext context = JAXBContext.newInstance(Autotest.class);
			Unmarshaller unmarshaller = context.createUnmarshaller();

			Autotest autotest;
			String content = pageInfo.getContent();
			if(content == null)
			{
				autotest = initAutotest();
			}
			else
			{
				ByteArrayInputStream input = new ByteArrayInputStream(content.getBytes("utf-8"));
				
				try
				{
					autotest = (Autotest) unmarshaller.unmarshal(input);
				}
				catch(UnmarshalException e)
				{
					autotest = initAutotest();
					e.printStackTrace();
				}
			}

			pageInfo.setAutotest(autotest);
		}
		catch (JAXBException e)
		{
			e.printStackTrace();
		}
		catch (UnsupportedEncodingException e1)
		{
			e1.printStackTrace();
		}
	}

	@RequestMapping("hello.su")
	public void hello(Model model, @RequestParam(defaultValue = "qwe") String id,
			String name)
	{
		model.addAttribute("time", new Date());
		
		PageInfo pageInfo = pageInfoMapper.getById(id);
		model.addAttribute("page", pageInfo);
		
		try
		{
			JAXBContext context = JAXBContext.newInstance(Autotest.class);
			Unmarshaller unmarshaller = context.createUnmarshaller();
	        
			String content = pageInfo.getContent();
			if(content == null)
			{
				content = "";
			}
			
			ByteArrayInputStream input = new ByteArrayInputStream(content.getBytes("utf-8"));
			Autotest autotest = (Autotest) unmarshaller.unmarshal(input);

			Pages pages = autotest.getPages();
			PageType page = pages.getPage().get(1);
			for(PageType pageT : pages.getPage())
			{
				if(pageT.getClazz().equals(name))
				{
					page = pageT;
					break;
				}
			}
//			JAXBUtils.transform(page.getContent());

			model.addAttribute("pages", pages);
			model.addAttribute("page", page);
			model.addAttribute("autotest", autotest);
			model.addAttribute("fieldType", FieldTypeEnum.values());
			model.addAttribute("strategyType", StrategyEnum.values());
			model.addAttribute("locatorType", PageFieldLocatorTypeEnum.values());
//			
//			XMLSerializer ser = new XMLSerializer();
//			JSON obj = ser.read(content);
//			System.out.println(obj);
//			
//			JSONObject jsonObject = JSONObject.fromObject((obj.toString().replace("@", "")));//.substring(1, obj.toString().length()-1)); 
//			System.out.println(jsonObject);
			
//			model.addAttribute("hao", jsonObject);
		}
		catch (JAXBException e)
		{
			e.printStackTrace();
		}
		catch (UnsupportedEncodingException e)
		{
			e.printStackTrace();
		}
	}
	
	@RequestMapping("addPage.su")
	public String addPage(Model model, String id)
	{
		PageInfo pageInfo = pageInfoMapper.getById(id);
		model.addAttribute("pageInfo", pageInfo);
		
		try
		{
			JAXBContext context = JAXBContext.newInstance(Autotest.class);
			Unmarshaller unmarshaller = context.createUnmarshaller();
	        
			String content = pageInfo.getContent();
			Autotest autotest;
			if(content != null)
			{
				ByteArrayInputStream input = new ByteArrayInputStream(content.getBytes("utf-8"));
				autotest = (Autotest) unmarshaller.unmarshal(input);
			}
			else
			{
				autotest = initAutotest();
			}
			
			pageInfo.setAutotest(autotest);
			
			PageType pageType = new PageType();
			pageType.setClazz("PageStuff");
			PageFieldType pageFieldType = new PageFieldType();
			pageFieldType.setName("PageFieldName");
			pageType.getField().add(pageFieldType);
			autotest.getPages().getPage().add(pageType);

			model.addAttribute("autotest", autotest);
			model.addAttribute("fieldType", FieldTypeEnum.values());
			model.addAttribute("strategyType", StrategyEnum.values());
			model.addAttribute("locatorType", PageFieldLocatorTypeEnum.values());
			model.addAttribute("engineType", EngineTypeDriverEnum.values());
		}
		catch (JAXBException e)
		{
			e.printStackTrace();
		}
		catch (UnsupportedEncodingException e)
		{
			e.printStackTrace();
		}
		
		return "page_info/test";
	}
	
	@RequestMapping("addField")
	public String addField(Model model, String id, String pageName, int tabIndex)
	{
		PageInfo pageInfo = pageInfoMapper.getById(id);
		pageInfo.setTabIndex(tabIndex);
		initEnums(model);
		model.addAttribute("pageInfo", pageInfo);
		
		try
		{
			JAXBContext context = JAXBContext.newInstance(Autotest.class);
			ByteArrayInputStream input = new ByteArrayInputStream(pageInfo.getContent().getBytes("utf-8"));
			
			Autotest autotest = (Autotest) context.createUnmarshaller().unmarshal(input);
			
			pageInfo.setAutotest(autotest);
			
			for(PageType page : autotest.getPages().getPage())
			{
				if(page.getClazz().equals(pageName))
				{
					PageFieldType field = new PageFieldType();
					field.setName(System.currentTimeMillis() + "_field");
					page.getField().add(field );
					break;
				}
			}
		}
		catch (JAXBException e)
		{
			e.printStackTrace();
		}
		catch (UnsupportedEncodingException e)
		{
			e.printStackTrace();
		}
		
		return "page_info/test";
	}
	
	@ResponseBody
	@RequestMapping(value = "updatePage.su")
	public PageInfo updatePage(Model model, PageInfo pageInfo)
	{
		try
		{
			Autotest autotest = pageInfo.getAutotest();
			
			List<DataSourceType> dataSourceList;
			if(autotest.getDataSources() == null)
			{
				dataSourceList = new ArrayList<DataSourceType>();
				
				DataSources dataSources = new DataSources();
				autotest.setDataSources(dataSources);
				dataSources.setDataSource(dataSourceList);
			}
			else
			{
				dataSourceList = autotest.getDataSources().getDataSource();
				for(int i = 0; i < dataSourceList.size();)
				{
					DataSourceType dataSourceType = dataSourceList.get(i);
					
					if(StringUtils.isBlank(dataSourceType.getName()))
					{
						dataSourceList.remove(i);
					}
					else
					{
						 i++;
					}
				}
			}
			
			for(PageType pageType : autotest.getPages().getPage())
			{
				boolean notFound = true;
				String dataSourceName = pageType.getDataSource();
				for(DataSourceType dataSourceType : dataSourceList)
				{
					if(dataSourceName.equals(dataSourceType.getName()))
					{
						notFound = false;
						break;
					}
				}
				
				if(notFound && StringUtils.isNotBlank(dataSourceName))
				{
					DataSourceType dataSourceType = new DataSourceType();
					dataSourceType.setName(dataSourceName);
					dataSourceType.setType(DataSourceTypeEnum.XML_DATA_SOURCE);
					dataSourceType.setResource(dataSourceName + ".xml");
					
					dataSourceList.add(dataSourceType);
				}
			}
			
			JAXBContext context = JAXBContext.newInstance(Autotest.class);
			Marshaller marshaller = context.createMarshaller();
			
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			marshaller.marshal(autotest, out);

			try
			{
				pageInfo.setContent(out.toString("UTF-8"));
			}
			catch (UnsupportedEncodingException e)
			{
				e.printStackTrace();
			}
			
			if(StringUtils.isNotBlank(pageInfo.getId()))
			{
				pageInfoMapper.update(pageInfo);
			}
			else
			{
				pageInfo.setCreateTime(new Date());
				pageInfoMapper.save(pageInfo);
			}
			
			pageInfo.setContent(null);
		}
		catch (JAXBException e)
		{
			e.printStackTrace();
		}
		
		return pageInfo;
	}
	
	@RequestMapping("delPage.su")
	public String delPage(String id, String pageName)
	{
		PageInfo pageInfo = pageInfoMapper.getById(id);

		try
		{
			JAXBContext context = JAXBContext.newInstance(Autotest.class);
			Unmarshaller unmarshaller = context.createUnmarshaller();
	        
			String content = pageInfo.getContent();
			if(content == null)
			{
				content = "";
			}
			
			ByteArrayInputStream input = new ByteArrayInputStream(content.getBytes("utf-8"));
			Autotest autotest = (Autotest) unmarshaller.unmarshal(input);
			
			List<PageType> pages = autotest.getPages().getPage();
			for(PageType page : pages)
			{
				if(page.getClazz().equals(pageName))
				{
					pages.remove(page);
					break;
				}
			}
			
			Marshaller marshaller = context.createMarshaller();
			
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			marshaller.marshal(autotest, out);
			
			pageInfo.setContent(out.toString("UTF-8"));
			
			pageInfoMapper.update(pageInfo);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return "redirect:/page_info/test.su";
	}
	
	@RequestMapping(value = "/download.su")
	public ResponseEntity<byte[]> download(String id)
	{
		PageInfo pageInfo = pageInfoMapper.getById(id);
		
		String content = pageInfo.getContent();
		content = (StringUtils.isBlank(content) ? "" : DomUtils.format(content));

		String fileName = pageInfo.getName();
		try
		{
			fileName = URLEncoder.encode(fileName, "utf-8");
		}
		catch (UnsupportedEncodingException e)
		{
			e.printStackTrace();
		}
		
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.TEXT_XML);
		headers.setContentDispositionFormData("filename", fileName + ".xml");
		
		try
		{
			return new ResponseEntity<byte[]>(content.getBytes("utf-8"), headers, HttpStatus.CREATED);
		}
		catch (UnsupportedEncodingException e)
		{
			e.printStackTrace();
		}
		
		return new ResponseEntity<byte[]>("not supported encoding.".getBytes(), headers, HttpStatus.CREATED);
	}
	
	/**
	 * 根据给定id的页面集生成数据源，然后跳转到数据源页面（当前项目）
	 * @param id 页面集id
	 * @return
	 */
	@RequestMapping("/generateDataSource")
	public String generateDataSource(String id)
	{
		final PageInfo pageInfo = pageInfoMapper.getById(id);
		if(pageInfo != null && StringUtils.isNotBlank(pageInfo.getContent()))
		{
			final String projectId = pageInfo.getProjectId();
			
			File outputDir = PathUtil.getRootDir();
			try
			{
				ByteArrayInputStream input = new ByteArrayInputStream(pageInfo.getContent().getBytes("utf-8"));
				
				dataSourceGenerator.generate(input, outputDir.toString(), new Callback<File>()
				{
					
					@Override
					public void callback(File data)
					{
						String name = data.getName();
						
						DataSourceInfo dataSourceInfo = new DataSourceInfo();
						dataSourceInfo.setName(name.replace(".xml", ""));
						dataSourceInfo.setProjectId(projectId);
						
						try(InputStream input = new FileInputStream(data))
						{
							StringBuffer contentBuf = new StringBuffer();
							
							byte[] buf = new byte[1024];
							int len = -1;
							
							while((len = input.read(buf)) != -1)
							{
								contentBuf.append(new String(buf, 0, len));
							}
							
							dataSourceInfo.setContent(contentBuf.toString());
						}
						catch (IOException e)
						{
							e.printStackTrace();
						}
						
						dataSourceInfoMapper.save(dataSourceInfo);
					}
				});
			}
			catch (DocumentException | SAXException e)
			{
				e.printStackTrace();
			}
			catch (UnsupportedEncodingException e1)
			{
				e1.printStackTrace();
			}
			
			return "redirect:/data_source_info/list.su?projectId=" + projectId;
		}
		else
		{
			return "redirect:/project/list.su";
		}
	}

	@RequestMapping("/generateSuiteRunner")
	public String generateSuiteRunner(String id)
	{
		PageInfo pageInfo = pageInfoMapper.getById(id);
		if(pageInfo != null && StringUtils.isNotBlank(pageInfo.getContent()))
		{
			final String projectId = pageInfo.getProjectId();

			File outputDir = PathUtil.getRootDir();
			try
			{
				final String pageInfoName = pageInfo.getName();
				
				ByteArrayInputStream input = new ByteArrayInputStream(pageInfo.getContent().getBytes("utf-8"));
				suiteRunnerGenerator.generate(input, outputDir.toString(), new Callback<File>()
				{

					@Override
					public void callback(File data)
					{
						SuiteRunnerInfo suiteRunnerInfo = new SuiteRunnerInfo();
						suiteRunnerInfo.setProjectId(projectId);
						suiteRunnerInfo.setName(pageInfoName + "测试");
						suiteRunnerInfo.setCreateTime(new Date());
						suiteRunnerInfo.setRemark("Generate from " + pageInfoName);
						
						StringBuffer contentBuf = new StringBuffer();
						try(InputStream input = new FileInputStream(data))
						{
							byte[] buf = new byte[1024];
							int len = -1;
							
							while((len = input.read(buf)) != -1)
							{
								contentBuf.append(new String(buf, 0, len));
							}
						}
						catch (IOException e)
						{
							e.printStackTrace();
						}
						
						suiteRunnerInfo.setContent(contentBuf.toString());

						try
						{
							JAXBContext context = JAXBContext.newInstance(Suite.class);
							Unmarshaller unmarshaller = context.createUnmarshaller();
							
							Suite suite = (Suite) unmarshaller.unmarshal(
									new ByteArrayInputStream(contentBuf.toString().getBytes()));
							suite.setPageConfig(pageInfoName + ".xml");
							
							Marshaller marshaller = context.createMarshaller();
							
							ByteArrayOutputStream out = new ByteArrayOutputStream();
							marshaller.marshal(suite, out);
							
							suiteRunnerInfo.setContent(out.toString("UTF-8"));
						}
						catch (JAXBException e)
						{
							e.printStackTrace();
						}
						catch (UnsupportedEncodingException e)
						{
							e.printStackTrace();
						}
						
						suiteRunnerInfoMapper.save(suiteRunnerInfo);
					}
				});
			}
			catch (DocumentException | SAXException e)
			{
				e.printStackTrace();
			}
			catch (UnsupportedEncodingException e1)
			{
				e1.printStackTrace();
			}
			
			return "redirect:/suite_runner_info/list.su?projectId=" + projectId;
		}
		else
		{
			return "redirect:/project/list.su";
		}
	}
	
	@ResponseBody
	@RequestMapping("count")
	public int getCountByProjectId(String projectId)
	{
		return pageInfoMapper.getCountByProjectId(projectId);
	}
	
	private Autotest initAutotest()
	{
		Autotest autotest = new Autotest();
		Autotest.Pages pages = new Autotest.Pages();
		autotest.setPages(pages);
		
		PageType pageType = new PageType();
		pageType.setClazz("类名");
		pages.getPage().add(pageType);
		
		PageFieldType pageFieldType = new PageFieldType();
		pageFieldType.setName("属性");
		pageType.getField().add(pageFieldType);

		return autotest;
	}
	
	private void initEnums(Model model)
	{
		model.addAttribute("fieldType", FieldTypeEnum.values());
		model.addAttribute("strategyType", StrategyEnum.values());
		model.addAttribute("locatorType", PageFieldLocatorTypeEnum.values());
		model.addAttribute("engineType", EngineTypeDriverEnum.values());
		model.addAttribute("dataSourceType", DataSourceTypeEnum.values());
	}
}
