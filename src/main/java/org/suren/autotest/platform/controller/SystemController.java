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

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLDecoder;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.suren.autotest.web.framework.log.Image4SearchLog;
import org.suren.autotest.web.framework.log.LoggerConstants;

/**
 * @author suren
 * @date 2017年2月3日 上午10:42:53
 */
@Controller
@RequestMapping("sys")
public class SystemController
{
	@Autowired
	private Image4SearchLog image4SearchLog;

	@RequestMapping("edit")
	public String edit(Model model)
	{
		File file = image4SearchLog.getOutputFile();
		
		model.addAttribute("file", file.getAbsolutePath());
		
		return "sys/sys_edit";
	}
	
	@RequestMapping("save")
	public String edit(Model model, String file)
	{
		URL url = this.getClass().getClassLoader().getResource(LoggerConstants.IMG_LOG_CONF_FILE_NAME);
		if(url != null)
		{
			try(InputStream input = url.openStream();
					OutputStream out = new FileOutputStream(URLDecoder.decode(url.getFile(), "utf-8")))
			{
				Properties pro = new Properties();
				pro.load(input);
				
				pro.setProperty(LoggerConstants.IMG_LOG_DIR, file);
				
				pro.store(out, "");
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
		}
		
		return "redirect:/sys/edit.su";
	}
}
