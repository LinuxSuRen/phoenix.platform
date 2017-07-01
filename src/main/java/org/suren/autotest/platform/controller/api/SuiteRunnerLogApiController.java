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
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.suren.autotest.platform.mapping.SuiteRunnerLogMapper;
import org.suren.autotest.platform.model.SuiteRunnerLog;
import org.suren.autotest.web.framework.log.Image4SearchLog;

/**
 * 运行日志
 * @author suren
 * @date 2017年2月3日 下午6:54:30
 */
@RestController
@RequestMapping("/api/suite_runner_log")
public class SuiteRunnerLogApiController
{
	@Autowired
	private SuiteRunnerLogMapper suiteRunnerLogMapper;
	@Autowired
	private Image4SearchLog image4SearchLog;
	
	@RequestMapping("list")
	public String list(String runnerId, Model model)
	{
		List<SuiteRunnerLog> logList = suiteRunnerLogMapper.findByRunnerId(runnerId);
		
		for(SuiteRunnerLog log : logList)
		{
			if(log.getMessage() != null && log.getMessage().length() > 20)
			{
				log.setMessage(log.getMessage().substring(0, 20));
			}
		}
		
		model.addAttribute("logList", logList);
		
		return "suite_runner_log/suite_runner_log_list";
	}
	
	@RequestMapping("detail")
	public String detail(String id, Model model)
	{
		SuiteRunnerLog log = suiteRunnerLogMapper.findById(id);
		
		model.addAttribute("log", log);
		
		return "suite_runner_log/suite_runner_log_detail";
	}
	
	@RequestMapping("gifDetail")
	public ResponseEntity<byte[]> gifDetail(String id)
	{
		File file = image4SearchLog.getOutputFile();
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		
		try(InputStream input = new FileInputStream(new File(file, id + ".gif")))
		{
			IOUtils.copy(input, out);
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.IMAGE_GIF);
		
		return new ResponseEntity<byte[]>(out.toByteArray(), headers, HttpStatus.CREATED);
	}
	
	@RequestMapping("del")
	public String del(String id)
	{
		SuiteRunnerLog log = suiteRunnerLogMapper.findById(id);
		String suiteRunnerId = log.getSuiteRunnerInfoId();
		
		suiteRunnerLogMapper.delById(id);
		
		return "redirect:/suite_runner_log/list.su?runnerId=" + suiteRunnerId;
	}
}
