/**
* Copyright © 1998-2017, Glodon Inc. All Rights Reserved.
*/
package glodon.jujian.invoker;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.suren.autotest.web.framework.selenium.SeleniumEngine;
import org.suren.autotest.web.framework.settings.SettingUtil;
import org.suren.autotest.web.framework.util.ThreadUtil;

/**
 * @author zhaoxj
 * @since jdk1.6
 * 2017年1月11日
 */
public class TopMenuInvoker {
	public static void execute(SettingUtil util, String[] params)
	{
		ThreadUtil.silentSleep(6000);
		
		String menuName = params[0];
		
		SeleniumEngine engine = util.getEngine();
		WebDriver driver = engine.getDriver();
		
		List<WebElement> eles = driver.findElements(By.className("menu-item"));
		for(WebElement ele : eles)
		{
			if("menu-item active".equals(ele.getAttribute("class")))
			{
				if(!menuName.equals(ele.getText()))
				{
					driver.findElement(By.partialLinkText(menuName)).click();
				}
				
				break;
			}
		}
	}
}
