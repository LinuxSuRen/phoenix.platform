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

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.suren.autotest.platform.mapping.AttachConfigMapper;
import org.suren.autotest.platform.model.AttachConfig;

/**
 * @author suren
 * @date 2017年2月14日 上午8:20:38
 */
@RestController
@RequestMapping("/api/attach_configs")
public class AttachConfigApiController
{
	@Autowired
	private AttachConfigMapper attachConfigMapper;
	
	@RequestMapping(method = RequestMethod.GET)
	public List<AttachConfig> list()
	{
		return attachConfigMapper.getAll();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public AttachConfig edit(@PathVariable String id)
	{
		return attachConfigMapper.getById(id);
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public void save(AttachConfig attachConfig)
	{
		attachConfigMapper.save(attachConfig);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public void del(@PathVariable String id)
	{
		attachConfigMapper.delById(id);
	}
}
