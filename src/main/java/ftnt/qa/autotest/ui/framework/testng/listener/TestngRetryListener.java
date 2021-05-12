package ftnt.qa.autotest.ui.framework.testng.listener;

import java.io.IOException;
import java.lang.reflect.Constructor;  
import java.lang.reflect.Method;  
import java.util.ArrayList;  
import java.util.Arrays;  
import java.util.HashSet;  
import java.util.Iterator;  
import java.util.Set;  
import org.testng.IRetryAnalyzer;  
import org.testng.ITestContext;  
import org.testng.ITestListener;  
import org.testng.ITestResult;  
import org.testng.annotations.ITestAnnotation;  

public class TestngRetryListener implements ITestListener {

	@Override
	public void onTestFailure(ITestResult result) {
		try {
			ScreenShotOnFailure.takeScreentShot();
			System.out.println(result.getMethod().getMethodName() + " failed, the screenshot saved in "
					+ ScreenShotOnFailure.getScreenShotPath() + " screenshot name : "
					+ ScreenShotOnFailure.getScreenShotName());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings("rawtypes")
	public void transform(ITestAnnotation annotation, Class testClass, Constructor testConstructor, Method testMethod) {
		IRetryAnalyzer retry = annotation.getRetryAnalyzer();
		if (retry == null) {
			annotation.setRetryAnalyzer(RetryToRunCase.class);
		}
	}

	@Override
	public void onFinish(ITestContext testContext) {
		ArrayList<ITestResult> testsToBeRemoved = new ArrayList<ITestResult>();
		Set<Integer> passedTestIds = new HashSet<Integer>();
		for (ITestResult passedTest : testContext.getPassedTests().getAllResults()) {
			passedTestIds.add(getId(passedTest));
		}

		Set<Integer> failedTestIds = new HashSet<Integer>();
		for (ITestResult failedTest : testContext.getFailedTests().getAllResults()) {
			int failedTestId = getId(failedTest);
			if (failedTestIds.contains(failedTestId) || passedTestIds.contains(failedTestId)) {
				testsToBeRemoved.add(failedTest);
			} else {
				failedTestIds.add(failedTestId);
			}
		}

		for (Iterator<ITestResult> iterator = testContext.getFailedTests().getAllResults().iterator(); iterator
				.hasNext();) {
			ITestResult testResult = iterator.next();
			if (testsToBeRemoved.contains(testResult)) {
				iterator.remove();
			}
		}
		
	}
	
    private int getId(ITestResult result) {  
        int id = result.getTestClass().getName().hashCode();  
        id = id + result.getMethod().getMethodName().hashCode();  
        id = id + (result.getParameters() != null ? Arrays.hashCode(result.getParameters()) : 0);  
        return id;  
    }  
    
	@Override
	public void onTestStart(ITestResult result) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onTestSuccess(ITestResult result) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub

	}
}