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
public class EnterprisePersonInfoPage extends Page {
	/**
	 * 
	 */
	@Autowired
	private Button addBut;
	/**
	 * 
	 */
	@Autowired
	private Text personName;
	/**
	 * 
	 */
	@Autowired
	private Text voucherNo;
	/**
	 * 
	 */
	@Autowired
	private Selector gender;
	/**
	 * 
	 */
	@Autowired
	private Button saveBut;
	/**
	 * getter and setter methods zone
	 */
	public Button getAddBut()
	{
		return addBut;
	}
	public void setAddBut(Button addBut)
	{
		this.addBut = addBut;
	}
	public Text getPersonName()
	{
		return personName;
	}
	public void setPersonName(Text personName)
	{
		this.personName = personName;
	}
	public Text getVoucherNo()
	{
		return voucherNo;
	}
	public void setVoucherNo(Text voucherNo)
	{
		this.voucherNo = voucherNo;
	}
	public Selector getGender()
	{
		return gender;
	}
	public void setGender(Selector gender)
	{
		this.gender = gender;
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
