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

package org.suren.autotest.platform.servlet;

import javax.servlet.jsp.JspException;

import org.springframework.web.servlet.tags.form.TagWriter;
import org.suren.autotest.web.framework.util.StringUtils;

/**
 * @author suren
 * @date 2017年1月31日 上午11:19:07
 */
public class ScriptTag extends AbstractContextHtmlElementTag
{
	/**  */
	private static final long	serialVersionUID	= 1L;
	
	private String src;
	private String type;

	@Override
	protected int writeTagContent(TagWriter tagWriter) throws JspException
	{
		tagWriter.startTag("script");
		tagWriter.writeAttribute("src", getContextUrl(src));
		tagWriter.writeAttribute("type", StringUtils.isBlank(type) ? "text/javascript" : type);
		tagWriter.endTag(true);
		return 0;
	}

	/**
	 * @return the src
	 */
	public String getSrc()
	{
		return src;
	}

	/**
	 * @param src the src to set
	 */
	public void setSrc(String src)
	{
		this.src = src;
	}

	public String getType()
	{
		return type;
	}

	public void setType(String type)
	{
		this.type = type;
	}

}
