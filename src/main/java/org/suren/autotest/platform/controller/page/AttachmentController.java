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

package org.suren.autotest.platform.controller.page;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.poi.util.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.suren.autotest.platform.mapping.AttachConfigMapper;
import org.suren.autotest.platform.mapping.AttachmentMapper;
import org.suren.autotest.platform.mapping.OptionsMapper;
import org.suren.autotest.platform.model.AttachConfig;
import org.suren.autotest.platform.model.Attachment;
import org.suren.autotest.platform.model.Options;
import org.suren.autotest.platform.security.UserDetail;

/**
 * @author suren
 * @date 2017年2月14日 上午8:20:47
 */
@Controller
@RequestMapping("attachment")
public class AttachmentController
{
	@Autowired
	private AttachmentMapper attachmentMapper;
	@Autowired
	private AttachConfigMapper attachConfigMapper;
	@Autowired
	private OptionsMapper optionsMapper;
	
	@RequestMapping("upload")
	public String upload(MultipartFile file, Attachment attachment,
			@RequestParam(value = "redirectPath") String redirectPath)
	{
		UserDetail userDetail = (UserDetail) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String ownerId = userDetail.getId();
		
		attachment.setOwnerId(ownerId);
		attachment.setCreateTime(new Date());
		
		Options options = optionsMapper.getByKey("attachRoot");
		if(options != null)
		{
			String attachRoot = options.getOptValue();
			attachment.setRelativePath(attachRoot);

			try(InputStream input = file.getInputStream();
					OutputStream output = new FileOutputStream(new File(attachRoot, attachment.getFileName())))
			{
				IOUtils.copy(input, output);
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
		}
		
		String configId;
		List<AttachConfig> attachConfigList = attachConfigMapper.getAll();
		if(CollectionUtils.isEmpty(attachConfigList))
		{
			AttachConfig config = new AttachConfig();
			config.setName("for demo");
			attachConfigMapper.save(config);
			configId = config.getId();
		}
		else
		{
			configId = attachConfigList.get(0).getId();
		}
		attachment.setConfigId(configId);
		
		attachmentMapper.save(attachment);
		
		return "redirect:" + redirectPath;
	}
	
	/**
	 * 删除附件
	 * @param id
	 * @return
	 */
	@RequestMapping("del")
	@ResponseBody
	public boolean del(String id)
	{
		attachmentMapper.delById(id);
		return true;
	}
	
	@RequestMapping("count")
	@ResponseBody
	public int count(String belongId)
	{
		return attachmentMapper.countByBelongId(belongId);
	}
}
