package com.glodon.fujian.page;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.suren.autotest.web.framework.core.ui.Button;
import org.suren.autotest.web.framework.core.ui.Text;
import org.suren.autotest.web.framework.core.ui.Selector;
import org.suren.autotest.web.framework.core.ui.FileUpload;
import org.suren.autotest.web.framework.core.ui.CheckBoxGroup;
import org.suren.autotest.web.framework.page.Page;

/**
 * @author suren
 * 
 */
@Component
public class SubSystemSelectPage extends Page {
	/**
	 * 
	 */
	@Autowired
	private Button registerSysBut;
	/**
	 * getter and setter methods zone
	 */
	public Button getRegisterSysBut()
	{
		return registerSysBut;
	}
	public void setRegisterSysBut(Button registerSysBut)
	{
		this.registerSysBut = registerSysBut;
	}
}
