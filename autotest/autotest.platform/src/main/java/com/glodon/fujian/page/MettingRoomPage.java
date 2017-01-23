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
public class MettingRoomPage extends Page {
	/**
	 * 
	 */
	@Autowired
	private Button meettingRoomMgrBut;
	/**
	 * 
	 */
	@Autowired
	private Button meettingRoomMain;
	/**
	 * 
	 */
	@Autowired
	private Button addMeettingRoomBut;
	/**
	 * 
	 */
	@Autowired
	private Text meetingRoomRoomName;
	/**
	 * 
	 */
	@Autowired
	private Selector meetingRoomRoomType;
	/**
	 * 
	 */
	@Autowired
	private Button mettingRootSaveBut;
	/**
	 * getter and setter methods zone
	 */
	public Button getMeettingRoomMgrBut()
	{
		return meettingRoomMgrBut;
	}
	public void setMeettingRoomMgrBut(Button meettingRoomMgrBut)
	{
		this.meettingRoomMgrBut = meettingRoomMgrBut;
	}
	public Button getMeettingRoomMain()
	{
		return meettingRoomMain;
	}
	public void setMeettingRoomMain(Button meettingRoomMain)
	{
		this.meettingRoomMain = meettingRoomMain;
	}
	public Button getAddMeettingRoomBut()
	{
		return addMeettingRoomBut;
	}
	public void setAddMeettingRoomBut(Button addMeettingRoomBut)
	{
		this.addMeettingRoomBut = addMeettingRoomBut;
	}
	public Text getMeetingRoomRoomName()
	{
		return meetingRoomRoomName;
	}
	public void setMeetingRoomRoomName(Text meetingRoomRoomName)
	{
		this.meetingRoomRoomName = meetingRoomRoomName;
	}
	public Selector getMeetingRoomRoomType()
	{
		return meetingRoomRoomType;
	}
	public void setMeetingRoomRoomType(Selector meetingRoomRoomType)
	{
		this.meetingRoomRoomType = meetingRoomRoomType;
	}
	public Button getMettingRootSaveBut()
	{
		return mettingRootSaveBut;
	}
	public void setMettingRootSaveBut(Button mettingRootSaveBut)
	{
		this.mettingRootSaveBut = mettingRootSaveBut;
	}
}
