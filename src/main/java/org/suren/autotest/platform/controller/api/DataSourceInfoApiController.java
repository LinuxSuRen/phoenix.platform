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
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Date;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.suren.autotest.platform.entity.DataSourceInfo;
import org.suren.autotest.platform.entity.PageInfo;
import org.suren.autotest.platform.mapping.DataSourceInfoMapper;
import org.suren.autotest.platform.mapping.PageInfoMapper;
import org.suren.autotest.platform.schemas.datasource.DataSources;
import org.suren.autotest.platform.util.DomUtils;
import org.suren.autotest.platform.util.JAXBUtils;
import org.suren.autotest.web.framework.util.StringUtils;

/**
 * 数据源管理
 * @author suren
 * @date 2017年1月22日 下午6:31:17
 */
@RestController
@RequestMapping("/api/data_source_infos")
public class DataSourceInfoApiController
{
	@Autowired
	private DataSourceInfoMapper dataSourceInfoMapper;
	@Autowired
	private PageInfoMapper pageInfoMapper;

	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public String createDataSourceInfo(@RequestParam String pageId)
	{
		PageInfo pageInfo = pageInfoMapper.getById(pageId);
		if(pageInfo != null)
		{
			String pageName = pageInfo.getName();

			DataSourceInfo dataSourceInfo = new DataSourceInfo();
			dataSourceInfo.setPageId(pageId);
			dataSourceInfo.setProjectId(pageInfo.getProjectId());
			dataSourceInfo.setCreateTime(new Date());
			dataSourceInfo.setName(pageName); //TODO 这里要考虑不能重复的问题

			dataSourceInfoMapper.save(dataSourceInfo);

			return dataSourceInfo.getId();
		}

		return null;
	}
}
