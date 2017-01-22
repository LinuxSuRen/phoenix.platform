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
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.suren.autotest.platform.mapping.DataSourceInfoMapper;
import org.suren.autotest.platform.model.DataSourceInfo;
import org.suren.autotest.platform.schemas.datasource.DataSourceFieldTypeEnum;
import org.suren.autotest.platform.schemas.datasource.DataSources;
import org.suren.autotest.platform.schemas.datasource.DataTypeEnum;

/**
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
		List<DataSourceInfo> dataSourceInfoList = dataSourceInfoMapper.getAll();
		model.addAttribute("dataSourceInfoList", dataSourceInfoList);
		model.addAttribute("projectId", projectId);
		
		return "data_source_info/data_source_info_list";
	}
	
	@RequestMapping("edit.su")
	public String dataSourceInfoEdit(Model model, String id)
	{
		DataSourceInfo dataSourceInfo = dataSourceInfoMapper.getById(id);
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
				ByteArrayInputStream input = new ByteArrayInputStream(dataSourceInfo.getContent().getBytes());
				dataSources = (DataSources) unmarshaller.unmarshal(input);
			}
			
			dataSourceInfo.setDataSources(dataSources);
		}
		catch (JAXBException e)
		{
			e.printStackTrace();
		}
		
		return "data_source_info/data_source_info_edit";
	}
	
	@RequestMapping("save.su")
	public String dataSourceInfoSave(Model model, DataSourceInfo dataSourceInfo)
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
				dataSourceInfoMapper.save(dataSourceInfo);
			}
		}
		catch (JAXBException e)
		{
			e.printStackTrace();
		}

		return "redirect:/data_source_info/list.su";
	}
	
	@RequestMapping("import.su")
	public String dataSourceInfoImport(Model model, MultipartFile file, String projectId)
	{
		String originalFileName = file.getOriginalFilename();

		DataSourceInfo dataSourceInfo = new DataSourceInfo();
		dataSourceInfo.setProjectId(projectId);
		dataSourceInfo.setName(originalFileName);
		
		model.addAttribute("dataSourceInfo", dataSourceInfo);
		initEnums(model);
		
		try
		{
			JAXBContext context = JAXBContext.newInstance(DataSources.class);
			Unmarshaller unmarshaller = context.createUnmarshaller();

			DataSources dataSources = (DataSources) unmarshaller.unmarshal(file.getInputStream());
			
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
	public String dataSourceInfoDel()
	{
		return "data_source_info_list";
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
