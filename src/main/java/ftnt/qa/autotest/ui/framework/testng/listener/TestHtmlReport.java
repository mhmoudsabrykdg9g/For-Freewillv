package ftnt.qa.autotest.ui.framework.testng.listener;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Date;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import ftnt.qa.autotest.ui.framework.utils.PropertiesUtil;

public class TestHtmlReport implements ITestListener   { 
	private static String reportPath; 
	
	@Override
	public void onStart(ITestContext context) {
		File htmlReportDir = new File("test-output/html-report");  
        if (!htmlReportDir.exists()) {  
            htmlReportDir.mkdirs();  
        }  
        String reportName = String.valueOf(new Date().getTime())+".html";  
        reportPath = htmlReportDir+"/"+reportName;  
        File report = new File(htmlReportDir,reportName);  
        if(report.exists()){  
                try {  
                    report.createNewFile();  
                } catch (IOException e) {  
                    e.printStackTrace();  
                }  
        }  
        String msg = "<html><head><meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" /><meta name=\"author\" content=\"ec-qa:xin.wang\"><meta name=\"description\" content=\"自动化测试用例结果列表\"><title>UI自动化测试报告</title></head><body STYLE=\"background-color:#00CCCC;\" align=\"center\"><br><a style=\"font-weight:bold;\">测试用例运行结果列表</a><br><br><table width=\"90%\" height=\"80\" border=\"1\" align=\"center\" style=\"table-layout:fixed;\"><thead><tr><th>测试用例名</th><th>测试用例结果</th></tr></thead><tbody style=\"word-wrap:break-word;font-weight:bold;\">";  
        try {  
            Files.write((Paths.get(reportPath)),msg.getBytes("utf-8"));  
        } catch (IOException e) {  
            e.printStackTrace();  
        }   
		
	}

	@Override
	public void onTestStart(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTestSuccess(ITestResult result) {
        StringBuilder caseRes = new StringBuilder("<tr><td>");  
        caseRes.append(result.getMethod().getRealClass()+"."+result.getMethod().getMethodName());  
        caseRes.append("</td><td>Passed</td></tr>");  
        String res = caseRes.toString();  
        try {  
            Files.write((Paths.get(reportPath)),res.getBytes("utf-8"),StandardOpenOption.APPEND);  
        } catch (IOException e) {  
            e.printStackTrace();  
        }  
	}

	@Override
	public void onTestFailure(ITestResult result) {
		String screenShotTip = null;  
        try {  
            if (PropertiesUtil.getProValue("testcase.failedcase.screenShotEnabled").equals("true")) {  
                ScreenShotOnFailure.takeScreentShot();  
                screenShotTip= result.getMethod().getMethodName() + " failed, the screenshot saved in "  
                        + ScreenShotOnFailure.getScreenShotPath() + " screenshot name : "  
                        + ScreenShotOnFailure.getScreenShotName();  
                System.out.println(screenShotTip);  
            }  
        } catch (IOException e) {  
            e.printStackTrace();  
        }  
          
        StringBuilder caseRes = new StringBuilder("<tr><td>");  
        caseRes.append(result.getMethod().getRealClass()+"."+result.getMethod().getMethodName());  
        caseRes.append("</td><td><br>");  
        caseRes.append(screenShotTip);  
        caseRes.append("<br><br>");  
        Throwable throwable = result.getThrowable();  
        caseRes.append("测试用例 运行失败，原因： ");  
        caseRes.append(throwable.getMessage());  
        caseRes.append("<br><br>");  
        StackTraceElement[] se = throwable.getStackTrace();  
        caseRes.append("堆栈信息:");  
        caseRes.append("<br>");  
        for (StackTraceElement e : se) {  
            caseRes.append(e.toString());  
            caseRes.append("<br>");  
        }  
        caseRes.append("</td></tr>");  
        String res = caseRes.toString();  
        try {  
            Files.write((Paths.get(reportPath)),res.getBytes("utf-8"),StandardOpenOption.APPEND);  
        } catch (IOException e) {  
            e.printStackTrace();  
        }  
	}

	@Override
	public void onTestSkipped(ITestResult result) {
        StringBuilder caseRes = new StringBuilder("<tr><td>");  
        caseRes.append(result.getMethod().getRealClass()+"."+result.getMethod().getMethodName());  
        caseRes.append("</td><td>Skipped</td></tr>");  
        String res = caseRes.toString();  
        try {  
            Files.write((Paths.get(reportPath)),res.getBytes("utf-8"),StandardOpenOption.APPEND);  
        } catch (IOException e) {  
            e.printStackTrace();  
        }  
		
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void onFinish(ITestContext context) {
		String msg = "</tbody></table></body></html>";  
        try {  
            Files.write((Paths.get(reportPath)),msg.getBytes("utf-8"),StandardOpenOption.APPEND);  
        } catch (IOException e) {  
            // TODO Auto-generated catch block  
            e.printStackTrace();  
        }  
  
	}

}
