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
public class RegisterPage extends Page {
	/**
	 * 
	 */
	@Autowired
	private CheckBoxGroup natural;
	/**
	 * 
	 */
	@Autowired
	private Text enterpriseName;
	/**
	 * 
	 */
	@Autowired
	private CheckBoxGroup businessFace;
	/**
	 * 
	 */
	@Autowired
	private CheckBoxGroup roleTypeList;
	/**
	 * 
	 */
	@Autowired
	private Text password;
	/**
	 * 
	 */
	@Autowired
	private Text repassword;
	/**
	 * 
	 */
	@Autowired
	private Text kaptchafield;
	/**
	 * 
	 */
	@Autowired
	private Button btnSubmit;
	/**
	 * 
	 */
	@Autowired
	private Button gooBt;
	/**
	 * 
	 */
	@Autowired
	private Button baseInfoBut;
	/**
	 * getter and setter methods zone
	 */
	public CheckBoxGroup getNatural()
	{
		return natural;
	}
	public void setNatural(CheckBoxGroup natural)
	{
		this.natural = natural;
	}
	public Text getEnterpriseName()
	{
		return enterpriseName;
	}
	public void setEnterpriseName(Text enterpriseName)
	{
		this.enterpriseName = enterpriseName;
	}
	public CheckBoxGroup getBusinessFace()
	{
		return businessFace;
	}
	public void setBusinessFace(CheckBoxGroup businessFace)
	{
		this.businessFace = businessFace;
	}
	public CheckBoxGroup getRoleTypeList()
	{
		return roleTypeList;
	}
	public void setRoleTypeList(CheckBoxGroup roleTypeList)
	{
		this.roleTypeList = roleTypeList;
	}
	public Text getPassword()
	{
		return password;
	}
	public void setPassword(Text password)
	{
		this.password = password;
	}
	public Text getRepassword()
	{
		return repassword;
	}
	public void setRepassword(Text repassword)
	{
		this.repassword = repassword;
	}
	public Text getKaptchafield()
	{
		return kaptchafield;
	}
	public void setKaptchafield(Text kaptchafield)
	{
		this.kaptchafield = kaptchafield;
	}
	public Button getBtnSubmit()
	{
		return btnSubmit;
	}
	public void setBtnSubmit(Button btnSubmit)
	{
		this.btnSubmit = btnSubmit;
	}
	public Button getGooBt()
	{
		return gooBt;
	}
	public void setGooBt(Button gooBt)
	{
		this.gooBt = gooBt;
	}
	public Button getBaseInfoBut()
	{
		return baseInfoBut;
	}
	public void setBaseInfoBut(Button baseInfoBut)
	{
		this.baseInfoBut = baseInfoBut;
	}
}
