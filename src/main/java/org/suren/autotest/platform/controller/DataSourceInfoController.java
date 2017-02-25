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
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.suren.autotest.platform.mapping.DataSourceInfoMapper;
import org.suren.autotest.platform.model.DataSourceInfo;
import org.suren.autotest.platform.model.SuiteRunnerInfo;
import org.suren.autotest.platform.schemas.datasource.DataSourceFieldTypeEnum;
import org.suren.autotest.platform.schemas.datasource.DataSourcePageFieldType;
import org.suren.autotest.platform.schemas.datasource.DataSourcePageType;
import org.suren.autotest.platform.schemas.datasource.DataSources;
import org.suren.autotest.platform.schemas.datasource.DataSources.DataSource;
import org.suren.autotest.platform.schemas.datasource.DataTypeEnum;
import org.suren.autotest.platform.util.DomUtils;
import org.suren.autotest.platform.util.JAXBUtils;
import org.suren.autotest.web.framework.util.StringUtils;

/**
 * 数据源管理
 * @author suren
 * @date 2017年1月22日 下午6:31:17
 */
@Controller
@RequestMapping("data_source_info")
public class DataSourceInfoController
{
	@Autowired
	private DataSourceInfoMapper dataSourceInfoMapper;
	
	@RequestMapping("list.su")
	public String dataSourceInfoList(Model model, String projectId)
	{
		List<DataSourceInfo> dataSourceInfoList = dataSourceInfoMapper.getAllByProjectId(projectId);
		model.addAttribute("dataSourceInfoList", dataSourceInfoList);
		model.addAttribute("projectId", projectId);
		
		return "data_source_info/data_source_info_list";
	}
	
	@RequestMapping("add.su")
	public String dataSourceInfoAdd(String projectId, Model model)
	{
		DataSourceInfo dataSourceInfo = new DataSourceInfo();
		dataSourceInfo.setProjectId(projectId);
		model.addAttribute("dataSourceInfo", dataSourceInfo);
		initEnums(model);
		
		DataSources dataSources = new DataSources();
		dataSourceInfo.setDataSources(dataSources);
		
		DataSource dataSource = new DataSource();
		dataSources.getDataSource().add(dataSource);
		dataSource.setPageClass("data_clazz");
		
		DataSourcePageType dataSourcePageType = new DataSourcePageType();
		dataSource.getPage().add(dataSourcePageType);
		
		DataSourcePageFieldType dataSourcePageFieldType = new DataSourcePageFieldType();
		dataSourcePageType.getField().add(dataSourcePageFieldType);
		dataSourcePageFieldType.setName("test");

		return "data_source_info/data_source_info_edit";
	}
	
	@RequestMapping("edit.su")
	public String dataSourceInfoEdit(Model model, DataSourceInfo dataSourceInfo)
	{
		String resultPath = "data_source_info/data_source_info_edit";
		
		String id = dataSourceInfo.getId();
		int tabIndex = dataSourceInfo.getTabIndex();
		dataSourceInfo = dataSourceInfoMapper.getById(id);
		if(dataSourceInfo == null)
		{
			return resultPath;
		}
		
		dataSourceInfo.setTabIndex(tabIndex);
		model.addAttribute("dataSourceInfo", dataSourceInfo);
		initEnums(model);

		try
		{
			JAXBContext context = JAXBContext.newInstance(DataSources.class);
			Unmarshaller unmarshaller = context.createUnmarshaller();
			
			DataSources dataSources = null;
			String content = dataSourceInfo.getContent();
			if(StringUtils.isBlank(content))
			{
			}
			else
			{
				ByteArrayInputStream input = new ByteArrayInputStream(dataSourceInfo.getContent().getBytes("utf-8"));
				dataSources = (DataSources) unmarshaller.unmarshal(input);
			}
			
			dataSourceInfo.setDataSources(dataSources);
		}
		catch (JAXBException e)
		{
			e.printStackTrace();
		}
		catch (UnsupportedEncodingException e)
		{
			e.printStackTrace();
		}
		
		return resultPath;
	}
	
	@ResponseBody
	@RequestMapping("save.su")
	public DataSourceInfo dataSourceInfoSave(Model model, DataSourceInfo dataSourceInfo)
	{
		DataSources dataSources = dataSourceInfo.getDataSources();
		
		try
		{
			JAXBContext context = JAXBContext.newInstance(DataSources.class);
			Marshaller marshaller = context.createMarshaller();
			
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			marshaller.marshal(dataSources, out);
			
			dataSourceInfo.setContent(out.toString());
			if(StringUtils.isNotBlank(dataSourceInfo.getId()))
			{
				dataSourceInfoMapper.update(dataSourceInfo);
			}
			else
			{
				dataSourceInfo.setCreateTime(new Date());
				dataSourceInfoMapper.save(dataSourceInfo);
			}
		}
		catch (JAXBException e)
		{
			e.printStackTrace();
		}

		return dataSourceInfo;
	}
	
	@RequestMapping("import.su")
	public String dataSourceInfoImport(Model model, MultipartFile file, String projectId)
	{
		String originalFileName = file.getOriginalFilename();
		if(originalFileName.endsWith(".xml"))
		{
			originalFileName = originalFileName.substring(0, originalFileName.length() - ".xml".length());
		}

		DataSourceInfo dataSourceInfo = new DataSourceInfo();
		dataSourceInfo.setProjectId(projectId);
		dataSourceInfo.setName(originalFileName);
		dataSourceInfo.setCreateTime(new Date());
		
		model.addAttribute("dataSourceInfo", dataSourceInfo);
		initEnums(model);
		
		try
		{
			JAXBContext context = JAXBContext.newInstance(DataSources.class);
			Unmarshaller unmarshaller = context.createUnmarshaller();

			DataSources dataSources = (DataSources) unmarshaller.unmarshal(file.getInputStream());
			
			JAXBUtils.clearEncryptData(dataSources);
			dataSourceInfo.setDataSources(dataSources);
		}
		catch (JAXBException e)
		{
			e.printStackTrace();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}

		return "/data_source_info/data_source_info_edit";
	}

	@RequestMapping("del.su")
	public String dataSourceInfoDel(String id)
	{
		DataSourceInfo dataSourceInfo = dataSourceInfoMapper.getById(id);
		
		dataSourceInfoMapper.delById(id);
		return "redirect:/data_source_info/list.su?projectId=" + dataSourceInfo.getProjectId();
	}
	
	@RequestMapping(value = "/download.su")
	public ResponseEntity<byte[]> download(String id)
	{
		DataSourceInfo sataSourceInfo = dataSourceInfoMapper.getById(id);
		
		String content = sataSourceInfo.getContent();
		content = (StringUtils.isBlank(content) ? "" : DomUtils.format(content));

		String fileName = sataSourceInfo.getName();
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
		return dataSourceInfoMapper.getCountByProjectId(projectId);
	}
	
	/**
	 * @param model
	 */
	private void initEnums(Model model)
	{
		model.addAttribute("dataType", DataTypeEnum.values());
		model.addAttribute("fieldType", DataSourceFieldTypeEnum.values());
	}
}
