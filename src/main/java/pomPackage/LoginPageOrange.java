package pomPackage;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import utilityPackage.PropertyClass;

public class LoginPageOrange {

	WebDriver driver;
	
	private WebDriverWait wait;
	
	@FindBy (xpath = "//input[@name='username']")
	private WebElement username;
	
	@FindBy (xpath = "//input[@name='password']")
	private WebElement password;
	
	@FindBy (xpath = "//button[@type='submit']")
	private WebElement loginButton;
	

	
	
	public LoginPageOrange(WebDriver driver) {
		this.driver = driver;
		wait = new WebDriverWait(driver, 20);
		PageFactory.initElements(driver, this);
	}
	
	
	public void sendUsername() throws InterruptedException, IOException {
		wait.until(ExpectedConditions.elementToBeClickable(username));
		username.sendKeys(PropertyClass.readDataFromPropertyFile("USERNAME"));
		Thread.sleep(3000);
	}
	
	public void sendPassword() throws InterruptedException, IOException {
		wait.until(ExpectedConditions.elementToBeClickable(password));
		password.sendKeys(PropertyClass.readDataFromPropertyFile("PASSWORD"));
		Thread.sleep(3000);
	}
	
	public void clickOnLoginButton() throws InterruptedException {
		wait.until(ExpectedConditions.visibilityOf(loginButton));
		loginButton.click();
		Thread.sleep(3000);
	}


	

	
}
