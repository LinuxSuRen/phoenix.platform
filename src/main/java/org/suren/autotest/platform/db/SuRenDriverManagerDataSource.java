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
import java.util.Properties;

import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.sqlite.Function;

/**
 * @author suren
 * @date 2017年2月7日 下午9:33:50
 */
public class SuRenDriverManagerDataSource extends DriverManagerDataSource
{

	@Override
	protected Connection getConnectionFromDriver(Properties props)
			throws SQLException
	{
		Connection conn = super.getConnectionFromDriver(props);
		Function.create(conn, "UUID", new Function() {
			protected void xFunc() {
				try
				{
					this.result(System.currentTimeMillis() + "");
				}
				catch (SQLException e)
				{
					e.printStackTrace();
				}
			}
	    });
		return conn;
	}

}
