package testPackage;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;



import basePackage.Base;
import pomPackage.HomePageOrange;
import pomPackage.LoginPageOrange;
import utilityPackage.Utils;



public class VerifyOrangeTestClass2_Log4j extends Base {

	
	public static Logger logger;
	
	//private WebDriver driver;
	public WebDriver driver; //As Listner class not taking the object of WebDriver if it is private....
	
	private LoginPageOrange loginPageOrange;
	private HomePageOrange homePageOrange;
	
	private SoftAssert softassert;
	

	
	@BeforeTest
	@Parameters ("browser1")
	public void launchBrowser(String Browser) {
		
		if(Browser.equals("Chrome")) {
			driver = openChromeBrowser();
		}
		
		/*if(Browser.equals("Firefox")) {
			driver = openFirefoxBrowser();
			
			
			helloo parag how arwe u git check
			Februrary.....ok
			15 feb
			
		}*/
		
		
		logger = logger.getLogger("Symworld"); // logger se project configure hua
		
		// logger always use in test class
		
		PropertyConfigurator.configure("log4j.properties"); // log file configure hui
		
		logger.info("BROWSER CHROME LAUCHED");
	}
	
	
	@BeforeClass
	public void launchOrangeHRMApplication() {
		
		
		driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		System.out.println("Application Launched Successfully" +"\n");
		logger.info("Application Launched Successfully");

		loginPageOrange = new LoginPageOrange(driver);
		homePageOrange = new HomePageOrange(driver);
		
		softassert = new SoftAssert();
		
	}
	
	
	@BeforeMethod
	public void LoginOrangeHRM() throws InterruptedException, IOException {
		
		loginPageOrange.sendUsername();
		logger.info("User entered username");
		loginPageOrange.sendPassword();
		logger.info("User entered password");
		loginPageOrange.clickOnLoginButton();
		logger.info("User clicked on login button");
		
		System.out.println("User Clicked on login button");
	}
	
	
	@Test ( priority = -1)
	public void verifyAdminOption() throws InterruptedException {
	
		String actUrl1 = driver.getCurrentUrl();
		Assert.assertEquals(actUrl1, "https://opensource-demo.orangehrmlive.com/web/index.php/dashboard/index");
		
		System.out.println("Application Logged-In Successfully" +"\n");
		
		homePageOrange.clickOnAdminOption();
		
		String actUrl2 = driver.getCurrentUrl();
		
		boolean result = actUrl2.equalsIgnoreCase("https://opensource-demo.orangehrmlive.com/web/index.php/admin/viewSystemUsers");
		
		softassert.assertTrue(result);
	
		String actUrl3 = driver.getTitle();
		
		softassert.assertEquals(actUrl3, "OrangeHRM");
		
		System.out.println("User clicked on AdminOption Successfully" + "\n" +"Tittle : " + actUrl3 + "\n" +"Url : " + actUrl2 +"\n" );
		
		softassert.assertAll();
	}
	
	
	@Test (priority = 1 )
	public void verifyAboutOption() throws InterruptedException {
		
		homePageOrange.clickOnAccountOptionsDropDown();
		
		String companyNameValue = homePageOrange.getOptionsONAccountOptionsDropDown(); //Actual value
		
		softassert.assertEquals(companyNameValue, "OrangeHRM");
		
		System.out.println("Company Name - OrangeHRM matched : " + companyNameValue);
		
		Thread.sleep(1000);
				
		homePageOrange.clickOnAboutCrossIcon();
				
		System.out.println("About Window Closed");

		softassert.assertAll();
	
	}
	
	@Test (priority=2, dependsOnMethods = "verifyAboutOption")
	public void verifyEmployeeTableData() throws InterruptedException {
		
		logger.warn("User should be clicked on Admin Option berfore navigating to admin table");
		homePageOrange.clickOnAdminOption();
		int statuscount = homePageOrange.findEmployeeTableData();
		Assert.assertNotNull(statuscount);
		System.out.println("Employees Enabled Status Count in table is : " + statuscount + "\n");
		logger.error("verifyEmployeeTableData TEST CASE is Passed");
		
	}
	
	

	
	@AfterMethod
	public void logoutOrangeHRM() throws InterruptedException {

		homePageOrange.clickOnAccountOptionsDropDown();
		homePageOrange.clickOnLogoutOption();
		System.out.println("Application Logged-Out Successfully" +"\n");
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
