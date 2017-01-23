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
public class PersonPerformPage extends Page {
	/**
	 * 
	 */
	@Autowired
	private Button addInfoBut;
	/**
	 * 
	 */
	@Autowired
	private Text projectName;
	/**
	 * 
	 */
	@Autowired
	private Text projectCode;
	/**
	 * 
	 */
	@Autowired
	private Text sectionName;
	/**
	 * 
	 */
	@Autowired
	private Text packageCode;
	/**
	 * 
	 */
	@Autowired
	private Selector packageType;
	/**
	 * 
	 */
	@Autowired
	private Button tradeTypeName;
	/**
	 * 
	 */
	@Autowired
	private Button tradeTypeName_1;
	/**
	 * 
	 */
	@Autowired
	private Button tradeTypeName_2;
	/**
	 * 
	 */
	@Autowired
	private Text constructionUnit;
	/**
	 * 
	 */
	@Autowired
	private Text ownerNo;
	/**
	 * 
	 */
	@Autowired
	private Text tenderAgentName;
	/**
	 * 
	 */
	@Autowired
	private Text bidWinningDate;
	/**
	 * 
	 */
	@Autowired
	private Text winningAmount;
	/**
	 * 
	 */
	@Autowired
	private Text contractSigningTime;
	/**
	 * 
	 */
	@Autowired
	private Text bidWinningMoney;
	/**
	 * 
	 */
	@Autowired
	private Text settlementAmount;
	/**
	 * 
	 */
	@Autowired
	private Text contractLimit;
	/**
	 * 
	 */
	@Autowired
	private Button saveBut;
	/**
	 * getter and setter methods zone
	 */
	public Button getAddInfoBut()
	{
		return addInfoBut;
	}
	public void setAddInfoBut(Button addInfoBut)
	{
		this.addInfoBut = addInfoBut;
	}
	public Text getProjectName()
	{
		return projectName;
	}
	public void setProjectName(Text projectName)
	{
		this.projectName = projectName;
	}
	public Text getProjectCode()
	{
		return projectCode;
	}
	public void setProjectCode(Text projectCode)
	{
		this.projectCode = projectCode;
	}
	public Text getSectionName()
	{
		return sectionName;
	}
	public void setSectionName(Text sectionName)
	{
		this.sectionName = sectionName;
	}
	public Text getPackageCode()
	{
		return packageCode;
	}
	public void setPackageCode(Text packageCode)
	{
		this.packageCode = packageCode;
	}
	public Selector getPackageType()
	{
		return packageType;
	}
	public void setPackageType(Selector packageType)
	{
		this.packageType = packageType;
	}
	public Button getTradeTypeName()
	{
		return tradeTypeName;
	}
	public void setTradeTypeName(Button tradeTypeName)
	{
		this.tradeTypeName = tradeTypeName;
	}
	public Button getTradeTypeName_1()
	{
		return tradeTypeName_1;
	}
	public void setTradeTypeName_1(Button tradeTypeName_1)
	{
		this.tradeTypeName_1 = tradeTypeName_1;
	}
	public Button getTradeTypeName_2()
	{
		return tradeTypeName_2;
	}
	public void setTradeTypeName_2(Button tradeTypeName_2)
	{
		this.tradeTypeName_2 = tradeTypeName_2;
	}
	public Text getConstructionUnit()
	{
		return constructionUnit;
	}
	public void setConstructionUnit(Text constructionUnit)
	{
		this.constructionUnit = constructionUnit;
	}
	public Text getOwnerNo()
	{
		return ownerNo;
	}
	public void setOwnerNo(Text ownerNo)
	{
		this.ownerNo = ownerNo;
	}
	public Text getTenderAgentName()
	{
		return tenderAgentName;
	}
	public void setTenderAgentName(Text tenderAgentName)
	{
		this.tenderAgentName = tenderAgentName;
	}
	public Text getBidWinningDate()
	{
		return bidWinningDate;
	}
	public void setBidWinningDate(Text bidWinningDate)
	{
		this.bidWinningDate = bidWinningDate;
	}
	public Text getWinningAmount()
	{
		return winningAmount;
	}
	public void setWinningAmount(Text winningAmount)
	{
		this.winningAmount = winningAmount;
	}
	public Text getContractSigningTime()
	{
		return contractSigningTime;
	}
	public void setContractSigningTime(Text contractSigningTime)
	{
		this.contractSigningTime = contractSigningTime;
	}
	public Text getBidWinningMoney()
	{
		return bidWinningMoney;
	}
	public void setBidWinningMoney(Text bidWinningMoney)
	{
		this.bidWinningMoney = bidWinningMoney;
	}
	public Text getSettlementAmount()
	{
		return settlementAmount;
	}
	public void setSettlementAmount(Text settlementAmount)
	{
		this.settlementAmount = settlementAmount;
	}
	public Text getContractLimit()
	{
		return contractLimit;
	}
	public void setContractLimit(Text contractLimit)
	{
		this.contractLimit = contractLimit;
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
