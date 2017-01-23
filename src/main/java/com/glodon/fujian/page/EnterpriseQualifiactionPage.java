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
public class EnterpriseQualifiactionPage extends Page {
	/**
	 * 
	 */
	@Autowired
	private Button addBut;
	/**
	 * 
	 */
	@Autowired
	private Text certNo;
	/**
	 * 
	 */
	@Autowired
	private Text businessRange;
	/**
	 * 
	 */
	@Autowired
	private Text validDate;
	/**
	 * 
	 */
	@Autowired
	private FileUpload addEnterQualiFile;
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
	public Text getCertNo()
	{
		return certNo;
	}
	public void setCertNo(Text certNo)
	{
		this.certNo = certNo;
	}
	public Text getBusinessRange()
	{
		return businessRange;
	}
	public void setBusinessRange(Text businessRange)
	{
		this.businessRange = businessRange;
	}
	public Text getValidDate()
	{
		return validDate;
	}
	public void setValidDate(Text validDate)
	{
		this.validDate = validDate;
	}
	public FileUpload getAddEnterQualiFile()
	{
		return addEnterQualiFile;
	}
	public void setAddEnterQualiFile(FileUpload addEnterQualiFile)
	{
		this.addEnterQualiFile = addEnterQualiFile;
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
