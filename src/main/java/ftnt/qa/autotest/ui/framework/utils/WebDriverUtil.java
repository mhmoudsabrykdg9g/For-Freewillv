package ftnt.qa.autotest.ui.framework.utils;


import org.openqa.selenium.remote.RemoteWebDriver;

import ftnt.qa.autotest.ui.testbase.TestBase;

public class WebDriverUtil {
	
	public static RemoteWebDriver getWebDriverInstance(){
		return TestBase.getWebDriver();
	}
}