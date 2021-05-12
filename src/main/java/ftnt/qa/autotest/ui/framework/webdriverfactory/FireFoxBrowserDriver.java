package ftnt.qa.autotest.ui.framework.webdriverfactory;

import org.openqa.selenium.firefox.FirefoxDriver;

import ftnt.qa.autotest.ui.framework.interfaces.IBrowserDriverBase;

public class FireFoxBrowserDriver implements IBrowserDriverBase {

	public void configBrowser() throws Exception {
		// TODO Auto-generated method stub
	}

	@SuppressWarnings("unchecked")
	public <T> T getWebDriver() throws Exception {
		// TODO Auto-generated method stub
		return (T) new FirefoxDriver();
	}
}