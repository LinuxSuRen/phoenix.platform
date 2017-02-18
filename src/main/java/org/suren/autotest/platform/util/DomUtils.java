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

package org.suren.autotest.platform.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

/**
 * @author suren
 * @date 2017年2月17日 下午12:38:17
 */
public abstract class DomUtils
{
	/**
	 * 把xml文本格式化
	 * @param text
	 * @return
	 */
	public static String format(String text)
	{
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		ByteArrayInputStream input = new ByteArrayInputStream(text.getBytes());
		
		SAXReader reader = new SAXReader();
		try
		{
			Document doc = reader.read(input);
			
			OutputFormat format = OutputFormat.createPrettyPrint();
			format.setIndentSize(4);
			
			XMLWriter writer = new XMLWriter(out, format);
			writer.write(doc);
		}
		catch (DocumentException | UnsupportedEncodingException e)
		{
			e.printStackTrace();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		
		return out.toString();
	}
}
