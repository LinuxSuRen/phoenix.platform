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

import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.config.Scope;
import org.springframework.web.context.request.RequestScope;

/**
 * @author suren
 * @date 2017年1月24日 上午9:26:26
 */
public class AutotestScope extends RequestScope implements Scope
{

	@Override
	protected int getScope()
	{
		return super.getScope();
	}

	@Override
	public String getConversationId()
	{
		return super.getConversationId();
	}

	@Override
	public Object get(String name, ObjectFactory<?> objectFactory)
	{
		// TODO Auto-generated method stub
		return super.get(name, objectFactory);
	}
	
}
