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
public class PersonBaseInfoPage extends Page {
	/**
	 * 
	 */
	@Autowired
	private Selector education;
	/**
	 * 
	 */
	@Autowired
	private Selector titles;
	/**
	 * 
	 */
	@Autowired
	private Text contactPhone;
	/**
	 * 
	 */
	@Autowired
	private Text workStartTime;
	/**
	 * 
	 */
	@Autowired
	private Button saveBut;
	/**
	 * getter and setter methods zone
	 */
	public Selector getEducation()
	{
		return education;
	}
	public void setEducation(Selector education)
	{
		this.education = education;
	}
	public Selector getTitles()
	{
		return titles;
	}
	public void setTitles(Selector titles)
	{
		this.titles = titles;
	}
	public Text getContactPhone()
	{
		return contactPhone;
	}
	public void setContactPhone(Text contactPhone)
	{
		this.contactPhone = contactPhone;
	}
	public Text getWorkStartTime()
	{
		return workStartTime;
	}
	public void setWorkStartTime(Text workStartTime)
	{
		this.workStartTime = workStartTime;
	}
	public Button getSaveBut()
	{
		return saveBut;
	}
	public void setSaveBut(Button saveBut)
	{
		this.saveBut = saveBut;
	}
}
