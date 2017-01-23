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
public class PersonInfoPage extends Page {
	/**
	 * 
	 */
	@Autowired
	private Button workCertBut;
	/**
	 * 
	 */
	@Autowired
	private Button personPerformBut;
	/**
	 * getter and setter methods zone
	 */
	public Button getWorkCertBut()
	{
		return workCertBut;
	}
	public void setWorkCertBut(Button workCertBut)
	{
		this.workCertBut = workCertBut;
	}
	public Button getPersonPerformBut()
	{
		return personPerformBut;
	}
	public void setPersonPerformBut(Button personPerformBut)
	{
		this.personPerformBut = personPerformBut;
	}
}
