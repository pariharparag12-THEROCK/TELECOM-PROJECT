package testPackage;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import com.github.dockerjava.api.model.Driver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class EE {

	
	public static void main(String[]args) throws InterruptedException {
		
		
		WebDriverManager.chromedriver().setup();
		
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://www.amazon.in");
		
		driver.navigate().refresh();
		
		driver.get("https://www.amazon.in/ref=nav_logo");
		
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		driver.findElement(By.xpath("//input[@id='twotabsearchtextbox']/following::a[text()='Amazon Pay'][1]")).click();
		
		Thread.sleep(3000);
		
		driver.close();
		
		
	}

}
