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

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import freemarker.cache.ClassTemplateLoader;
import freemarker.cache.TemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;
import freemarker.template.TemplateException;

/**
 * 确认对话框
 * @author suren
 * @date 2017年2月2日 下午2:39:31
 */
public class DialogTag extends DefaultFMTagSupport
{

	/**  */
	private static final long	serialVersionUID	= 1L;
	
	private String dialogId;
	private boolean ajaxDel;
	private String callback;

	@Override
	protected Map<String, Object> paramMap()
	{
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("dialogId", dialogId);
		paramMap.put("ajaxDel", ajaxDel);
		paramMap.put("callback", callback);
		return paramMap;
	}

	@Override
	protected String templateName()
	{
		return "dialog.ftl";
	}

	public String getDialogId()
	{
		return dialogId;
	}

	public void setDialogId(String dialogId)
	{
		this.dialogId = dialogId;
	}

	/**
	 * @return the ajaxDel
	 */
	public boolean isAjaxDel()
	{
		return ajaxDel;
	}

	/**
	 * @param ajaxDel the ajaxDel to set
	 */
	public void setAjaxDel(boolean ajaxDel)
	{
		this.ajaxDel = ajaxDel;
	}

	/**
	 * @return the callback
	 */
	public String getCallback()
	{
		return callback;
	}

	/**
	 * @param callback the callback to set
	 */
	public void setCallback(String callback)
	{
		this.callback = callback;
	}

}
