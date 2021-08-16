package ftnt.qa.autotest.ui.fortisiem.pageobject;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.poi.util.SystemOutLogger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ftnt.qa.autotest.ui.fortisiem.selenium3.CommonAction;
import ftnt.qa.autotest.ui.fortisiem.selenium3.GetTableBy;
import ftnt.qa.autotest.ui.fortisiem.selenium3.GetTableNew;
import ftnt.qa.autotest.ui.fortisiem.selenium3.GetTableOld;
import ftnt.qa.autotest.ui.framework.testbase.TestBase;
import ftnt.qa.autotest.ui.framework.utils.CaptureElementUtil;

import java.util.concurrent.TimeUnit;

/**
 * Created by ssuku on 1/9/17.
 */
public class Login {
	private WebDriver driver;
	private static final Logger logger = LoggerFactory.getLogger(Login.class);

	public Login(WebDriver driver) {
		this.driver = driver;
	}

	@FindBy(id = "loginHtml:username")
	private WebElement userName;
	@FindBy(id = "loginHtml:password")
	private WebElement password;
	@FindBy(id = "loginHtml:domain")
	private WebElement domain;
	@FindBy(id = "loginHtml:userDomain")
	private WebElement userDomain;
	@FindBy(id = "loginHtml:loginBtn")
	private WebElement loginBtn;
	@FindBy(css = "ul.nav.navbar-nav.navbar-right.header-menu li:nth-child(3)")
	private WebElement userIcon;
	@FindBy(partialLinkText = "Logout")
	private WebElement logout;
	@FindBy(css = "div.errors ul li")
	private WebElement errorMsg;
	@FindBy(id = "si_searchAttrInput")
	private WebElement searchAttrInput;

	@FindBy(how = How.XPATH, using = "//*[@id=\"mainHeader\"]/div/nav/div[2]/ul[2]/li[3]/a")
	private WebElement profile;

	public Login login(String userName, String password, String custId, String domain)
			throws ConfigurationException, InterruptedException {
		this.userName.sendKeys(userName);
		this.password.sendKeys(password);
		this.domain.clear();
		this.domain.sendKeys(custId);
		Select select = null;
		for (int i = 0; i < 3; i++) {
			try {
				select = new Select(userDomain);
				select.selectByVisibleText(domain);
				break;
			} catch (org.openqa.selenium.StaleElementReferenceException e) {
			}
		}

		loginBtn.click();
		// 截取指定元素，这里必须使用 driver.find的方式传递
		CaptureElementUtil.getInstance().getScreenShot(driver.findElement(By.id("logoDiv")));
		Thread.sleep(3000);
		userIcon.click();
		CaptureElementUtil.getInstance().getScreenShot(
				driver.findElement(By.cssSelector("#userProfilePopup > div > div > div.ao-modal-body > table")));
		
		//GetTableOld tableOld = new GetTableOld(driver,"//*[@id=\"userProfilePopup\"]/div/div/div[2]/table");
		//String text0 = tableOld.getCellText(1, 3);
		//logger.info("表格信息为{}", text0);
		
/*		GetTableNew tableO = new GetTableNew(driver, "//*[@id=\"userProfilePopup\"]/div/div/div[2]/table");
		String text = tableO.xpathCellText(1, 3);
		String text2 = tableO.xpathCellText(3, 3);
		logger.info("表格信息为{}", text);
		logger.info("表格2信息为{}", text2);
		
		GetTableNew table1 = new GetTableNew(driver, "#userProfilePopup > div > div > div.ao-modal-body > table");
		String text3 = table1.cssCellText(1, 3);
		String text4 = table1.cssCellText(3, 3);
		logger.info("表格CSS信息为{}", text3);
		logger.info("表格2CSS信息为{}", text4);*/
		
		GetTableBy getTableBy = new GetTableBy(driver);
		String text5 = getTableBy.getCellText(By.cssSelector("#userProfilePopup > div > div > div.ao-modal-body > table"), 0, 0, "tbody");
		logger.info("getTabltBy表格信息为：{}",text5);

		try {
			TimeUnit.SECONDS.sleep(2);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		return PageFactory.initElements(driver, Login.class);
	}

	public boolean isLoggedIn() {
		boolean result = true;
		// check if input search box can be located
		try {
			// new
			// WebDriverWait(driver,5).until(ExpectedConditions.visibilityOf(searchAttrInput));
		} catch (org.openqa.selenium.TimeoutException e) {
			result = false;
		}
		return result;
	}

	public String getErrorMsg() {
		return errorMsg.getText();
	}

	public Login logout() {
		userIcon.click();
		logout.click();
		return this;
	}
}
