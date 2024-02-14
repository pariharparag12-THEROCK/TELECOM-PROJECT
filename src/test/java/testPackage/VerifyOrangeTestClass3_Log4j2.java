package testPackage;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.bouncycastle.oer.its.ieee1609dot2.basetypes.PublicVerificationKey;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import basePackage.Base;
import pomPackage.HomePagePractice2;
import pomPackage.LoginPageOrange;

public class VerifyOrangeTestClass3_Log4j2 extends Base {

	public static Logger logger;

	//private WebDriver driver;
	public WebDriver driver; //As Listner class not taking the object of WebDriver if it is private....
	
	private LoginPageOrange loginPageOrange;
	private HomePagePractice2 homePageOrange;
	
	SoftAssert softassert;
	

	
	@BeforeTest
	@Parameters ("browser1")
	public void launchBrowser(String Browser) {
		
		if(Browser.equals("Chrome")) {
			driver = openChromeBrowser();
		}
		
		/*if(Browser.equals("Firefox")) {
			driver = openFirefoxBrowser();
			
			
			
		}*/
		
		logger = Logger.getLogger("Symworld");
		
		PropertyConfigurator.configure("log4j.properties");
		
		logger.info("Browser Launched Successfully");
		
		
	}
	
	
	


	@BeforeClass
	public void launchOrangeHRMApplication() {
		
		//driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
		
		driver.navigate().to("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
		
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		
		logger.info("Navigated to Application Successfully");
		
		//System.out.println("Application Launched Successfully" +"\n");
		//log.info("Application Launched Successfully");

		loginPageOrange = new LoginPageOrange(driver);
		homePageOrange = new HomePagePractice2(driver);
		
		softassert = new SoftAssert();
		
	}
	
	
	@BeforeMethod
	public void LoginOrangeHRM() throws InterruptedException, IOException {
		
		loginPageOrange.sendUsername();
		logger.info("username entered successfully");
		
		loginPageOrange.sendPassword();
		logger.info("password entered successfully");
		
		loginPageOrange.clickOnLoginButton();
		logger.info("login button clicked successfully");
		
	}
	
	
	@Test (priority =-1)
	public void VerifyAdminOption() throws InterruptedException {
		
		
		String actURL1 = driver.getCurrentUrl();
		Assert.assertEquals(actURL1, "https://opensource-demo.orangehrmlive.com/web/index.php/dashboard/index");
		
		homePageOrange.clickOnAdminOption();
		logger.info("Admin Option clicked successfully");
		
		
		String actUrl2 =  driver.getCurrentUrl();
		String expUrl2 = "https://opensource-demo.orangehrmlive.com/web/index.php/admin/viewSystemUsers";
		
		softassert.assertEquals(actUrl2, expUrl2);
		logger.info("Admin option URL matched successfully");
		
		String tittle1 = driver.getTitle();
		
		boolean result = tittle1.equalsIgnoreCase("OrangeHRM");
		softassert.assertTrue(result);
		logger.error("Admin option Title matched successfully");
		
		
		softassert.assertAll();
		Reporter.log("Tc is passed", true);
	}
	
	
	
	@Test (priority = 1)
	public void VerifyAccountOptionDropDown() throws InterruptedException {
		
		homePageOrange.clickOnAccountDropDown();
		logger.info("AccountOptions dropdown clicked successfully");
		String VersionValue =  homePageOrange.getAccountDropDownOptions();
		
		if (VersionValue.equals("OrangeHRM OS 5.6")) {
			logger.info("VersionValue matched : successfully " + VersionValue );
			softassert.assertTrue(true);
			
			Reporter.log("Tc is passed", true);
		}
		
		else {
			logger.error("VersionValue not matched" );
			softassert.assertTrue(false);
			
			Reporter.log("Tc is failed", false);
		}
		
		
		homePageOrange.clickOnCrossIcon();
		logger.info("User closed About Window");
		
		softassert.assertAll();
	}
	
	
	@Test (priority =2)
	public void verifyEmployeeTableDATA() throws InterruptedException {
		
		homePageOrange.clickOnAdminOption();
		logger.info("Admin Option clicked successfully");
		Thread.sleep(2000);
		int EmployestatusCount = homePageOrange.findemployeeAdminTable();
		
		if (EmployestatusCount!=0) {
			
			softassert.assertNotNull(EmployestatusCount);
			logger.info("Employee status having some value");
			Reporter.log("Tc is passed", true);
		}
		
		
		else {
			softassert.assertNotNull(EmployestatusCount);
			logger.info("Employee status having null value");
			Reporter.log("Tc is failed", false);
		}
	}
	
	
	
	

	
	@AfterMethod
	public void logoutOrangeHRM() throws InterruptedException {

		homePageOrange.clickOnAccountOptionsDropDown();
		logger.info("Clicked on Account Option");
		homePageOrange.clickOnLogoutOption();
		logger.info("Application Logged-Out Successfully");
		//System.out.println("Application Logged-Out Successfully" +"\n");
	}
	
	@AfterClass
	public void cleanObjects() {
		loginPageOrange = null;
		homePageOrange = null; 
		softassert = null;
		System.out.println("Objects are cleaned Successfully");
	}
	
	@AfterTest
	public void closedBrowser() {
		driver.quit();
		driver = null;
		System.gc();
		System.out.println("Browser closed successfully");
		logger.info("Browser closed successfully");
	}
	
}
