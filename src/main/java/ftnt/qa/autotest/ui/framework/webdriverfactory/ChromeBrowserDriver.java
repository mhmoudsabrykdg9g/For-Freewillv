package ftnt.qa.autotest.ui.framework.webdriverfactory;

import java.io.File;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;

import ec.qa.autotest.ui.constants.CommonConstants;
import ftnt.qa.autotest.ui.framework.interfaces.IBrowserDriverBase;

public class ChromeBrowserDriver implements IBrowserDriverBase {

	private ChromeDriverService chromeService = null;

	public void configBrowser() throws Exception {
		System.setProperty(CommonConstants.CHROME_DRIVER_FIELD, CommonConstants.CHROME_DRIVER_PATH);
		chromeService = new ChromeDriverService.Builder()
				.usingDriverExecutable(new File(CommonConstants.CHROME_DRIVER_PATH)).usingAnyFreePort().build();
		chromeService.start();
	}

	@SuppressWarnings("unchecked")
	public <T> T getWebDriver() throws Exception {
		configBrowser();
		return (T) new ChromeDriver(chromeService);
	}
}