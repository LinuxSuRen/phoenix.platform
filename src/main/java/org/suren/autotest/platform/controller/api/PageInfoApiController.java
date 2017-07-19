/**
 * http://surenpi.com
 */
package org.suren.autotest.platform.controller.api;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.suren.autotest.platform.entity.DataSourceInfo;
import org.suren.autotest.platform.entity.PageField;
import org.suren.autotest.platform.entity.PageInfo;
import org.suren.autotest.platform.mapping.PageFieldMapper;
import org.suren.autotest.platform.mapping.PageInfoMapper;
import org.suren.autotest.platform.model.SuiteRunnerInfo;
import org.suren.autotest.platform.schemas.autotest.Autotest;
import org.suren.autotest.platform.util.JAXBUtils;

import com.surenpi.autotest.code.Generator;

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
	@Autowired
	private PageFieldMapper pageFieldMapper;
	
	@Resource(name = "xml_to_datasource")
	private Generator dataSourceGenerator;
	@Resource(name = "xml_to_suite_runner")
	private Generator suiteRunnerGenerator;
	
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

	@ApiOperation("新增Page信息")
	@RequestMapping(method = RequestMethod.POST)
	public void updatePage(@RequestBody PageInfo pageInfo,  @PathVariable String projectId)
	{
		pageInfo.setCreateTime(new Date());
		pageInfo.setProjectId(projectId);
		pageInfoMapper.save(pageInfo);
	}

	@ApiOperation("下载页面")
	@RequestMapping(value = "/{id}/download", method = RequestMethod.GET)
	public ResponseEntity<byte[]> download(@PathVariable String id,  @PathVariable String projectId)
	{
		PageInfo pageInfo = pageInfoMapper.getById(id);
		List<PageField> pageFieldList = pageFieldMapper.getByPageId(id);
		
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

	@RequestMapping(value = "/count", method = RequestMethod.GET)
	public int getCountByProjectId(@PathVariable String projectId)
	{
		return pageInfoMapper.getCountByProjectId(projectId);
	}
}
