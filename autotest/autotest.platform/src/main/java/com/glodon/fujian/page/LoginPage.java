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
public class LoginPage extends Page {
	/**
	 * 
	 */
	@Autowired
	private Text useName;
	/**
	 * 
	 */
	@Autowired
	private Text password;
	/**
	 * 
	 */
	@Autowired
	private Text kaptcha;
	/**
	 * 
	 */
	@Autowired
	private Button loginBut;
	/**
	 * 
	 */
	@Autowired
	private Button registerBut;
	/**
	 * getter and setter methods zone
	 */
	public Text getUseName()
	{
		return useName;
	}
	public void setUseName(Text useName)
	{
		this.useName = useName;
	}
	public Text getPassword()
	{
		return password;
	}
	public void setPassword(Text password)
	{
		this.password = password;
	}
	public Text getKaptcha()
	{
		return kaptcha;
	}
	public void setKaptcha(Text kaptcha)
	{
		this.kaptcha = kaptcha;
	}
	public Button getLoginBut()
	{
		return loginBut;
	}
	public void setLoginBut(Button loginBut)
	{
		this.loginBut = loginBut;
	}
	public Button getRegisterBut()
	{
		return registerBut;
	}
	public void setRegisterBut(Button registerBut)
	{
		this.registerBut = registerBut;
	}
}
