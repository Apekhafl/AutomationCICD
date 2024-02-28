package selenium.TestComponents;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import selenium.resources.ExtentReportsNG;

public class Listeners implements ITestListener {
	BaseTest base=new BaseTest();
    ExtentTest test;
	ExtentReports extent = ExtentReportsNG.getReportObject();
    ThreadLocal<ExtentTest> extentTest=new ThreadLocal<ExtentTest>();
	@Override
	public void onTestStart(ITestResult result) {
		test = extent.createTest(result.getMethod().getMethodName());
		extentTest.set(test);
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		// TODO Auto-generated method stub
		extentTest.get().log(Status.PASS, "Test Passed");
	}

	@Override
	public void onTestFailure(ITestResult result) {
		extentTest.get().fail(result.getThrowable());
	       try {
	    	   base.driver = (WebDriver)result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
	       }catch(Exception e) {
	    	   e.printStackTrace();
	       }
	       String filepath = null;
	       try {
	    	   filepath=base.getScreenShot(result.getMethod().getMethodName(),base.driver);
	       }catch(Exception e) {
	    	   e.printStackTrace();
	       }
	       extentTest.get().addScreenCaptureFromPath(filepath, result.getMethod().getMethodName());
		}

	@Override
	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub
		ITestListener.super.onTestSkipped(result);
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		ITestListener.super.onTestFailedButWithinSuccessPercentage(result);
	}

	@Override
	public void onTestFailedWithTimeout(ITestResult result) {
		// TODO Auto-generated method stub
		ITestListener.super.onTestFailedWithTimeout(result);
	}

	@Override
	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
		ITestListener.super.onStart(context);
	}

	@Override
	public void onFinish(ITestContext context) {
		extent.flush();
	}

}
