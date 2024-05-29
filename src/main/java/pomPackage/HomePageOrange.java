package pomPackage;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;




public class HomePageOrange {

	WebDriver driver;
	
	private WebDriverWait wait;
	
	
	@FindBy (xpath = "//a[@href='/web/index.php/auth/logout']")
	private WebElement logoutOption;
	
	@FindBy (xpath = "//p[contains(@class,'oxd-userdropdown-name')]")
	private WebElement accountOptions;
	
	@FindBy(xpath = "//p[@class='oxd-text oxd-text--p orangehrm-about-text']/ancestor::div[@class='oxd-grid-2 orangehrm-about']/descendant::p[2]")
	private WebElement companyNameValue;
	
	
	@FindBy (xpath ="//button[@class='oxd-dialog-close-button oxd-dialog-close-button-position']")
	private WebElement aboutCrossIcon;
	
	@FindBy (xpath = "//span[text()='Admin']")
	private WebElement adminOption;

	@FindBy (xpath = "//div[@role='row']")
	private List<WebElement> rows;
	
	@FindBy (xpath = "(//div[@role='row'])[2]//div[@role='cell']")
	private List<WebElement> columns;
	
	
	
	public HomePageOrange (WebDriver driver) {
		
		this.driver = driver;
		wait = new WebDriverWait(driver, 20);
		PageFactory.initElements(driver, this);
	}
	
	
	public void clickOnAccountOptionsDropDown() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(accountOptions));
		accountOptions.click();
		Thread.sleep(2000);
	}
	

	
	public String getOptionsONAccountOptionsDropDown() throws InterruptedException {		
	
		List<WebElement> list = driver.findElements(By.xpath("//a[@role='menuitem']"));
		
		for(WebElement element : list) {
			
			System.out.println(element.getText());
			if(element.getText().contentEquals("About")) {
				element.click();
				System.out.println("User clicked on About option");
			}
	
		}
		
		Thread.sleep(2000);
		
		String companyNameValue1 = companyNameValue.getText();
		
		return companyNameValue1;
		
	}
	
	
	public void clickOnAboutCrossIcon() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(aboutCrossIcon));
		aboutCrossIcon.click();
		System.out.println("User clicked on cross icon");
		Thread.sleep(2000);
	}
	
	
	
	public int findEmployeeTableData() throws InterruptedException {
		
		JavascriptExecutor j = (JavascriptExecutor)driver;
		j.executeScript("scroll(0,400)");
		Thread.sleep(2000);
		
		int r = rows.size();
		System.out.println("Rows present in the table is: " + r);
		
		int c = columns.size();
		System.out.println("Columns present in the table is: " + c + "\n");
		
		System.out.println("Employees data show in table : " );
		
		for(int i= 2; i<=r; i++) {
			for(int k=2; k<=c; k++) {
				String employeedata = driver.findElement(By.xpath("(//div[@role='row'])["+i+"]//div[@role='cell']["+k+"]")).getText();
				System.out.print(employeedata +" ");
			}
			System.out.println();
		}
		
		System.out.println();
		
		int statuscount = 0;
		
		System.out.println("Employees name of Status Enabled : " + "\n");
		for (int i=2; i<=r; i++) {
			String employeestatus = driver.findElement(By.xpath("(//div[@role='row'])["+i+"]//div[@role='cell'][5]")).getText();
			if(employeestatus.equals("Enabled")) {
				statuscount=statuscount+1;
				System.out.println(driver.findElement(By.xpath("(//div[@role='row'])["+i+"]//div[@role='cell'][4]")).getText());
			}
		}
	
		
		return statuscount;
		
		
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
