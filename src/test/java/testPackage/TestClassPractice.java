package testPackage;

import java.io.IOException;
import java.util.concurrent.TimeUnit;


import org.openqa.selenium.WebDriver;
import org.testng.Assert;
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

import pomPackage.HomePagePractice1;
import pomPackage.LoginPageOrange;



public class TestClassPractice extends Base {

	//private WebDriver driver;
	public WebDriver driver; //As Listner class not taking the object of WebDriver if it is private....
	
	private LoginPageOrange loginPageOrange;
	private HomePagePractice1 homePageOrange;
	
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
		}*/
	}
	
	
	@BeforeClass
	public void launchOrangeHRMApplication() {
		
		
		driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		System.out.println("Application Launched Successfully" +"\n");

		loginPageOrange = new LoginPageOrange(driver);
		homePageOrange = new HomePagePractice1(driver);
		
		softassert = new SoftAssert();
		
	}
	
	
	@BeforeMethod
	public void LoginOrangeHRM() throws InterruptedException, IOException {
		
		loginPageOrange.sendUsername();
		loginPageOrange.sendPassword();
		loginPageOrange.clickOnLoginButton();
		
		System.out.println("User Clicked on login button");
	}
	
	@Test (priority = -1)
	public void verifyAdminOption() throws InterruptedException {
		
		String actURl1 = driver.getCurrentUrl();
		
		Assert.assertEquals(actURl1, "https://opensource-demo.orangehrmlive.com/web/index.php/dashboard/index");
		
		System.out.println("Application logged-In succesfully");
		
		homePageOrange.clickOnAdminOption();
		
		String actURL2 = driver.getCurrentUrl();
		
		boolean result = actURL2.equalsIgnoreCase("https://opensource-demo.orangehrmlive.com/web/index.php/admin/viewSystemUsers");
		
		softassert.assertTrue(result);
		
		String tittle = driver.getTitle();
		
		softassert.assertEquals(tittle, "OrangeHRM");
		
		System.out.println("User clicked on AdminOption Successfully" + "\n" +"Tittle : " + tittle + "\n" +"Url : " + actURL2 +"\n" );
		
		softassert.assertAll();
		
	}
	
	
	@Test (priority = 1)
	public void verifyAboutWindow() throws InterruptedException {
		
		homePageOrange.clickOnAccoutOptions();
		String actVersionValue = homePageOrange.getOptionsOnAccountDropDown();
		
		softassert.assertEquals(actVersionValue, "OrangeHRM OS 5.5");
		
		Thread.sleep(2000);
		
		System.out.println("Actual Version Value is" + actVersionValue );
		
		homePageOrange.clickOnCrossIconOnAccountWindow();
		
		System.out.println("User closed Account WINDOW" );
		
		Thread.sleep(3000);
		
		softassert.assertAll();
	}
	
	@Test (priority = 2)
	public void verifyEmployeeTableData() throws InterruptedException {
		
		homePageOrange.clickOnAdminOption();
		
		int statusCount1 =	homePageOrange.findEmployeeAdminTableData();
		
		softassert.assertNotNull(statusCount1);
		
		System.out.println("Employee status Enabled Count would be : "+ statusCount1);
		
		softassert.assertAll();
		
	}
	
	

	
	@AfterMethod
	public void logoutOrangeHRM() throws InterruptedException {

		homePageOrange.clickOnAccoutOptions();
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
	}
	
}
