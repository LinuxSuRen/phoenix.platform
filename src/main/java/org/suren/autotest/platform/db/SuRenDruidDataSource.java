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

package org.suren.autotest.platform.db;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.UUID;

import org.sqlite.Function;
import org.sqlite.SQLiteConnection;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidPooledConnection;
import com.alibaba.druid.proxy.jdbc.ConnectionProxy;

/**
 * @author suren
 * @date 2017年3月16日 下午9:29:54
 */
public class SuRenDruidDataSource extends DruidDataSource
{

	/**  */
	private static final long	serialVersionUID	= 1L;

	@Override
	public DruidPooledConnection getConnection() throws SQLException
	{
		DruidPooledConnection druidPooledConnection = super.getConnection();
		Connection conn = druidPooledConnection.getConnection();
		
		if(conn instanceof ConnectionProxy)
		{
			createUserDefFunc4SQLite(((ConnectionProxy) conn).getRawObject());
		}
		else if(conn instanceof SQLiteConnection)
		{
			createUserDefFunc4SQLite(conn);
		}
		
		return druidPooledConnection;
	}
	
	/**
	 * 为SQLite数据库添加自定义函数
	 * @param conn
	 * @throws SQLException
	 */
	private void createUserDefFunc4SQLite(Connection conn) throws SQLException
	{
		Function.create(conn, "UUID", new Function() {
			@Override
			protected void xFunc() {
				try
				{
					this.result(UUID.randomUUID().toString());
				}
				catch (SQLException e)
				{
					e.printStackTrace();
				}
			}
	    });
		Function.create(conn, "left", new Function() {
			@Override
			protected void xFunc() {
				try
				{
					String str = this.value_text(0);
					int len = this.value_int(1);
					
					if(str.length() > len)
					{
						this.result(str.substring(0, len));
					}
					else
					{
						this.result(str);
					}
				}
				catch (SQLException e)
				{
					e.printStackTrace();
				}
			}
	    });
	}

}
