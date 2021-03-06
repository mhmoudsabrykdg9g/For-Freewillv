package ftnt.qa.autotest.ui.framework.testng.listener;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.List;

import org.apache.commons.configuration.ConfigurationException;
import org.openqa.selenium.WebDriver;
import org.testng.IAnnotationTransformer;
import org.testng.IReporter;
import org.testng.IRetryAnalyzer;
import org.testng.ISuite;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;
import org.testng.annotations.ITestAnnotation;
import org.testng.xml.XmlSuite;
/**
 * 三大监听器合并实现
 * @see 依赖以下3个类：ReportListener，RetryToRunCase，ScreenShotOnFailure
 * @author Lei.Wu
 * @date 2018-3-12
 * @version FTNT-UI-AutoTest Version 1.0
 * @since Jdk1.8
 */
public class TestNGListener extends TestListenerAdapter implements IReporter,IAnnotationTransformer {
	public static WebDriver driver;

	public static WebDriver getDriver() {
		return driver;
	}

	public static void setDriver(WebDriver driver) {
		TestNGListener.driver = driver;
	}

	@Override
	public void onTestFailure(ITestResult result) {
		super.onTestFailure(result);
		ScreenShotOnFailure screenshot = new ScreenShotOnFailure(driver, result);
		try {
			screenshot.getScreenShot();
		} catch (ConfigurationException e) {
			e.printStackTrace();
		}
	}
	
//	@Override //使用Emailalbe report 的源码设计的监听器
//	public void generateReport(List<XmlSuite> xmlSuites, List<ISuite> suites, String outputDirectory) {
//		ReportListener reportListener=new ReportListener();
//		reportListener.generateReport(xmlSuites, suites, outputDirectory);
//		
//	}
	
	@Override //自定义的监听器,含有打包测试结果并发送测试邮件的功能
	public void generateReport(List<XmlSuite> xmlSuites, List<ISuite> suites, String outputDirectory) {
		SimpleReportListener reportListener=new SimpleReportListener();
		reportListener.generateReport(xmlSuites, suites, outputDirectory);
	}

    @Override
    @SuppressWarnings("rawtypes")
    public void transform(ITestAnnotation iTestAnnotation, Class aClass, Constructor constructor, Method method) {
        IRetryAnalyzer iRetryAnalyzer= iTestAnnotation.getRetryAnalyzer();
        if(iRetryAnalyzer==null){
            iTestAnnotation.setRetryAnalyzer(RetryToRunCase.class);
        }
    }

}
