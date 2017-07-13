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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.suren.autotest.platform.entity.PageField;
import org.suren.autotest.platform.mapping.PageFieldMapper;

import io.swagger.annotations.ApiOperation;

/**
 * @author suren
 * @date 2017年7月13日 上午10:30:54
 */
@RestController
@RequestMapping("/api/page_fields/")
public class PageFieldApiController
{
	@Autowired
	private PageFieldMapper pageFieldMapper;
	
	@ApiOperation("list")
	@RequestMapping(method = RequestMethod.GET)
	public List<PageField> list()
	{
		return pageFieldMapper.getAll();
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public String save(@RequestBody PageField pageField)
	{
		pageFieldMapper.save(pageField);
		return "";
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public void del(@PathVariable String id)
	{
		pageFieldMapper.delById(id);
	}
}
