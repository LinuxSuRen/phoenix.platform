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
public class GpspLogin extends Page {
	/**
	 * 
	 */
	@Autowired
	private Text username;
	/**
	 * 
	 */
	@Autowired
	private Text password;
	/**
	 * 
	 */
	@Autowired
	private Button loginBut;
	/**
	 * getter and setter methods zone
	 */
	public Text getUsername()
	{
		return username;
	}
	public void setUsername(Text username)
	{
		this.username = username;
	}
	public Text getPassword()
	{
		return password;
	}
	public void setPassword(Text password)
	{
		this.password = password;
	}
	public Button getLoginBut()
	{
		return loginBut;
	}
	public void setLoginBut(Button loginBut)
	{
		this.loginBut = loginBut;
	}
}
