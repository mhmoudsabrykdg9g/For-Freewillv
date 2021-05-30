package ftnt.qa.autotest.ui.fortisiem.testcases;

import org.openqa.selenium.support.PageFactory;
import ftnt.qa.autotest.ui.framework.testng.listener.RetryToRunCase;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import ftnt.qa.autotest.ui.fortisiem.pageobject.Login;
import ftnt.qa.autotest.ui.testbase.TestBase;

/**
 * Created by ssuku on 1/9/17.
 */
public class LoginTests extends BaseTest {

    // Login test
    @Parameters({"username", "password", "custId", "domain"})
    @Test(retryAnalyzer=RetryToRunCase.class)
    public void testLogin(String username, String password, String custId, String domain)  {
        Login loginObj = PageFactory.initElements(driver, Login.class);
        loginObj.login(username, password, custId, domain);
        Assert.assertTrue(loginObj.isLoggedIn(), "login failed");
    }

    // Login test with wrong credentials
    @Test(retryAnalyzer=RetryToRunCase.class)
    public void testLoginWrongCred()  {
        Login loginObj = PageFactory.initElements(driver, Login.class);
        loginObj.login("admin", "", "super", "LOCAL");
        Assert.assertEquals(loginObj.getErrorMsg(), "Invalid username or password.");
    }

    // Login test with invalid customer name
    @Test(retryAnalyzer=RetryToRunCase.class)
    public void testLoginInvalidCustomer()  {
        Login loginObj = PageFactory.initElements(driver, Login.class);
        loginObj.login("admin", "", "test", "LOCAL");
        Assert.assertEquals(loginObj.getErrorMsg(), "Invalid customer name: test");
    }


    // Logout test
    @Parameters({"username", "password", "custId", "domain"})
    @Test(retryAnalyzer=RetryToRunCase.class)
    public void testLogout(String username, String password, String custId, String domain) {
        Login loginObj = PageFactory.initElements(driver, Login.class);
        loginObj.login(username, password, custId, domain);
        Assert.assertTrue(loginObj.isLoggedIn(), "login failed");
        loginObj.logout();
        Assert.assertFalse(loginObj.isLoggedIn(), "logout failed");
    }
}
