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
public class JsProject extends Page {
	@Autowired
	private Button projectNameSearchBut;
	public Button getProjectNameSearchBut() {
		return projectNameSearchBut;
	}
	public void setProjectNameSearchBut(Button projectNameSearchBut) {
		this.projectNameSearchBut = projectNameSearchBut;
	}
	@Autowired
	private FileUpload ziXingAttach_1;
	@Autowired
	private FileUpload ziXingAttach_2;
	@Autowired
	private FileUpload ziXingAttach_3;
	@Autowired
	private FileUpload ziXingAttach_4;
	@Autowired
	private FileUpload ziXingAttach_5;
	@Autowired
	private FileUpload ziXingAttach_6;
	@Autowired
	private Text bidPackage_tenderContent;
	@Autowired
	private Button bidPackage_bidCategoryName;
	/**
	 * 
	 */
	@Autowired
	private Button jsProjectIntoBut;
	/**
	 * 
	 */
	@Autowired
	private Button newProject;
	/**
	 * 
	 */
	@Autowired
	private Selector industry;
	/**
	 * 
	 */
	@Autowired
	private Selector tenderType;
	/**
	 * 
	 */
	@Autowired
	private Selector tenderForm;
	/**
	 * 
	 */
	@Autowired
	private Selector tenderWay;
	/**
	 * 
	 */
	@Autowired
	private Selector reviewWay;
	/**
	 * 
	 */
	@Autowired
	private CheckBoxGroup division;
	/**
	 * 
	 */
	@Autowired
	private Button nextBut;
	/**
	 * 
	 */
	@Autowired
	private Text projectName;
	/**
	 * 
	 */
	@Autowired
	private Button proCategoryName;
	/**
	 * 
	 */
	@Autowired
	private Text jianAnZaoJia;
	/**
	 * 
	 */
	@Autowired
	private Selector projMgrDept;
	/**
	 * 
	 */
	@Autowired
	private Text projectAddress;
	/**
	 * 
	 */
	@Autowired
	private Text projectOrgUnit_orgName;
	/**
	 * 
	 */
	@Autowired
	private Selector projectOrgUnit_companyType;
	/**
	 * 
	 */
	@Autowired
	private Text projectOrgUnit_legalName;
	/**
	 * 
	 */
	@Autowired
	private Text projectOrgUnit_address;
	/**
	 * 
	 */
	@Autowired
	private Text projectOrgUnit_contactName;
	/**
	 * 
	 */
	@Autowired
	private Text projectOrgUnit_contactPhone;
	/**
	 * 
	 */
	@Autowired
	private Text projectOrgUnit_telephone;
	/**
	 * 
	 */
	@Autowired
	private Text projectOrgUnit_socialCreditCode;
	/**
	 * 
	 */
	@Autowired
	private Text projectOrgAgent_qual;
	/**
	 * 
	 */
	@Autowired
	private Text projectOrgAgent_managerName;
	/**
	 * 
	 */
	@Autowired
	private Text projectOrgAgent_managerPhone;
	/**
	 * 
	 */
	@Autowired
	private Text projectOrgAgent_managerLevel;
	/**
	 * 
	 */
	@Autowired
	private Text projectExtra_projectMgrDocNo;
	/**
	 * 
	 */
	@Autowired
	private Text projectExtra_miishenpi;
	/**
	 * 
	 */
	@Autowired
	private Text projectExtra_approvalName;
	/**
	 * 
	 */
	@Autowired
	private Text investProjectCode;
	/**
	 * 
	 */
	@Autowired
	private Selector projectExtra_fundSource;
	/**
	 * 
	 */
	@Autowired
	private Text investmentSum;
	/**
	 * 
	 */
	@Autowired
	private Text investPercent;
	/**
	 * 
	 */
	@Autowired
	private Text projectExtra_planTime;
	/**
	 * 
	 */
	@Autowired
	private Button industryName;
	/**
	 * 
	 */
	@Autowired
	private Text projectExtra_projectScale;
	/**
	 * 
	 */
	@Autowired
	private Text bidPackage_packageName;
	/**
	 * 
	 */
	@Autowired
	private Text bidPackage_contractEstimate;
	/**
	 * 
	 */
	@Autowired
	private Selector bidPackage_qualityGrade;
	/**
	 * 
	 */
	@Autowired
	private Text bidPackage_planTime;
	/**
	 * 
	 */
	@Autowired
	private Button saveBut;
	/**
	 * getter and setter methods zone
	 */
	public Button getJsProjectIntoBut()
	{
		return jsProjectIntoBut;
	}
	public void setJsProjectIntoBut(Button jsProjectIntoBut)
	{
		this.jsProjectIntoBut = jsProjectIntoBut;
	}
	public Button getNewProject()
	{
		return newProject;
	}
	public void setNewProject(Button newProject)
	{
		this.newProject = newProject;
	}
	public Selector getIndustry()
	{
		return industry;
	}
	public void setIndustry(Selector industry)
	{
		this.industry = industry;
	}
	public Selector getTenderType()
	{
		return tenderType;
	}
	public void setTenderType(Selector tenderType)
	{
		this.tenderType = tenderType;
	}
	public Selector getTenderForm()
	{
		return tenderForm;
	}
	public void setTenderForm(Selector tenderForm)
	{
		this.tenderForm = tenderForm;
	}
	public Selector getTenderWay()
	{
		return tenderWay;
	}
	public void setTenderWay(Selector tenderWay)
	{
		this.tenderWay = tenderWay;
	}
	public Selector getReviewWay()
	{
		return reviewWay;
	}
	public void setReviewWay(Selector reviewWay)
	{
		this.reviewWay = reviewWay;
	}
	public CheckBoxGroup getDivision()
	{
		return division;
	}
	public void setDivision(CheckBoxGroup division)
	{
		this.division = division;
	}
	public Button getNextBut()
	{
		return nextBut;
	}
	public void setNextBut(Button nextBut)
	{
		this.nextBut = nextBut;
	}
	public Text getProjectName()
	{
		return projectName;
	}
	public void setProjectName(Text projectName)
	{
		this.projectName = projectName;
	}
	public Button getProCategoryName()
	{
		return proCategoryName;
	}
	public void setProCategoryName(Button proCategoryName)
	{
		this.proCategoryName = proCategoryName;
	}
	public Text getJianAnZaoJia()
	{
		return jianAnZaoJia;
	}
	public void setJianAnZaoJia(Text jianAnZaoJia)
	{
		this.jianAnZaoJia = jianAnZaoJia;
	}
	public Selector getProjMgrDept()
	{
		return projMgrDept;
	}
	public void setProjMgrDept(Selector projMgrDept)
	{
		this.projMgrDept = projMgrDept;
	}
	public Text getProjectAddress()
	{
		return projectAddress;
	}
	public void setProjectAddress(Text projectAddress)
	{
		this.projectAddress = projectAddress;
	}
	public Text getProjectOrgUnit_orgName()
	{
		return projectOrgUnit_orgName;
	}
	public void setProjectOrgUnit_orgName(Text projectOrgUnit_orgName)
	{
		this.projectOrgUnit_orgName = projectOrgUnit_orgName;
	}
	public Selector getProjectOrgUnit_companyType()
	{
		return projectOrgUnit_companyType;
	}
	public void setProjectOrgUnit_companyType(Selector projectOrgUnit_companyType)
	{
		this.projectOrgUnit_companyType = projectOrgUnit_companyType;
	}
	public Text getProjectOrgUnit_legalName()
	{
		return projectOrgUnit_legalName;
	}
	public void setProjectOrgUnit_legalName(Text projectOrgUnit_legalName)
	{
		this.projectOrgUnit_legalName = projectOrgUnit_legalName;
	}
	public Text getProjectOrgUnit_address()
	{
		return projectOrgUnit_address;
	}
	public void setProjectOrgUnit_address(Text projectOrgUnit_address)
	{
		this.projectOrgUnit_address = projectOrgUnit_address;
	}
	public Text getProjectOrgUnit_contactName()
	{
		return projectOrgUnit_contactName;
	}
	public void setProjectOrgUnit_contactName(Text projectOrgUnit_contactName)
	{
		this.projectOrgUnit_contactName = projectOrgUnit_contactName;
	}
	public Text getProjectOrgUnit_contactPhone()
	{
		return projectOrgUnit_contactPhone;
	}
	public void setProjectOrgUnit_contactPhone(Text projectOrgUnit_contactPhone)
	{
		this.projectOrgUnit_contactPhone = projectOrgUnit_contactPhone;
	}
	public Text getProjectOrgUnit_telephone()
	{
		return projectOrgUnit_telephone;
	}
	public void setProjectOrgUnit_telephone(Text projectOrgUnit_telephone)
	{
		this.projectOrgUnit_telephone = projectOrgUnit_telephone;
	}
	public Text getProjectOrgUnit_socialCreditCode()
	{
		return projectOrgUnit_socialCreditCode;
	}
	public void setProjectOrgUnit_socialCreditCode(Text projectOrgUnit_socialCreditCode)
	{
		this.projectOrgUnit_socialCreditCode = projectOrgUnit_socialCreditCode;
	}
	public Text getProjectOrgAgent_qual()
	{
		return projectOrgAgent_qual;
	}
	public void setProjectOrgAgent_qual(Text projectOrgAgent_qual)
	{
		this.projectOrgAgent_qual = projectOrgAgent_qual;
	}
	public Text getProjectOrgAgent_managerName()
	{
		return projectOrgAgent_managerName;
	}
	public void setProjectOrgAgent_managerName(Text projectOrgAgent_managerName)
	{
		this.projectOrgAgent_managerName = projectOrgAgent_managerName;
	}
	public Text getProjectOrgAgent_managerPhone()
	{
		return projectOrgAgent_managerPhone;
	}
	public void setProjectOrgAgent_managerPhone(Text projectOrgAgent_managerPhone)
	{
		this.projectOrgAgent_managerPhone = projectOrgAgent_managerPhone;
	}
	public Text getProjectOrgAgent_managerLevel()
	{
		return projectOrgAgent_managerLevel;
	}
	public void setProjectOrgAgent_managerLevel(Text projectOrgAgent_managerLevel)
	{
		this.projectOrgAgent_managerLevel = projectOrgAgent_managerLevel;
	}
	public Text getProjectExtra_projectMgrDocNo()
	{
		return projectExtra_projectMgrDocNo;
	}
	public void setProjectExtra_projectMgrDocNo(Text projectExtra_projectMgrDocNo)
	{
		this.projectExtra_projectMgrDocNo = projectExtra_projectMgrDocNo;
	}
	public Text getProjectExtra_miishenpi()
	{
		return projectExtra_miishenpi;
	}
	public void setProjectExtra_miishenpi(Text projectExtra_miishenpi)
	{
		this.projectExtra_miishenpi = projectExtra_miishenpi;
	}
	public Text getProjectExtra_approvalName()
	{
		return projectExtra_approvalName;
	}
	public void setProjectExtra_approvalName(Text projectExtra_approvalName)
	{
		this.projectExtra_approvalName = projectExtra_approvalName;
	}
	public Text getInvestProjectCode()
	{
		return investProjectCode;
	}
	public void setInvestProjectCode(Text investProjectCode)
	{
		this.investProjectCode = investProjectCode;
	}
	public Selector getProjectExtra_fundSource()
	{
		return projectExtra_fundSource;
	}
	public void setProjectExtra_fundSource(Selector projectExtra_fundSource)
	{
		this.projectExtra_fundSource = projectExtra_fundSource;
	}
	public Text getInvestmentSum()
	{
		return investmentSum;
	}
	public void setInvestmentSum(Text investmentSum)
	{
		this.investmentSum = investmentSum;
	}
	public Text getInvestPercent()
	{
		return investPercent;
	}
	public void setInvestPercent(Text investPercent)
	{
		this.investPercent = investPercent;
	}
	public Text getProjectExtra_planTime()
	{
		return projectExtra_planTime;
	}
	public void setProjectExtra_planTime(Text projectExtra_planTime)
	{
		this.projectExtra_planTime = projectExtra_planTime;
	}
	public Button getIndustryName()
	{
		return industryName;
	}
	public void setIndustryName(Button industryName)
	{
		this.industryName = industryName;
	}
	public Text getProjectExtra_projectScale()
	{
		return projectExtra_projectScale;
	}
	public void setProjectExtra_projectScale(Text projectExtra_projectScale)
	{
		this.projectExtra_projectScale = projectExtra_projectScale;
	}
	public Text getBidPackage_packageName()
	{
		return bidPackage_packageName;
	}
	public void setBidPackage_packageName(Text bidPackage_packageName)
	{
		this.bidPackage_packageName = bidPackage_packageName;
	}
	public Text getBidPackage_contractEstimate()
	{
		return bidPackage_contractEstimate;
	}
	public void setBidPackage_contractEstimate(Text bidPackage_contractEstimate)
	{
		this.bidPackage_contractEstimate = bidPackage_contractEstimate;
	}
	public Selector getBidPackage_qualityGrade()
	{
		return bidPackage_qualityGrade;
	}
	public void setBidPackage_qualityGrade(Selector bidPackage_qualityGrade)
	{
		this.bidPackage_qualityGrade = bidPackage_qualityGrade;
	}
	public Text getBidPackage_planTime()
	{
		return bidPackage_planTime;
	}
	public void setBidPackage_planTime(Text bidPackage_planTime)
	{
		this.bidPackage_planTime = bidPackage_planTime;
	}
	public Button getSaveBut()
	{
		return saveBut;
	}
	public void setSaveBut(Button saveBut)
	{
		this.saveBut = saveBut;
	}
	public Button getBidPackage_bidCategoryName() {
		return bidPackage_bidCategoryName;
	}
	public void setBidPackage_bidCategoryName(Button bidPackage_bidCategoryName) {
		this.bidPackage_bidCategoryName = bidPackage_bidCategoryName;
	}
	public Text getBidPackage_tenderContent() {
		return bidPackage_tenderContent;
	}
	public void setBidPackage_tenderContent(Text bidPackage_tenderContent) {
		this.bidPackage_tenderContent = bidPackage_tenderContent;
	}
	public FileUpload getZiXingAttach_1() {
		return ziXingAttach_1;
	}
	public void setZiXingAttach_1(FileUpload ziXingAttach_1) {
		this.ziXingAttach_1 = ziXingAttach_1;
	}
	public FileUpload getZiXingAttach_2() {
		return ziXingAttach_2;
	}
	public void setZiXingAttach_2(FileUpload ziXingAttach_2) {
		this.ziXingAttach_2 = ziXingAttach_2;
	}
	public FileUpload getZiXingAttach_3() {
		return ziXingAttach_3;
	}
	public void setZiXingAttach_3(FileUpload ziXingAttach_3) {
		this.ziXingAttach_3 = ziXingAttach_3;
	}
	public FileUpload getZiXingAttach_4() {
		return ziXingAttach_4;
	}
	public void setZiXingAttach_4(FileUpload ziXingAttach_4) {
		this.ziXingAttach_4 = ziXingAttach_4;
	}
	public FileUpload getZiXingAttach_5() {
		return ziXingAttach_5;
	}
	public void setZiXingAttach_5(FileUpload ziXingAttach_5) {
		this.ziXingAttach_5 = ziXingAttach_5;
	}
	public FileUpload getZiXingAttach_6() {
		return ziXingAttach_6;
	}
	public void setZiXingAttach_6(FileUpload ziXingAttach_6) {
		this.ziXingAttach_6 = ziXingAttach_6;
	}
}
