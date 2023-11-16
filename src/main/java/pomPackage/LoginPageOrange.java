package pomPackage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

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
	
	
	public void sendUsername() throws InterruptedException {
		wait.until(ExpectedConditions.visibilityOf(username));
		username.sendKeys("Admin");
		Thread.sleep(3000);
	}
	
	public void sendPassword() throws InterruptedException {
		wait.until(ExpectedConditions.visibilityOf(password));
		password.sendKeys("admin123");
		Thread.sleep(3000);
	}
	
	public void clickOnLoginButton() throws InterruptedException {
		wait.until(ExpectedConditions.visibilityOf(loginButton));
		loginButton.click();
		Thread.sleep(3000);
	}


	

	
}
