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

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.suren.autotest.platform.mapping.SuiteRunnerLogMapper;
import org.suren.autotest.platform.model.SuiteRunnerLog;

/**
 * 运行日志
 * @author suren
 * @date 2017年2月3日 下午6:54:30
 */
@RequestMapping("suite_runner_log")
@Controller
public class SuiteRunnerLogController
{
	@Autowired
	private SuiteRunnerLogMapper suiteRunnerLogMapper;
	
	@RequestMapping("list")
	public String list(String runnerId, Model model)
	{
		List<SuiteRunnerLog> logList = suiteRunnerLogMapper.findByRunnerId(runnerId);
		
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
}
