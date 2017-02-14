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
import org.suren.autotest.platform.mapping.AttachConfigMapper;
import org.suren.autotest.platform.model.AttachConfig;
import org.suren.autotest.web.framework.util.StringUtils;

/**
 * @author suren
 * @date 2017年2月14日 上午8:20:38
 */
@Controller
@RequestMapping("attach_config")
public class AttachConfigController
{
	@Autowired
	private AttachConfigMapper attachConfigMapper;
	
	@RequestMapping("list")
	public String list(Model model)
	{
		List<AttachConfig> attachConfigList = attachConfigMapper.getAll();
		model.addAttribute("attachConfigList", attachConfigList);
		
		return "attach_config/attach_config_list";
	}
	
	@RequestMapping("edit")
	public String edit(AttachConfig attachConfig, Model model)
	{
		if(StringUtils.isNotBlank(attachConfig.getId()))
		{
			attachConfig = attachConfigMapper.getById(attachConfig.getId());
			
			model.addAttribute("attachConfig", attachConfig);
		}
		
		return "attach_config/attach_config_edit";
	}
	
	@RequestMapping("save")
	public String save(AttachConfig attachConfig)
	{
		attachConfigMapper.save(attachConfig);
		
		return "redirect:/attach_config/list.su";
	}
	
	@RequestMapping("del")
	public String del(String id)
	{
		attachConfigMapper.delById(id);
		
		return "redirect:/attach_config/list.su";
	}
}
