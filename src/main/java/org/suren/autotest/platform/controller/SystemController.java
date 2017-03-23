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

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.suren.autotest.platform.mapping.OptionsMapper;
import org.suren.autotest.platform.model.Options;
import org.suren.autotest.platform.model.SystemConf;
import org.suren.autotest.web.framework.autoit3.AutoItCmd;
import org.suren.autotest.web.framework.log.Image4SearchLog;
import org.suren.autotest.web.framework.log.LoggerConstants;
import org.suren.autotest.web.framework.selenium.SeleniumEngine;
import org.suren.autotest.web.framework.util.EncryptorUtil;
import org.suren.autotest.web.framework.util.StringUtils;

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
	@Autowired
	private OptionsMapper optionsMapper;
	@Autowired
	private SeleniumEngine seleniumEngine;

	@RequestMapping("edit")
	public String edit(Model model, HttpServletRequest request)
	{
		File file = image4SearchLog.getOutputFile();
		
		SystemConf sysConf = new SystemConf();
		sysConf.setGifPath(file.getAbsolutePath());
		sysConf.setSecurtyKey(EncryptorUtil.getSecretKey());
		
		Options options = optionsMapper.getByKey("attachRoot");
		if(options != null)
		{
			sysConf.setAttachRoot(options.getOptValue());
		}
		
		if(StringUtils.isBlank(sysConf.getAttachRoot()))
		{
			sysConf.setAttachRoot(request.getServletContext().getRealPath("/"));
		}
		
		try(InputStream autoItInput = SystemConf.class.getResourceAsStream("/" + AutoItCmd.AUTO_IT3_PATH))
		{
			Properties pro = new Properties();
			pro.load(autoItInput);
			sysConf.setAutoIt(pro.getProperty(AutoItCmd.PRO_PATH));
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		
		//浏览器配置
		seleniumEngine.setDriverStr("chrome");
		seleniumEngine.initConfig();
		String chromeVer = seleniumEngine.getChromeVer();
		sysConf.setChromeVer(chromeVer);
		
		model.addAttribute("sysConf", sysConf);
		
		return "sys/sys_edit";
	}
	
	@RequestMapping("save")
	public String save(Model model, SystemConf sysConf)
	{
		URL url = this.getClass().getClassLoader().getResource(LoggerConstants.IMG_LOG_CONF_FILE_NAME);
		if(url != null)
		{
			try(InputStream input = url.openStream();
					OutputStream out = new FileOutputStream(URLDecoder.decode(url.getFile(), "utf-8")))
			{
				Properties pro = new Properties();
				pro.load(input);
				
				pro.setProperty(LoggerConstants.IMG_LOG_DIR, sysConf.getGifPath());
				
				pro.store(out, "");
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
		}
		
		url = this.getClass().getClassLoader().getResource(EncryptorUtil.ENCRYPT_FILE);
		if(url != null)
		{
			try(InputStream input = url.openStream();
					OutputStream out = new FileOutputStream(URLDecoder.decode(url.getFile(), "utf-8")))
			{
				Properties pro = new Properties();
				pro.load(input);
				
				pro.setProperty(EncryptorUtil.ENCRYPT_KEY, sysConf.getSecurtyKey());
				
				pro.store(out, "");
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
		}
		
		Options options = optionsMapper.getByKey("attachRoot");
		if(options == null)
		{
			options = new Options();
			options.setOptKey("attachRoot");
			options.setOptValue(sysConf.getAttachRoot());
			optionsMapper.save(options);
		}
		else
		{
			options.setOptValue(sysConf.getAttachRoot());
			optionsMapper.update(options);
		}
		
		URL autoItUrl = SystemConf.class.getResource("/" + AutoItCmd.AUTO_IT3_PATH);
		if(autoItUrl != null)
		{
			try(InputStream autoItInput = autoItUrl.openStream();
					OutputStream out = new FileOutputStream(
					new File(URLDecoder.decode(autoItUrl.getFile(), "utf-8"))))
			{
				Properties pro = new Properties();
				pro.load(autoItInput);
				pro.setProperty(AutoItCmd.PRO_PATH, sysConf.getAutoIt());
				
				pro.store(out, "");
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
		}
		
		seleniumEngine.setDriverStr("chrome");
		seleniumEngine.initConfig();
		seleniumEngine.setChromeVer(sysConf.getChromeVer());
		seleniumEngine.storePro();
		
		return "redirect:/sys/edit.su";
	}
}
