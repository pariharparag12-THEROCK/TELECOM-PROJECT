package pomPackage;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;



import org.openqa.selenium.support.ui.WebDriverWait;


public class HomePageOrange {

	WebDriver driver;
	
	@FindBy (xpath = "//a[@href='/web/index.php/auth/logout']")
	private WebElement logoutOption;
	

//	@FindBy (xpath = "//p[text()='John Doe']")
//	private WebElement accountOptions;
	
	@FindBy (xpath = "//p[contains(@class,'oxd-userdropdown-name')]")
	private WebElement accountOptions;
	
	@FindBy(xpath = "(//div//div//p[@class='oxd-text oxd-text--p orangehrm-about-text'])[1]")
	private WebElement companyNameValue;
	
	
	@FindBy (xpath ="//button[@class='oxd-dialog-close-button oxd-dialog-close-button-position']")
	private WebElement aboutCrossIcon;
	
	@FindBy (xpath = "//span[text()='Admin']")
	private WebElement adminOption;

	
	private WebDriverWait wait;
	
	
	public HomePageOrange (WebDriver driver) {
		
		this.driver = driver;
		wait = new WebDriverWait(driver, 20);
		PageFactory.initElements(driver, this);
	}
	
	
	public void clickOnAccountOptionsDropDown() throws InterruptedException {
		wait.until(ExpectedConditions.visibilityOf(accountOptions));
		accountOptions.click();
		Thread.sleep(2000);
	}
	
	
	public String getOptionsONAccountOptionsDropDown() throws InterruptedException {
		
		List<WebElement> list = driver.findElements(By.xpath("//a[@role='menuitem']"));
		
		for(WebElement element : list) {
			element.getText().contentEquals("About");
			element.click();
			System.out.println(driver.getTitle() + " : Title");
		}
		
		
		wait.until(ExpectedConditions.visibilityOf(companyNameValue));
		
		Thread.sleep(4000);
		String companyNameValue1 = companyNameValue.getText();
		
		//boolean res = companyName.isDisplayed();
		
		return companyNameValue1;
		
	}
	
	
	public void clickOnAboutCrossIcon() throws InterruptedException {
		wait.until(ExpectedConditions.visibilityOf(aboutCrossIcon));
		aboutCrossIcon.click();
		Thread.sleep(2000);
	}
	
	
	public void clickOnLogoutOption() throws InterruptedException {
		
		wait.until(ExpectedConditions.visibilityOf(logoutOption));
		logoutOption.click();
		Thread.sleep(2000);
	}
	
	public void clickOnAdminOption() throws InterruptedException {
			
			wait.until(ExpectedConditions.visibilityOf(adminOption));
			adminOption.click();
			Thread.sleep(2000);
		}
	
	
}
