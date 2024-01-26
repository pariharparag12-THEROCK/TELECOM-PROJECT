package pomPackage;

import java.util.List;

import org.bouncycastle.asn1.mozilla.PublicKeyAndChallenge;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;


import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePagePractice1 {

	WebDriver driver;
	
	private WebDriverWait wait;
	
	
	@FindBy (xpath = "//a[@href='/web/index.php/auth/logout']")
	private WebElement logoutOption;
	
	@FindBy (xpath ="//span[text()='Admin']")
	private WebElement admin;
	
	@FindBy  (xpath="//p[contains(@class,'oxd-userdropdown-name')]")
	private WebElement accountOptions;
	

	@FindBy (xpath = "(//div//p[text()='OrangeHRM OS 5.5'])[2]")
	private WebElement version;
	
	@FindBy (xpath = "//button[@class='oxd-dialog-close-button oxd-dialog-close-button-position']")
	private WebElement crossIcon;
	
	@FindBy (xpath = "//div[@role='row']")
	private List<WebElement> rows;
	
	@FindBy (xpath = "(//div[@role='row'])[2]//div[@role='cell']")
	private List<WebElement> columns;
	
	
	
	public HomePagePractice1 (WebDriver driver) {
		
		this.driver = driver;
		wait = new WebDriverWait(driver, 20);
		PageFactory.initElements(driver, this);
	}
	
	
	
	public void clickOnAdminOption() throws InterruptedException {
		
		wait.until(ExpectedConditions.elementToBeClickable(admin));
		admin.click();
		Thread.sleep(3000);
	}
	
	
	public void clickOnAccoutOptions() throws InterruptedException {
		
		wait.until(ExpectedConditions.elementToBeClickable(accountOptions));
		accountOptions.click();
		Thread.sleep(2000);
	}
	
	
	
	
	public String getOptionsOnAccountDropDown() {
		
		
	List<WebElement> list =	driver.findElements(By.xpath("//a[@role ='menuitem']"));
		
	int length = list.size();
	
	System.out.println(length);
	System.out.println(list);
		
	
	for (WebElement element: list) {
		
		if(element.getText().contentEquals("About")) {
			
			System.out.println(element.getText());
			element.click();
			System.out.println("USER CLICKED ON ABOUT OPTION");
		}
		
	}
	
	 String versionValue = version.getText();
	 return versionValue;
		
	}
	
	
	public int findEmployeeAdminTableData() {
		
		int row = rows.size();
		System.out.println("Employee row is : "+ row);
		
		int column = columns.size();
		System.out.println("Employee column is: "+ column);
		
		
		for (int i=2; i<=row; i++) {
			for(int j=2; j<=column; j++) {
				
				String employeeData = driver.findElement(By.xpath("(//div[@role='row'])["+i+"]//div[@role='cell']["+j+"]")).getText();
				System.out.print(employeeData +" ");
			}
			
			System.out.println();
		}
		
		int statusCount = 0;
		
		for(int i=2; i<=row; i++) {
			
			String EmployeeStatus = driver.findElement(By.xpath("(//div[@role='row'])["+i+"]//div[@role='cell'][5]")).getText();
			
			if (EmployeeStatus.equals("Enabled")) {
				System.out.println("(//div[@role='row'])["+i+"]//div[@role='cell'][4]");
				statusCount = statusCount+1;
			}
			
		}
		
	
		
		
		return statusCount;
		
	}
	
	
	
	public void clickOnCrossIconOnAccountWindow() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(crossIcon));
		crossIcon.click();
		Thread.sleep(3000);
	}
	
	
	
	
	public void clickOnLogoutOption() throws InterruptedException {
		
		wait.until(ExpectedConditions.visibilityOf(logoutOption));
		logoutOption.click();
		Thread.sleep(2000);
	}
	

	
}
