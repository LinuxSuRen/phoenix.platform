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
public class EnterpriseApprovePage extends Page {
	/**
	 * 
	 */
	@Autowired
	private Button approveListBut;
	/**
	 * getter and setter methods zone
	 */
	public Button getApproveListBut()
	{
		return approveListBut;
	}
	public void setApproveListBut(Button approveListBut)
	{
		this.approveListBut = approveListBut;
	}
}
