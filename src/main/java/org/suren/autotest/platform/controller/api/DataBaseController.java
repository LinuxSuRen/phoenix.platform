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

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLDecoder;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.suren.autotest.platform.model.DataBase;
import org.suren.autotest.web.framework.util.StringUtils;

/**
 * 数据库初始化
 * @author suren
 * @date 2017年2月16日 下午7:40:37
 */
@Controller
@RequestMapping("data_base")
public class DataBaseController
{
	@RequestMapping("init_schema")
	public String initSchema(Model model)
	{
		DataBase dataBase = new DataBase();
		dataBase.setPort(3306);
		dataBase.setUrl("127.0.0.1");
		dataBase.setUsername("root");
		dataBase.setSchema("phoenix");
		dataBase.setDefaultSchema("mysql");
		model.addAttribute("dataBase", dataBase);
		
		return "data_base/init_schema";
	}
	
	@RequestMapping("create_schema")
	@ResponseBody
	public String createSchema(DataBase dataBase)
	{
		String url = dataBase.getUrl();
		int port = dataBase.getPort();
		String username = dataBase.getUsername();
		String password = dataBase.getPassword();
		
		try
		{
			Class.forName(dataBase.getDriver());
		}
		catch (ClassNotFoundException e)
		{
			return e.getMessage();
		}
		
		String connUrl = getJdbcUrl(dataBase.getDefaultSchema(), url, port);
		URL jdbcProUrl = DataBase.class.getClassLoader().getResource("jdbc.properties");
		try(InputStream input = DataBase.class.getClassLoader().getResourceAsStream("platform.sql");
				OutputStream output = new FileOutputStream(URLDecoder.decode(jdbcProUrl.getFile(), "utf-8"));
				Connection conn = DriverManager.getConnection(connUrl, username, password);
				Statement statement = conn.createStatement())
		{
			StringBuffer buf = new StringBuffer();
			buf.append("use ").append(dataBase.getSchema()).append(";");
			
			try
			{
				statement.execute(buf.toString());
			}
			catch(SQLException e)
			{
				statement.execute("create database " + dataBase.getSchema() + " default character set utf8;");
			}
			
			if(input != null)
			{
				List<String> lines = IOUtils.readLines(input);
				for(String line : lines)
				{
					buf.append(line).append("\n");
				}
				
				String[] sqlArray = buf.toString().split(";");
				for(String sql : sqlArray)
				{
					if(StringUtils.isBlank(sql))
					{
						continue;
					}
					
					statement.addBatch(sql);
				}
				
				statement.executeBatch();
				
				Properties pro = new Properties();
				pro.setProperty("jdbc.driver", dataBase.getDriver());
				pro.setProperty("jdbc.url", getJdbcUrl(dataBase.getSchema(), url, port));
				pro.setProperty("jdbc.username", username);
				pro.setProperty("jdbc.password", password);
				pro.store(output, "");
			}
			else
			{
				new FileNotFoundException("platform.sql");
			}
		}
		catch(SQLException | IOException e)
		{
			return e.getMessage();
		}
		
		return "";
	}
	
	private String getJdbcUrl(String schema, String url, int port)
	{
		StringBuffer buf = new StringBuffer();
		buf.append("jdbc:mysql://");
		buf.append(url).append(":").append(port).append("/");
		buf.append(schema).append("?useUnicode=true&characterEncoding=utf-8");
		
		return buf.toString();
	}
}
