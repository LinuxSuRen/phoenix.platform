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

package org.suren.autotest.platform.controller.commons;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.suren.autotest.platform.constants.ControllerConstants;

/**
 * 进度信息获取
 * @author suren
 * @date 2017年2月24日 下午4:02:41
 */
@Controller
@RequestMapping("progress")
public class ProgressController
{
	@ResponseBody
	@RequestMapping("info")
	public Object getProgressInfo(HttpServletRequest request, String key)
	{
		Object res = request.getSession().getAttribute(ControllerConstants.PROGRESS_PREFIX + key);
		if(res == null)
		{
			res = new Object()
			{
				@SuppressWarnings("unused")
				public String getMsg()
				{
					return "emtpy";
				}
			};
		}
		return res;
	}
}
