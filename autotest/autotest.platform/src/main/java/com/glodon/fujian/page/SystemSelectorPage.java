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
public class SystemSelectorPage extends Page {
	/**
	 * 
	 */
	@Autowired
	private Button gpspBut;
	/**
	 * 
	 */
	@Autowired
	private Button gbmpBut;
	/**
	 * getter and setter methods zone
	 */
	public Button getGpspBut()
	{
		return gpspBut;
	}
	public void setGpspBut(Button gpspBut)
	{
		this.gpspBut = gpspBut;
	}
	public Button getGbmpBut()
	{
		return gbmpBut;
	}
	public void setGbmpBut(Button gbmpBut)
	{
		this.gbmpBut = gbmpBut;
	}
}
