package listner;


import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;


import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;

import utilityPackage.Extent_Reporter_PP;
import utilityPackage.ScreenShotPP;

public class ListnerClass implements ITestListener {

	
	String testName;
	ExtentReports extentReport;
	static ExtentTest extentTest;
	WebDriver driver;
	
	

	
	
	@Override  //Test start under Suite
	public void onStart(ITestContext context) { 
		System.out.println("This is the test Scenario : OnStart " + context.getName());
		extentReport = Extent_Reporter_PP.generateExtentReports();
	}
	
	
	@Override //Test Method Starts
	public void onTestStart(ITestResult result) {
		testName = result.getName();
		extentTest = extentReport.createTest(testName); //Generate the entry of testName in Extent Report
		extentTest.log(Status.INFO, testName+ " : Started Executing");
       
	}

	@Override  //Test Method Success
	public void onTestSuccess(ITestResult result) {
		testName = result.getName();
		extentTest = extentReport.createTest(testName); //Generate the entry of testName in Extent Report
		extentTest.log(Status.PASS, MarkupHelper.createLabel("Name of the PASSED Test Case is: " + testName, ExtentColor.GREEN));
		
		// Use the provided code to get the WebDriver instance
		try {
            WebDriver driver = (WebDriver) result.getTestClass().getRealClass().getDeclaredField("driver").get(result.getInstance());
            if(driver!=null) {
            	this.driver = driver;
            	System.out.println("System taken driver object");
            	extentTest.pass("ScreenShot : ", MediaEntityBuilder.createScreenCaptureFromBase64String("data:image/png;base64," + ScreenShotPP.getScreenShotAsBase64(driver)).build());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
	}

	@Override  //Test Method Failure
	public void onTestFailure(ITestResult result) {
		testName = result.getName();
		extentTest.log(Status.FAIL, MarkupHelper.createLabel("Name of the FAILED Test Case is: " + testName, ExtentColor.RED));
		extentTest.log(Status.INFO, testName+ " : Test Case Failed " + result.getThrowable());
		
		// Use the provided code to get the WebDriver instance
        try {
            WebDriver driver = (WebDriver) result.getTestClass().getRealClass().getDeclaredField("driver").get(result.getInstance());
            if(driver!=null) {
            	this.driver = driver;
            	System.out.println("System taken driver object");
            	extentTest.fail("ScreenShot : ", MediaEntityBuilder.createScreenCaptureFromBase64String("data:image/png;base64," + ScreenShotPP.getScreenShotAsBase64(driver)).build());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
       
     
	
        
	}


	@Override //Test Method Skipped
	public void onTestSkipped(ITestResult result) {
		
		testName = result.getName();
		extentTest = extentReport.createTest(testName); //Generate the entry of testName in Extent Report
		extentTest.log(Status.SKIP, MarkupHelper.createLabel("Name of the SKIPPED Test Case is: " + testName, ExtentColor.ORANGE));
	
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		System.out.println("This is the test Scenario : OnTestFailedButWithinSuccessPercentage " + result.getName());
	}

	@Override //Test Method FailedWithTimeOut
	public void onTestFailedWithTimeout(ITestResult result) {
		System.out.println("This is the test Scenario : OnTestFailedWithTimeout " + result.getName());
	}

	

	@Override //Test Finish under Suite
	public void onFinish(ITestContext context) {
		
		extentReport.flush();
	}

	
	
}
