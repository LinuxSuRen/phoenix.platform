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
import javax.xml.bind.Unmarshaller;

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
import org.suren.autotest.platform.schemas.PageType;
import org.suren.autotest.platform.schemas.StrategyEnum;

/**
 * @author suren
 * @date 2017年1月17日 下午8:40:45
 */
@Controller
public class TestController
{
	@Autowired
	private UserMapper userMapper;
	@Autowired
	private PageInfoMapper pageInfoMapper;
	
	@RequestMapping("test.su")
	public void test(Model model, @RequestParam(defaultValue = "qwe") String id)
	{
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
			for(PageType pageT : pages.getPage())
			{
//				JAXBUtils.transform(pageT.getContent());
			}

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
	public String addPage(Model model, @RequestParam(defaultValue = "qwe") String id)
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
		
		return "test";
	}
	
	@RequestMapping(value = "updatePage.su")
	public String updatePage(Autotest autotest, Model model, @RequestParam(defaultValue = "qwe") String id)
	{
		JAXBContext context;
		try
		{
			context = JAXBContext.newInstance(Autotest.class);
			Marshaller marshaller = context.createMarshaller();
			
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			marshaller.marshal(autotest, out);
			
			PageInfo pageInfo = pageInfoMapper.getById(id);
			pageInfo.setContent(new String(out.toByteArray()));
			
			pageInfoMapper.update(pageInfo);
		}
		catch (JAXBException e)
		{
			e.printStackTrace();
		}
		
		return "redirect:/test.su";
	}
	
	@RequestMapping("delPage.su")
	public String delPage(@RequestParam(defaultValue = "qwe") String id, String pageName)
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
		
		return "redirect:/test.su";
	}
}
