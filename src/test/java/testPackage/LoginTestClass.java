package testPackage;


import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import basePackage.Base;
import pomPackage.HomePageOrange;
import pomPackage.LoginPageOrange;
import utilityPackage.ScreenShotPP;
import utilityPackage.Utils;


public class LoginTestClass extends Base{

	//WebDriver driver;
	public WebDriver driver; //As Listner class not taking the object of WebDriver if it has default modifier....
	
	//private LoginPageOrange loginPageOrange;
	//private HomePageOrange homePageOrange;
	
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
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		System.out.println("Application Launched Successfully" +"\n");

//		loginPageOrange = new LoginPageOrange(driver);
//		homePageOrange = new HomePageOrange(driver);
//		
		softassert = new SoftAssert();
		
	}
	
	
	@Test(dataProvider = "LoginData")
	public void loginTest(String user, String pwd, String exp) throws InterruptedException, IOException {
		
		driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
		
		WebElement username = driver.findElement(By.xpath("//input[@name='username'] "));
		username.clear();
		username.sendKeys(user);
		
		WebElement password = driver.findElement(By.xpath("//input[@name='password']"));
		password.clear();
		password.sendKeys(pwd);
		
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		Thread.sleep(2000);
		
		String expectedURL = "https://opensource-demo.orangehrmlive.com/web/index.php/dashboard/index";
		String actualURL = driver.getCurrentUrl();
		
		if(exp.equals("Valid")) {
			if(expectedURL.equals(actualURL)) {
				Thread.sleep(3000);
				driver.findElement(By.xpath("//p[contains(@class,'oxd-userdropdown-name')]")).click();
				//ScreenShotPP.getScreenShotPP(driver);
				driver.findElement(By.xpath("//a[@href='/web/index.php/auth/logout']")).click();
				Assert.assertTrue(true);
			}
			else {
				Assert.assertTrue(false);
			}
		}
		
		else if(exp.equals("Invalid")) {
			if(expectedURL.equals(actualURL)) {
				Thread.sleep(3000);
				driver.findElement(By.xpath("//p[contains(@class,'oxd-userdropdown-name')]")).click();
				//ScreenShotPP.getScreenShotPP(driver);
				driver.findElement(By.xpath("//a[@href='/web/index.php/auth/logout']")).click();
				Assert.assertTrue(false);
			}
			else {
				Assert.assertTrue(true);
				//ScreenShotPP.getScreenShotPP(driver);
			}
		}
	
		
	}
	

	@DataProvider(name= "LoginData")
	public String[][] getData() throws EncryptedDocumentException, IOException {
		
		/*String loginData[][] = {
				{"Admin", "admin123", "Valid"},
				{"admin", "admin124", "Invalid"},
				{"Admin1", "Admin127", "Invalid"},
				{"admin", "Admin23", "Invalid"}
				
		};	*/
		
		
		String path = ".\\src\\main\\resources\\Login_DATA.xlsx";
		
		Utils utils = new Utils(path);
		
		int totalrows = utils.getRowCount("Sheet1") + 1; //totalrows = rowcount      // utils.getRowCount("Sheet1");
		int totalcolumns = 	utils.getCellCount("Sheet1", 0); //totalcolumns = cellcount
		System.out.println("Total rows in the excel sheet is: " + totalrows);
		System.out.println("Total columns in the excel sheet is: " + totalcolumns);
		
		
		String loginData[][] = new String[totalrows-1][totalcolumns];   //String[totalrows][totalcolumns];
		
		for(int i=1; i<=totalrows-1; i++) {								//for(int i=1; i<=totalrows; i++) {	
			for(int j=0 ; j<=totalcolumns-1; j++) {
			loginData[i-1][j] = utils.getCellDataString("Sheet1", i, j);
			}
		}
		
		
		return loginData;
	}
	
	
	@AfterClass
	public void cleanObjects() {
//		loginPageOrange = null;
//		homePageOrange = null; 
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
