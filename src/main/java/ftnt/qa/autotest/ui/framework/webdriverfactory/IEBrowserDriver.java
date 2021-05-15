package ftnt.qa.autotest.ui.framework.webdriverfactory;

import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import ftnt.qa.autotest.ui.framework.constants.CommonConstants;
import ftnt.qa.autotest.ui.framework.interfaces.IBrowserDriverBase;

public class IEBrowserDriver implements IBrowserDriverBase {

	private DesiredCapabilities dcIE;

	public void configBrowser() throws Exception {
		System.setProperty(CommonConstants.IE_DRIVER_FIELD, CommonConstants.IE_DRIVER_PATH);
		dcIE = DesiredCapabilities.internetExplorer();
		dcIE.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
		dcIE.setCapability(InternetExplorerDriver.IGNORE_ZOOM_SETTING, true);
	}

	@SuppressWarnings("unchecked")
	public <T> T getWebDriver() throws Exception {
		return (T) new InternetExplorerDriver(dcIE);
	}
}