/**
 * http://surenpi.com
 */
package org.suren.autotest.platform.controller;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.Date;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.UnmarshalException;
import javax.xml.bind.Unmarshaller;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.suren.autotest.platform.mapping.PageInfoMapper;
import org.suren.autotest.platform.mapping.UserMapper;
import org.suren.autotest.platform.model.PageInfo;
import org.suren.autotest.platform.schemas.Autotest;
import org.suren.autotest.platform.schemas.Autotest.Pages;
import org.suren.autotest.platform.schemas.EngineTypeDriverEnum;
import org.suren.autotest.platform.schemas.FieldTypeEnum;
import org.suren.autotest.platform.schemas.PageFieldLocatorTypeEnum;
import org.suren.autotest.platform.schemas.PageFieldType;
import org.suren.autotest.platform.schemas.PageType;
import org.suren.autotest.platform.schemas.StrategyEnum;

/**
 * 项目集
 * @author suren
 * @date 2017年1月17日 下午8:40:45
 */
@Controller
@RequestMapping("page_info")
public class PageInfoController
{
	@Autowired
	private UserMapper userMapper;
	@Autowired
	private PageInfoMapper pageInfoMapper;

	@RequestMapping("add.su")
	public String pageInfoAdd(Model model, String projectId)
	{
		PageInfo pageInfo = new PageInfo();
		pageInfo.setProjectId(projectId);
		pageInfo.setAutotest(initAutotest());
		
		model.addAttribute("pageInfo", pageInfo);
		model.addAttribute("fieldType", FieldTypeEnum.values());
		model.addAttribute("strategyType", StrategyEnum.values());
		model.addAttribute("locatorType", PageFieldLocatorTypeEnum.values());
		model.addAttribute("engineType", EngineTypeDriverEnum.values());
		
		return "/page_info/test";
	}
	
	@RequestMapping("list.su")
	public String list(Model model, String projectId)
	{
		List<PageInfo> pageInfoList = pageInfoMapper.getAllByProjectId(projectId);
		model.addAttribute("pageInfoList", pageInfoList);
		model.addAttribute("projectId", projectId);
		
		return "page_info_list";
	}
	
	@RequestMapping("/del")
	public String del(Model model, String id)
	{
		PageInfo pageInfo = pageInfoMapper.getById(id);
		pageInfoMapper.delById(id);
		
		return "redirect:/page_info/list.su?projectId=" + pageInfo.getProjectId();
	}
	
	@RequestMapping("test.su")
	public void test(Model model, @RequestParam(defaultValue = "qwe") String id)
	{
		PageInfo pageInfo = pageInfoMapper.getById(id);
		model.addAttribute("pageInfo", pageInfo);
		
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
				ByteArrayInputStream input = new ByteArrayInputStream(content.getBytes());
				
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
			model.addAttribute("fieldType", FieldTypeEnum.values());
			model.addAttribute("strategyType", StrategyEnum.values());
			model.addAttribute("locatorType", PageFieldLocatorTypeEnum.values());
			model.addAttribute("engineType", EngineTypeDriverEnum.values());
		}
		catch (JAXBException e)
		{
			e.printStackTrace();
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
			
			ByteArrayInputStream input = new ByteArrayInputStream(content.getBytes());
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
	}
	
	@RequestMapping("addPage.su")
	public String addPage(Model model, String id)
	{
		PageInfo pageInfo = pageInfoMapper.getById(id);
		
		try
		{
			JAXBContext context = JAXBContext.newInstance(Autotest.class);
			Unmarshaller unmarshaller = context.createUnmarshaller();
	        
			String content = pageInfo.getContent();
			Autotest autotest;
			if(content != null)
			{
				ByteArrayInputStream input = new ByteArrayInputStream(content.getBytes());
				autotest = (Autotest) unmarshaller.unmarshal(input);
			}
			else
			{
				autotest = initAutotest();
			}
			
			
			PageType pageType = new PageType();
			pageType.setClazz("PageStuff");
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
		
		return "page_info/test";
	}
	
	@RequestMapping(value = "updatePage.su")
	public String updatePage(Model model, PageInfo pageInfo)
	{
		JAXBContext context;
		try
		{
			Autotest autotest = pageInfo.getAutotest();
			
			context = JAXBContext.newInstance(Autotest.class);
			Marshaller marshaller = context.createMarshaller();
			
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			marshaller.marshal(autotest, out);

			if(StringUtils.isNotBlank(pageInfo.getId()))
			{
				pageInfo.setContent(out.toString());
				
				pageInfoMapper.update(pageInfo);
			}
			else
			{
				pageInfo.setContent(out.toString());
				pageInfoMapper.save(pageInfo);
			}
		}
		catch (JAXBException e)
		{
			e.printStackTrace();
		}
		
		return "redirect:/page_info/list.su";
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
			
			ByteArrayInputStream input = new ByteArrayInputStream(content.getBytes());
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
			
			pageInfo.setContent(new String(out.toByteArray()));
			
			pageInfoMapper.update(pageInfo);
		}
		catch(Exception e)
		{
		}
		
		return "redirect:/page_info/test.su";
	}
	
	private Autotest initAutotest()
	{
		Autotest autotest = new Autotest();
		Autotest.Pages pages = new Autotest.Pages();
		autotest.setPages(pages);
		
		PageType pageType = new PageType();
		pageType.setClazz(String.valueOf(System.currentTimeMillis()));
		pages.getPage().add(pageType);
		
		PageFieldType pageFieldType = new PageFieldType();
		pageFieldType.setName(String.valueOf(System.currentTimeMillis()));
		pageType.getField().add(pageFieldType);

		return autotest;
	}
}
