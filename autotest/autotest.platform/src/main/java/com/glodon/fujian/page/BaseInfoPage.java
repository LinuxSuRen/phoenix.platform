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
public class BaseInfoPage extends Page {
	/**
	 * 
	 */
	@Autowired
	private Text address;
	/**
	 * 
	 */
	@Autowired
	private Text createDate;
	/**
	 * 
	 */
	@Autowired
	private Text bank;
	/**
	 * 
	 */
	@Autowired
	private Text account;
	/**
	 * 
	 */
	@Autowired
	private Text threeInOne_socialCreditCode;
	/**
	 * 
	 */
	@Autowired
	private Selector threeInOne_corporationType;
	/**
	 * 
	 */
	@Autowired
	private Text threeInOne_legalRepresentative;
	/**
	 * 
	 */
	@Autowired
	private Text threeInOne_registeredCapital;
	/**
	 * 
	 */
	@Autowired
	private Text threeInOne_startDate;
	/**
	 * 
	 */
	@Autowired
	private Text threeInOne_endDate;
	/**
	 * 
	 */
	@Autowired
	private Text threeInOne_registrationAuthority;
	/**
	 * 
	 */
	@Autowired
	private Text threeInOne_registerDate;
	/**
	 * 
	 */
	@Autowired
	private Text contactName;
	/**
	 * 
	 */
	@Autowired
	private Text contactPhone;
	/**
	 * 
	 */
	@Autowired
	private FileUpload threeInOnFile;
	/**
	 * 
	 */
	@Autowired
	private FileUpload basicFile;
	/**
	 * 
	 */
	@Autowired
	private Button saveBut;
	/**
	 * 
	 */
	@Autowired
	private Button submitBut;
	/**
	 * 
	 */
	@Autowired
	private Button okBut;
	/**
	 * getter and setter methods zone
	 */
	public Text getAddress()
	{
		return address;
	}
	public void setAddress(Text address)
	{
		this.address = address;
	}
	public Text getCreateDate()
	{
		return createDate;
	}
	public void setCreateDate(Text createDate)
	{
		this.createDate = createDate;
	}
	public Text getBank()
	{
		return bank;
	}
	public void setBank(Text bank)
	{
		this.bank = bank;
	}
	public Text getAccount()
	{
		return account;
	}
	public void setAccount(Text account)
	{
		this.account = account;
	}
	public Text getThreeInOne_socialCreditCode()
	{
		return threeInOne_socialCreditCode;
	}
	public void setThreeInOne_socialCreditCode(Text threeInOne_socialCreditCode)
	{
		this.threeInOne_socialCreditCode = threeInOne_socialCreditCode;
	}
	public Selector getThreeInOne_corporationType()
	{
		return threeInOne_corporationType;
	}
	public void setThreeInOne_corporationType(Selector threeInOne_corporationType)
	{
		this.threeInOne_corporationType = threeInOne_corporationType;
	}
	public Text getThreeInOne_legalRepresentative()
	{
		return threeInOne_legalRepresentative;
	}
	public void setThreeInOne_legalRepresentative(Text threeInOne_legalRepresentative)
	{
		this.threeInOne_legalRepresentative = threeInOne_legalRepresentative;
	}
	public Text getThreeInOne_registeredCapital()
	{
		return threeInOne_registeredCapital;
	}
	public void setThreeInOne_registeredCapital(Text threeInOne_registeredCapital)
	{
		this.threeInOne_registeredCapital = threeInOne_registeredCapital;
	}
	public Text getThreeInOne_startDate()
	{
		return threeInOne_startDate;
	}
	public void setThreeInOne_startDate(Text threeInOne_startDate)
	{
		this.threeInOne_startDate = threeInOne_startDate;
	}
	public Text getThreeInOne_endDate()
	{
		return threeInOne_endDate;
	}
	public void setThreeInOne_endDate(Text threeInOne_endDate)
	{
		this.threeInOne_endDate = threeInOne_endDate;
	}
	public Text getThreeInOne_registrationAuthority()
	{
		return threeInOne_registrationAuthority;
	}
	public void setThreeInOne_registrationAuthority(Text threeInOne_registrationAuthority)
	{
		this.threeInOne_registrationAuthority = threeInOne_registrationAuthority;
	}
	public Text getThreeInOne_registerDate()
	{
		return threeInOne_registerDate;
	}
	public void setThreeInOne_registerDate(Text threeInOne_registerDate)
	{
		this.threeInOne_registerDate = threeInOne_registerDate;
	}
	public Text getContactName()
	{
		return contactName;
	}
	public void setContactName(Text contactName)
	{
		this.contactName = contactName;
	}
	public Text getContactPhone()
	{
		return contactPhone;
	}
	public void setContactPhone(Text contactPhone)
	{
		this.contactPhone = contactPhone;
	}
	public FileUpload getThreeInOnFile()
	{
		return threeInOnFile;
	}
	public void setThreeInOnFile(FileUpload threeInOnFile)
	{
		this.threeInOnFile = threeInOnFile;
	}
	public FileUpload getBasicFile()
	{
		return basicFile;
	}
	public void setBasicFile(FileUpload basicFile)
	{
		this.basicFile = basicFile;
	}
	public Button getSaveBut()
	{
		return saveBut;
	}
	public void setSaveBut(Button saveBut)
	{
		this.saveBut = saveBut;
	}
	public Button getSubmitBut()
	{
		return submitBut;
	}
	public void setSubmitBut(Button submitBut)
	{
		this.submitBut = submitBut;
	}
	public Button getOkBut()
	{
		return okBut;
	}
	public void setOkBut(Button okBut)
	{
		this.okBut = okBut;
	}
}
