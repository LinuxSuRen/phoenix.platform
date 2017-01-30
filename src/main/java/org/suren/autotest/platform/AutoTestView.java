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

package org.suren.autotest.platform;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

import org.springframework.web.servlet.view.JstlView;

/**
 * @author suren
 * @date 2017年1月30日 下午1:18:44
 */
public class AutoTestView extends JstlView
{

	@Override
	protected RequestDispatcher getRequestDispatcher(
			HttpServletRequest request, String path)
	{
		RequestDispatcher requestDispatcher = super.getRequestDispatcher(request, path);
		
		return requestDispatcher;
	}
}
