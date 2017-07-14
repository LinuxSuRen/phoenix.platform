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

package org.suren.autotest.platform.controller.api;

import java.io.ByteArrayOutputStream;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.suren.autotest.platform.mapping.SuiteRunnerInfoMapper;
import org.suren.autotest.platform.model.SuiteRunnerInfo;
import org.suren.autotest.platform.schemas.suite.Suite;
import org.suren.autotest.web.framework.util.StringUtils;

/**
 * 测试套件管理
 * @author suren
 * @date 2017年1月23日 上午7:38:33
 */
@RestController
@RequestMapping("/api/suite_runner_infos")
public class SuiteRunnerInfoApiController
{
	@Autowired
	private SuiteRunnerInfoMapper suiteRunnerInfoMapper;
	
	@RequestMapping(value = "/{projectId}", method = RequestMethod.GET)
	public List<SuiteRunnerInfo> suiteRunnerInfoList(@PathVariable String projectId)
	{
		return suiteRunnerInfoMapper.getAllByProjectId(projectId);
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public SuiteRunnerInfo suiteRunnerInfoEdit(@RequestParam String id)
	{
		return suiteRunnerInfoMapper.getById(id);
	}
	
	@RequestMapping(method = RequestMethod.DELETE)
	public void suiteRunnerInfoDel(@RequestParam String id)
	{
		suiteRunnerInfoMapper.delById(id);
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public void suiteRunnerInfoSave(@RequestBody SuiteRunnerInfo suiteRunnerInfo)
	{
		Suite suite = suiteRunnerInfo.getSuite();
		
		try
		{
			JAXBContext context = JAXBContext.newInstance(Suite.class);
			Marshaller marshaller = context.createMarshaller();
			
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			marshaller.marshal(suite, out);
			
			suiteRunnerInfo.setContent(out.toString("UTF-8"));
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
		catch (UnsupportedEncodingException e)
		{
			e.printStackTrace();
		}
	}
}
