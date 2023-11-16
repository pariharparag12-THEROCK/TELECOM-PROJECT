package basePackage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Base {

	
	public WebDriver openChromeBrowser() {
	//	System.setProperty("webdriver.chrome.driver", "E:\\AUTOMATION\\chromedriver-win64\\chromedriver.exe");
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		System.out.println("Chrome Browser Launched Successfully");
		driver.manage().deleteAllCookies();
		return driver;
		
	}
	
	public WebDriver openFirefoxBrowser() {
	//	System.setProperty("webdriver.gecko.driver", "E:\\AUTOMATION\\geckodriver-v0.33.0-win64\\geckodriver.exe");
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new FirefoxDriver();
		driver.manage().window().maximize();
		System.out.println("Firefox Browser Launched Successfully");
		driver.manage().deleteAllCookies();
		return driver;
		
	}
	
}
