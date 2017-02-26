/*
 * Copyright 2002-2007 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.suren.autotest.platform.controller;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.suren.autotest.platform.constants.ControllerConstants;
import org.suren.autotest.platform.mapping.SuiteRunnerInfoMapper;
import org.suren.autotest.platform.mapping.SuiteRunnerLogMapper;
import org.suren.autotest.platform.model.DebugRunInfo;
import org.suren.autotest.platform.model.SuiteRunnerInfo;
import org.suren.autotest.platform.model.SuiteRunnerLog;
import org.suren.autotest.platform.schemas.suite.ActionEnum;
import org.suren.autotest.platform.schemas.suite.ActionType;
import org.suren.autotest.platform.schemas.suite.ErrorLinesEnum;
import org.suren.autotest.platform.schemas.suite.LackLinesEnum;
import org.suren.autotest.platform.schemas.suite.Suite;
import org.suren.autotest.platform.schemas.suite.SuitePageType;
import org.suren.autotest.platform.security.UserDetail;
import org.suren.autotest.platform.util.DomUtils;
import org.suren.autotest.web.framework.core.SuiteProgressInfo;
import org.suren.autotest.web.framework.core.suite.SuiteRunner;
import org.suren.autotest.web.framework.util.StringUtils;

/**
 * 测试套件管理
 * @author suren
 * @date 2017年1月23日 上午7:38:33
 */
@Controller
@RequestMapping("suite_runner_info")
public class SuiteRunnerInfoController
{
	@Autowired
	private SuiteRunnerInfoMapper suiteRunnerInfoMapper;
	@Autowired
	private SuiteRunnerLogMapper suiteRunnerLogMapper;

	@Autowired
	private ServletContext servletContext;
	
	@RequestMapping("list.su")
	public String suiteRunnerInfoList(Model model, String projectId)
	{
		List<SuiteRunnerInfo> suiteRunnerInfoList = suiteRunnerInfoMapper.getAllByProjectId(projectId);
		addAttr(model, suiteRunnerInfoList);
		model.addAttribute("projectId", projectId);
		
		return "suite_runner_info/list";
	}
	
	@RequestMapping("edit.su")
	public String suiteRunnerInfoEdit(Model model, SuiteRunnerInfo suiteRunnerInfo)
	{
		String id = suiteRunnerInfo.getId();
		int tabIndex = suiteRunnerInfo.getTabIndex();
		suiteRunnerInfo = suiteRunnerInfoMapper.getById(id);
		suiteRunnerInfo.setTabIndex(tabIndex);
		
		try
		{
			JAXBContext context = JAXBContext.newInstance(Suite.class);
			Unmarshaller unmarshaller = context.createUnmarshaller();
			
			String content = suiteRunnerInfo.getContent();
			Suite suite;
			if(StringUtils.isBlank(content))
			{
				suite = initSuite();
			}
			else
			{
				ByteArrayInputStream input = new ByteArrayInputStream(content.getBytes("utf-8"));
				suite = (Suite) unmarshaller.unmarshal(input);
			}
			
			suiteRunnerInfo.setSuite(suite);
		}
		catch (JAXBException e)
		{
			e.printStackTrace();
		}
		catch (UnsupportedEncodingException e)
		{
			e.printStackTrace();
		}
		
		addAttr(model, suiteRunnerInfo);
		
		return "suite_runner_info/edit";
	}

	@RequestMapping("add.su")
	public String suiteRunnerInfoAdd(Model model, String projectId)
	{
		SuiteRunnerInfo suiteRunnerInfo = new SuiteRunnerInfo();
		suiteRunnerInfo.setProjectId(projectId);
		
		addAttr(model, suiteRunnerInfo);
		
		return "suite_runner_info/edit";
	}

	@RequestMapping("addPage.su")
	public String suiteRunnerInfoPageAdd(Model model, SuiteRunnerInfo suiteRunnerInfo)
	{
		String resultPath = "suite_runner_info/edit";
		
		suiteRunnerInfo = suiteRunnerInfoMapper.getById(suiteRunnerInfo.getId());
		if(suiteRunnerInfo == null)
		{
			return resultPath;
		}
		
		try
		{
			JAXBContext context = JAXBContext.newInstance(Suite.class);
			Unmarshaller unmarshaller = context.createUnmarshaller();
			
			String content = suiteRunnerInfo.getContent();
			Suite suite;
			if(StringUtils.isBlank(content))
			{
				suite = initSuite();
			}
			else
			{
				ByteArrayInputStream input = new ByteArrayInputStream(content.getBytes("utf-8"));
				suite = (Suite) unmarshaller.unmarshal(input);
			}
			
			suiteRunnerInfo.setSuite(suite);
		}
		catch (JAXBException e)
		{
			e.printStackTrace();
		}
		catch (UnsupportedEncodingException e)
		{
			e.printStackTrace();
		}
		
		SuitePageType suitePageType = new SuitePageType();
		suitePageType.setClazz("dsfsfd");
		suiteRunnerInfo.getSuite().getPage().add(suitePageType);
		
		addAttr(model, suiteRunnerInfo);
		
		return resultPath;
	}
	
	@ResponseBody
	@RequestMapping("save.su")
	public SuiteRunnerInfo suiteRunnerInfoSave(Model model, SuiteRunnerInfo suiteRunnerInfo)
	{
		int tabIndex = suiteRunnerInfo.getTabIndex();
		Suite suite = suiteRunnerInfo.getSuite();
		
		try
		{
			JAXBContext context = JAXBContext.newInstance(Suite.class);
			Marshaller marshaller = context.createMarshaller();
			
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			marshaller.marshal(suite, out);
			
			suiteRunnerInfo.setContent(out.toString());
			if(StringUtils.isNotBlank(suiteRunnerInfo.getId()))
			{
				suiteRunnerInfoMapper.update(suiteRunnerInfo);
			}
			else
			{
				suiteRunnerInfo.setCreateTime(new Date());
				suiteRunnerInfoMapper.save(suiteRunnerInfo);
			}
		}
		catch (JAXBException e)
		{
			e.printStackTrace();
		}
		
		suiteRunnerInfo = suiteRunnerInfoMapper.getById(suiteRunnerInfo.getId());
		suiteRunnerInfo.setTabIndex(tabIndex);

		return suiteRunnerInfo;
	}
	
	@RequestMapping("import.su")
	public String suiteRunnerInfoImport(Model model, MultipartFile file,
			String projectId)
	{
		String originalFileName = file.getOriginalFilename();
		if(originalFileName.endsWith(".xml"))
		{
			originalFileName = originalFileName.substring(0, originalFileName.length() - ".xml".length());
		}
		
		SuiteRunnerInfo suiteRunnerInfo = new SuiteRunnerInfo();
		suiteRunnerInfo.setProjectId(projectId);
		suiteRunnerInfo.setName(originalFileName);
		
		addAttr(model, suiteRunnerInfo);
		
		try
		{
			JAXBContext context = JAXBContext.newInstance(Suite.class);
			Unmarshaller unmarshaller = context.createUnmarshaller();
			
			Suite suite = (Suite) unmarshaller.unmarshal(file.getInputStream());
			suiteRunnerInfo.setSuite(suite);
		}
		catch (JAXBException e)
		{
			e.printStackTrace();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		
		return "suite_runner_info/edit";
	}
	
	@RequestMapping("del.su")
	public String suiteRunnerInfoDel(Model model, String id)
	{
		SuiteRunnerInfo suiteRunnerInfo = suiteRunnerInfoMapper.getById(id);
		
		suiteRunnerInfoMapper.delById(id);
		return "redirect:/suite_runner_info/list.su?projectId=" + suiteRunnerInfo.getProjectId();
	}
	
	@ResponseBody
	@RequestMapping("run.su")
	public SuiteRunnerLog suiteRunnerToRun(HttpServletRequest request, Model model, DebugRunInfo debugRunInfo)
	{
		String id = debugRunInfo.getId();
		
		Date beginTime = new Date();
		
		//设置进度信息到session中
		SuiteProgressInfo suiteProgressInfo = null;
		String progressKey = request.getParameter(ControllerConstants.PROGRESS_KEY);
		if(StringUtils.isNotBlank(progressKey))
		{
			suiteProgressInfo = new SuiteProgressInfo();
			
			
			request.getSession().setAttribute(ControllerConstants.PROGRESS_PREFIX + progressKey, suiteProgressInfo);
		}
		
		//预备运行日志信息
		SuiteRunnerLog suiteRunnerLog = new SuiteRunnerLog();
		suiteRunnerLog.setMessage("");
		suiteRunnerLog.setBeginTime(beginTime);
		suiteRunnerLog.setEndTime(new Date());
		suiteRunnerLog.setSuiteRunnerInfoId(id);
		suiteRunnerLog.setRemark(debugRunInfo.getRemark());
		
		//用户信息
		UserDetail userDetail = (UserDetail) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String ownerId = userDetail.getId();
		suiteRunnerLog.setTriggerUserId(ownerId);
		
		SuiteRunnerInfo suiteRunnerInfo = suiteRunnerInfoMapper.getById(id);
		File runnerFile = new File(servletContext.getRealPath("/deploy"), suiteRunnerInfo.getProjectId() + "/"
				+ "/" + ownerId + "/" + suiteRunnerInfo.getName() + ".xml");

		try
		{
			suiteRunnerLogMapper.save(suiteRunnerLog);
			suiteProgressInfo.setIdentify(suiteRunnerLog.getId());
			
			new SuiteRunner(suiteProgressInfo).runFromFile(runnerFile);
		}
		catch(Exception e)
		{
			suiteRunnerLog.setMessage(e.getMessage());
			
			if(suiteProgressInfo != null)
			{
				suiteProgressInfo.setInfo(String.format("套件[%s]运行过程中发生错误！", suiteRunnerInfo.getName()));
			}
		}
		finally
		{
			suiteRunnerLog.setEndTime(new Date());
			
			suiteRunnerLogMapper.update(suiteRunnerLog);
		}
		
		return suiteRunnerLog;
	}
	
	@RequestMapping(value = "/download.su")
	public ResponseEntity<byte[]> download(String id)
	{
		SuiteRunnerInfo suiteRunnerInfo = suiteRunnerInfoMapper.getById(id);
		
		String content = suiteRunnerInfo.getContent();
		content = (StringUtils.isBlank(content) ? "" : DomUtils.format(content));

		String fileName = suiteRunnerInfo.getName();
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
	
	@ResponseBody
	@RequestMapping("count")
	public int getCountByProjectId(String projectId)
	{
		return suiteRunnerInfoMapper.getCountByProjectId(projectId);
	}
	
	private void addAttr(Model model, SuiteRunnerInfo suiteRunnerInfo)
	{
		model.addAttribute("suiteRunnerInfo", suiteRunnerInfo);
		initEnum(model);
	}
	
	private void addAttr(Model model, List<SuiteRunnerInfo> suiteRunnerInfoList)
	{
		model.addAttribute("suiteRunnerInfoList", suiteRunnerInfoList);
		initEnum(model);
	}
	
	private void initEnum(Model model)
	{
		model.addAttribute("actionType", ActionEnum.values());
		model.addAttribute("errorLinesType", ErrorLinesEnum.values());
		model.addAttribute("lackLinesType", LackLinesEnum.values());
	}
	
	/**
	 * @return
	 */
	private Suite initSuite()
	{
		Suite suite = new Suite();
		SuitePageType suitePageType = new SuitePageType();
		suite.getPage().add(suitePageType);
		suitePageType.setClazz("Clazz");
		
		ActionType actionType = new ActionType();
		suitePageType.getActions().getAction().add(actionType);
		actionType.setField("field");
		
		return suite;
	}
}
