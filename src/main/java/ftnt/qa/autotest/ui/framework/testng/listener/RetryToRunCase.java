package ftnt.qa.autotest.ui.framework.testng.listener;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;
import org.testng.Reporter;
import ec.qa.autotest.ui.constants.CommonConstants;
import ftnt.qa.autotest.ui.testbase.TestBase;

/**
 * @author xin.wang 实现testng接口实现用例失败重跑
 */
public class RetryToRunCase implements IRetryAnalyzer {

	private int retryCount = 1;

	private static int maxRetryCount;

	public int getRetryCount() {
		return retryCount;
	}

	public static int getMaxRetryCount() {
		return maxRetryCount;
	}

	@SuppressWarnings("static-access")
	public RetryToRunCase() {
		this.maxRetryCount = CommonConstants.RETRY_COUNT;
	}

	public boolean retry(ITestResult result) {
		if (retryCount <= maxRetryCount) {
			Reporter.setCurrentTestResult(result);
			TestBase.success = false;
			retryCount++;
			return true;
		}
		return false;
	}
}
