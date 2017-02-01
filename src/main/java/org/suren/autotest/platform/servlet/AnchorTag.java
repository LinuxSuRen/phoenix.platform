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

import org.springframework.util.ObjectUtils;
import org.springframework.web.servlet.tags.form.TagWriter;

/**
 * @author suren
 * @date 2017年2月1日 下午7:07:01
 */
public class AnchorTag extends AbstractContextHtmlElementTag
{

	/**  */
	private static final long	serialVersionUID	= 1L;
	
	private String href;
	private String innerHtml;

	@Override
	protected int writeTagContent(TagWriter tagWriter) throws JspException
	{
		tagWriter.startTag("a");
		tagWriter.writeAttribute("href", getContextUrl(href));
		writeOptionalAttributes(tagWriter);
		tagWriter.appendValue(innerHtml);
		tagWriter.endTag(true);
		return EVAL_PAGE;
	}
	
	@Override
	protected String resolveCssClass() throws JspException {
		return ObjectUtils.getDisplayString(evaluate("cssClass", getCssClass()));
	}

	public String getHref()
	{
		return href;
	}

	public void setHref(String href)
	{
		this.href = href;
	}

	public String getInnerHtml()
	{
		return innerHtml;
	}

	public void setInnerHtml(String innerHtml)
	{
		this.innerHtml = innerHtml;
	}

}
