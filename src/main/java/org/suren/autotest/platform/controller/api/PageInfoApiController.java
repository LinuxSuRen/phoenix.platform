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
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.dom4j.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.suren.autotest.platform.entity.PageInfo;
import org.suren.autotest.platform.mapping.PageInfoMapper;
import org.suren.autotest.platform.model.DataSourceInfo;
import org.suren.autotest.platform.model.SuiteRunnerInfo;
import org.suren.autotest.platform.schemas.autotest.Autotest;
import org.suren.autotest.platform.schemas.autotest.Autotest.DataSources;
import org.suren.autotest.platform.schemas.autotest.DataSourceType;
import org.suren.autotest.platform.schemas.autotest.DataSourceTypeEnum;
import org.suren.autotest.platform.schemas.autotest.PageType;
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
 * 页面集
 * @author suren
 * @date 2017年1月17日 下午8:40:45
 */
@RestController
@RequestMapping("/api/pages_info/{projectId}")
public class PageInfoApiController
{
	@Autowired
	private PageInfoMapper pageInfoMapper;
	
	@Resource(name = "xml_to_datasource")
	private Generator dataSourceGenerator;
	@Resource(name = "xml_to_suite_runner")
	private Generator suiteRunnerGenerator;
	
	@ApiOperation("导入")
	@RequestMapping(value = "/transfer", method = RequestMethod.POST)
	public PageInfo autotestTransfer(MultipartFile file, @PathVariable String projectId)
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
			
//			pageInfo.setAutotest(autotest);
		}
		catch (JAXBException e)
		{
			e.printStackTrace();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}

		return pageInfo;
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public List<PageInfo> list(@PathVariable String projectId)
	{
		return pageInfoMapper.getAllByProjectId(projectId);
	}

	@ApiOperation("删除Page信息")
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public void del(@PathVariable String id,  @PathVariable String projectId)
	{
		pageInfoMapper.delById(id);
	}

	@ApiOperation("获取Page信息")
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public PageInfo getPageInfo(@PathVariable String id,  @PathVariable String projectId)
	{
		return pageInfoMapper.getById(id);
	}

	@ApiOperation("更新Page信息")
	@RequestMapping(method = RequestMethod.POST)
	public void updatePage(@RequestBody PageInfo pageInfo,  @PathVariable String projectId)
	{
		pageInfo.setProjectId(projectId);
		pageInfoMapper.save(pageInfo);
	}

//	@ApiOperation("更新Page信息")
//	@RequestMapping(method = RequestMethod.POST)
//	public void updatePage(@RequestBody PageInfo pageInfo,  @PathVariable String projectId)
//	{
//		try
//		{
//			Autotest autotest = pageInfo.getAutotest();
//			
//			List<DataSourceType> dataSourceList;
//			if(autotest.getDataSources() == null)
//			{
//				dataSourceList = new ArrayList<DataSourceType>();
//				
//				DataSources dataSources = new DataSources();
//				autotest.setDataSources(dataSources);
//				dataSources.setDataSource(dataSourceList);
//			}
//			else
//			{
//				dataSourceList = autotest.getDataSources().getDataSource();
//				for(int i = 0; i < dataSourceList.size();)
//				{
//					DataSourceType dataSourceType = dataSourceList.get(i);
//					
//					if(StringUtils.isBlank(dataSourceType.getName()))
//					{
//						dataSourceList.remove(i);
//					}
//					else
//					{
//						 i++;
//					}
//				}
//			}
//			
//			for(PageType pageType : autotest.getPages().getPage())
//			{
//				boolean notFound = true;
//				String dataSourceName = pageType.getDataSource();
//				for(DataSourceType dataSourceType : dataSourceList)
//				{
//					if(dataSourceName.equals(dataSourceType.getName()))
//					{
//						notFound = false;
//						break;
//					}
//				}
//				
//				if(notFound && StringUtils.isNotBlank(dataSourceName))
//				{
//					DataSourceType dataSourceType = new DataSourceType();
//					dataSourceType.setName(dataSourceName);
//					dataSourceType.setType(DataSourceTypeEnum.XML_DATA_SOURCE);
//					dataSourceType.setResource(dataSourceName + ".xml");
//					
//					dataSourceList.add(dataSourceType);
//				}
//			}
//			
//			JAXBContext context = JAXBContext.newInstance(Autotest.class);
//			Marshaller marshaller = context.createMarshaller();
//			
//			ByteArrayOutputStream out = new ByteArrayOutputStream();
//			marshaller.marshal(autotest, out);
//
////			try
////			{
////				pageInfo.setContent(out.toString("UTF-8"));
////			}
////			catch (UnsupportedEncodingException e)
////			{
////				e.printStackTrace();
////			}
//			
//			if(StringUtils.isNotBlank(pageInfo.getId()))
//			{
//				pageInfoMapper.update(pageInfo);
//			}
//			else
//			{
//				pageInfo.setCreateTime(new Date());
//				pageInfoMapper.save(pageInfo);
//			}
//			
////			pageInfo.setContent(null);
//		}
//		catch (JAXBException e)
//		{
//			e.printStackTrace();
//		}
//	}

	@ApiOperation("根据页面名称删除页面")
	@RequestMapping(value = "/page/{id}", method = RequestMethod.DELETE)
	public void delPage(@PathVariable String id, @RequestParam String pageName,  @PathVariable String projectId)
	{
		PageInfo pageInfo = pageInfoMapper.getById(id);

		try
		{
			JAXBContext context = JAXBContext.newInstance(Autotest.class);
			Unmarshaller unmarshaller = context.createUnmarshaller();
	        
//			String content = pageInfo.getContent();
//			if(content == null)
//			{
//				content = "";
//			}
			
//			ByteArrayInputStream input = new ByteArrayInputStream(content.getBytes("utf-8"));
//			Autotest autotest = (Autotest) unmarshaller.unmarshal(input);
//			
//			List<PageType> pages = autotest.getPages().getPage();
//			for(PageType page : pages)
//			{
//				if(page.getClazz().equals(pageName))
//				{
//					pages.remove(page);
//					break;
//				}
//			}
//			
//			Marshaller marshaller = context.createMarshaller();
//			
//			ByteArrayOutputStream out = new ByteArrayOutputStream();
//			marshaller.marshal(autotest, out);
//			
//			pageInfo.setContent(out.toString("UTF-8"));
			
			pageInfoMapper.update(pageInfo);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	@ApiOperation("下载页面")
	@RequestMapping(value = "/{id}/download", method = RequestMethod.GET)
	public ResponseEntity<byte[]> download(@PathVariable String id,  @PathVariable String projectId)
	{
		PageInfo pageInfo = pageInfoMapper.getById(id);
		
//		String content = pageInfo.getContent();
//		content = (StringUtils.isBlank(content) ? "" : DomUtils.format(content));

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
		
//		try
//		{
//			return new ResponseEntity<byte[]>(content.getBytes("utf-8"), headers, HttpStatus.CREATED);
//		}
//		catch (UnsupportedEncodingException e)
//		{
//			e.printStackTrace();
//		}
		
		return new ResponseEntity<byte[]>("not supported encoding.".getBytes(), headers, HttpStatus.CREATED);
	}
	
	/**
	 * 根据给定id的页面集生成数据源，然后跳转到数据源页面（当前项目）
	 * @param id 页面集id
	 * @return
	 */
	@ApiOperation("生成数据源")
	@RequestMapping(value = "/{id}/generateDataSource", method = RequestMethod.GET)
	public DataSourceInfo generateDataSource(@PathVariable String id,  @PathVariable String projectId)
	{
		final PageInfo pageInfo = pageInfoMapper.getById(id);
//		if(pageInfo != null && StringUtils.isNotBlank(pageInfo.getContent()))
//		{
//			File outputDir = PathUtil.getRootDir();
//			try
//			{
//				ByteArrayInputStream input = new ByteArrayInputStream(pageInfo.getContent().getBytes("utf-8"));
//				final DataSourceInfo dataSourceInfo = new DataSourceInfo();
//
//				dataSourceGenerator.generate(input, outputDir.toString(), new Callback<File>()
//				{
//					
//					@Override
//					public void callback(File data)
//					{
//						String name = data.getName();
//
//						dataSourceInfo.setName(name.replace(".xml", ""));
//						dataSourceInfo.setProjectId(projectId);
//						
//						try(InputStream input = new FileInputStream(data))
//						{
//							StringBuffer contentBuf = new StringBuffer();
//							
//							byte[] buf = new byte[1024];
//							int len = -1;
//							
//							while((len = input.read(buf)) != -1)
//							{
//								contentBuf.append(new String(buf, 0, len));
//							}
//							
//							dataSourceInfo.setContent(contentBuf.toString());
//						}
//						catch (IOException e)
//						{
//							e.printStackTrace();
//						}
//					}
//				});
//			}
//			catch (DocumentException | SAXException e)
//			{
//				e.printStackTrace();
//			}
//			catch (UnsupportedEncodingException e1)
//			{
//				e1.printStackTrace();
//			}
//		}

		return null;
	}

	@ApiOperation("生成套件信息")
	@RequestMapping(value = "/{id}/generateSuiteRunner", method = RequestMethod.GET)
	public SuiteRunnerInfo generateSuiteRunner(@PathVariable String id,  @PathVariable String projectId)
	{
		PageInfo pageInfo = pageInfoMapper.getById(id);
//		if(pageInfo != null && StringUtils.isNotBlank(pageInfo.getContent()))
//		{
//			File outputDir = PathUtil.getRootDir();
//			try
//			{
//				final String pageInfoName = pageInfo.getName();
//				SuiteRunnerInfo suiteRunnerInfo = new SuiteRunnerInfo();
//				
//				ByteArrayInputStream input = new ByteArrayInputStream(pageInfo.getContent().getBytes("utf-8"));
//				suiteRunnerGenerator.generate(input, outputDir.toString(), new Callback<File>()
//				{
//
//					@Override
//					public void callback(File data)
//					{
//						suiteRunnerInfo.setProjectId(projectId);
//						suiteRunnerInfo.setName(pageInfoName + "测试");
//						suiteRunnerInfo.setCreateTime(new Date());
//						suiteRunnerInfo.setRemark("Generate from " + pageInfoName);
//						
//						StringBuffer contentBuf = new StringBuffer();
//						try(InputStream input = new FileInputStream(data))
//						{
//							byte[] buf = new byte[1024];
//							int len = -1;
//							
//							while((len = input.read(buf)) != -1)
//							{
//								contentBuf.append(new String(buf, 0, len));
//							}
//						}
//						catch (IOException e)
//						{
//							e.printStackTrace();
//						}
//						
//						suiteRunnerInfo.setContent(contentBuf.toString());
//
//						try
//						{
//							JAXBContext context = JAXBContext.newInstance(Suite.class);
//							Unmarshaller unmarshaller = context.createUnmarshaller();
//							
//							Suite suite = (Suite) unmarshaller.unmarshal(
//									new ByteArrayInputStream(contentBuf.toString().getBytes()));
//							suite.setPageConfig(pageInfoName + ".xml");
//							
//							Marshaller marshaller = context.createMarshaller();
//							
//							ByteArrayOutputStream out = new ByteArrayOutputStream();
//							marshaller.marshal(suite, out);
//							
//							suiteRunnerInfo.setContent(out.toString("UTF-8"));
//						}
//						catch (JAXBException e)
//						{
//							e.printStackTrace();
//						}
//						catch (UnsupportedEncodingException e)
//						{
//							e.printStackTrace();
//						}
//					}
//				});
//
//				return suiteRunnerInfo;
//			}
//			catch (DocumentException | SAXException e)
//			{
//				e.printStackTrace();
//			}
//			catch (UnsupportedEncodingException e1)
//			{
//				e1.printStackTrace();
//			}
//		}

		return null;
	}

	@RequestMapping(value = "/count", method = RequestMethod.GET)
	public int getCountByProjectId(@PathVariable String projectId)
	{
		return pageInfoMapper.getCountByProjectId(projectId);
	}
}
