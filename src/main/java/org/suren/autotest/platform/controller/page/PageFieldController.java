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

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.suren.autotest.platform.schemas.autotest.FieldTypeEnum;
import org.suren.autotest.platform.schemas.autotest.StrategyEnum;

/**
 * @author suren
 * @date 2017年7月14日 上午8:36:55
 */
@Controller
@RequestMapping("/page_field")
public class PageFieldController
{
	@RequestMapping(value = "/list")
	public String fieldTable(@RequestParam String pageId, Model model)
	{
		model.addAttribute("fieldType", FieldTypeEnum.values());
		model.addAttribute("strategyType", StrategyEnum.values());
		model.addAttribute("pageId", pageId);
		return "page_info/page_field_table";
	}
}
