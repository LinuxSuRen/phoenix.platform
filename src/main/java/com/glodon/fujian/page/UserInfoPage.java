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
public class UserInfoPage extends Page {
	/**
	 * 
	 */
	@Autowired
	private Button baseInfoBut;
	/**
	 * 
	 */
	@Autowired
	private Button entQualiBut;
	/**
	 * 
	 */
	@Autowired
	private Button entPersonInfoBut;
	/**
	 * getter and setter methods zone
	 */
	public Button getBaseInfoBut()
	{
		return baseInfoBut;
	}
	public void setBaseInfoBut(Button baseInfoBut)
	{
		this.baseInfoBut = baseInfoBut;
	}
	public Button getEntQualiBut()
	{
		return entQualiBut;
	}
	public void setEntQualiBut(Button entQualiBut)
	{
		this.entQualiBut = entQualiBut;
	}
	public Button getEntPersonInfoBut()
	{
		return entPersonInfoBut;
	}
	public void setEntPersonInfoBut(Button entPersonInfoBut)
	{
		this.entPersonInfoBut = entPersonInfoBut;
	}
}
