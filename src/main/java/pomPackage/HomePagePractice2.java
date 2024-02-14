package pomPackage;



import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.fasterxml.jackson.annotation.JacksonInject.Value;

	public class HomePagePractice2 {
	
		WebDriver driver;
		WebDriverWait wait;
		
		@FindBy (xpath ="//span[text()='Admin']") private WebElement adminOption;
		
		@FindBy (xpath ="//p[@class='oxd-userdropdown-name']") private WebElement accountOptions;
		
		@FindBy (xpath ="//a[@href='/web/index.php/auth/logout']") private WebElement logoutbutton;
		
		@FindBy (xpath = "//p[contains(@class,'oxd-userdropdown-name')]") private WebElement accountDropDown;
		
		@FindBy (xpath = "(//p[text()='OrangeHRM OS 5.6'])[2]") private WebElement version;	
		
		@FindBy (xpath = "//button[contains(@class,'oxd-dialog-close-button oxd-dialog-close-button-position')]")
		private WebElement crossIcon;
		
		@FindBy (xpath ="//div[@role='row']") private List<WebElement> rows;
		
		@FindBy (xpath ="(//div[@role='row'])[2]//div[@role='cell']") private List<WebElement> columns;
		
		
		
		
		public HomePagePractice2 (WebDriver driver) {
			
			this.driver = driver;
			wait = new WebDriverWait(driver, 20);
			PageFactory.initElements(driver,this);
		}
		
		
		public void clickOnAdminOption() throws InterruptedException {
			
			wait.until(ExpectedConditions.elementToBeClickable(adminOption));
			adminOption.click();
			Thread.sleep(3000);
		}
		
		public void clickOnAccountOptionsDropDown() throws InterruptedException {
			wait.until(ExpectedConditions.elementToBeClickable(accountOptions));
			accountOptions.click();
			Thread.sleep(2000);
		}
		
		
		
		public void clickOnAccountDropDown() throws InterruptedException {
			wait.until(ExpectedConditions.elementToBeClickable(accountDropDown));
			accountDropDown.click();
			Thread.sleep(2000);
			
		}
	
		
		
		public String getAccountDropDownOptions() {
			
		
			List<WebElement> accountOptions = driver.findElements(By.xpath("//a[@role='menuitem']"));
	
			int countAccountOptions = accountOptions.size();
	
			for(WebElement element : accountOptions) {
				
				if(element.getText().contentEquals("About")){
						System.out.println( "User get account option : " + element.getText());	
						element.click();
						System.out.println("User clicked on About option");
				}
			}
			
			
			System.out.println("User Navigated on About Window");	
			String versionvalue = 	version.getText();
			System.out.println("Version value is : " +versionvalue);
			
			return versionvalue;
			
		}
		
		
		public int findemployeeAdminTable() {
			
			
			int row = rows.size();
			System.out.println("number of row is : "+ row);
			
			int column = columns.size();
			System.out.println("number of column is : "+ column);
			
			
			for (int i=2; i<=row; i++) {
				for(int j=2; j<=column; j++ ) {
					
					String employeedata = driver.findElement(By.xpath("(//div[@role='row'])["+i+"]//div[@role='cell']["+j+"]")).getText();
					System.out.print(employeedata +" ");
				}
				
				System.out.println();
			}
			
			System.out.println();
			
			
			int statusCount = 0;
			
			for (int i=2; i<=row; i++) {
					
					String employeeStatus = driver.findElement(By.xpath("(//div[@role='row'])["+i+"]//div[@role='cell'][5]")).getText();
					
						if(employeeStatus.equals("Enabled")) {
							System.out.println( "Employee Name status Enabled: "+  driver.findElement(By.xpath("(//div[@role='row'])["+i+"]//div[@role='cell'][4]")).getText());
							statusCount = statusCount+1;
						}
			}
			
			
			System.out.println("Total number of employees ehose status is Enabled: " + statusCount);
			return statusCount;
			
		}
		
		
		
		
		public void clickOnCrossIcon() throws InterruptedException {
			wait.until(ExpectedConditions.elementToBeClickable(crossIcon));
			crossIcon.click();
			Thread.sleep(3000);
		}
		
		
		public void clickOnLogoutOption() throws InterruptedException {
			wait.until(ExpectedConditions.elementToBeClickable(logoutbutton));
			logoutbutton.click();
			Thread.sleep(2000);
		}
		
	}
