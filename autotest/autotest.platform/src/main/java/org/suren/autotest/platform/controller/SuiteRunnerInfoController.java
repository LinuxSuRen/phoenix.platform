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
import java.util.List;

import javax.servlet.ServletContext;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.suren.autotest.platform.mapping.SuiteRunnerInfoMapper;
import org.suren.autotest.platform.model.SuiteRunnerInfo;
import org.suren.autotest.platform.schemas.suite.ActionEnum;
import org.suren.autotest.platform.schemas.suite.ActionType;
import org.suren.autotest.platform.schemas.suite.ErrorLinesEnum;
import org.suren.autotest.platform.schemas.suite.LackLinesEnum;
import org.suren.autotest.platform.schemas.suite.Suite;
import org.suren.autotest.platform.schemas.suite.SuitePageType;
import org.suren.autotest.web.framework.core.suite.SuiteRunner;
import org.suren.autotest.web.framework.util.StringUtils;

/**
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
	public String suiteRunnerInfoEdit(Model model, String id)
	{
		SuiteRunnerInfo suiteRunnerInfo = suiteRunnerInfoMapper.getById(id);
		
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
				ByteArrayInputStream input = new ByteArrayInputStream(content.getBytes());
				suite = (Suite) unmarshaller.unmarshal(input);
			}
			
			suiteRunnerInfo.setSuite(suite);
		}
		catch (JAXBException e)
		{
			e.printStackTrace();
		}
		
		addAttr(model, suiteRunnerInfo);
		
		return "suite_runner_info/edit";
	}

	@RequestMapping("add.su")
	public String suiteRunnerInfoAdd(Model model, String projectId)
	{
		return "suite_runner_info/edit";
	}
	
	@RequestMapping("save.su")
	public String suiteRunnerInfoSave(Model model, SuiteRunnerInfo suiteRunnerInfo)
	{
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
				suiteRunnerInfoMapper.save(suiteRunnerInfo);
			}
		}
		catch (JAXBException e)
		{
			e.printStackTrace();
		}

		return "redirect:/suite_runner_info/list.su?projectId=" + suiteRunnerInfo.getProjectId();
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
	
	@RequestMapping("run.su")
	public String suiteRunnerToRun(Model model, String id)
	{
		SuiteRunnerInfo suiteRunnerInfo = suiteRunnerInfoMapper.getById(id);
		File runnerFile = new File(servletContext.getRealPath("/deploy"), suiteRunnerInfo.getName() + ".xml");

		SuiteRunner.runFromFile(runnerFile);
		
		return "redirect:/suite_runner_info/list.su?projectId=" + suiteRunnerInfo.getProjectId();
	}
	
	public static void main(String[] args)
	{
		SuiteRunner.runFromFile(new File("D:/Program Files (x86)/Gboat-Toolkit-Suit/workspace_surenpi/.metadata/.plugins/org.eclipse.wst.server.core/tmp0/wtpwebapps/autotest.platform/deploy/项目报建测试.xml"));
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
