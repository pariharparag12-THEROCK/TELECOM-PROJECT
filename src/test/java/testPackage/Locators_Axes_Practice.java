package testPackage;

import java.util.List;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;




	

public class Locators_Axes_Practice {
	

		    static WebDriver driver;
			
			
			public static void main(String []args) throws InterruptedException {
				
				
				WebDriverManager.chromedriver().setup();
				
				driver = new ChromeDriver();
				
				driver.manage().window().maximize();
				
				driver.get("https://money.rediff.com/gainers/bse/daily/groupa");
				
				driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
				
				/*
				//self [Selects the current node]
				WebElement self = driver.findElement(By.xpath("//a[contains(text(),'Neuland Laborat')]/self::a"));
				String s =	self.getText();
				System.out.println(s);
				 
				//Parant--->always one
				String parant = driver.findElement(By.xpath("//a[contains(text(),'Neuland Laborat')]/parent::td")).getText();
				System.out.println(parant);
				
				
				//Ancestors Childs
					List<WebElement> AC = driver.findElements(By.xpath("//a[contains(text(),'Neuland Laborat')]/ancestor::tr/child::td"));
					int v =	AC.size();
			        System.out.println(v);
				
			        
			    //Ancestors (parants + Grandparants)
			      WebElement a = driver.findElement(By.xpath("//a[contains(text(),'Neuland Laborat')]/ancestor::tr"));
					String f =	a.getText();
			        System.out.println(f);
			        
			    //Decendents (child+GrandChilds)    
			     List<WebElement> desElements =  driver.findElements(By.xpath("//a[contains(text(),'Neuland Laborat')]/ancestor::tr/descendant::*"));
			     int d = desElements.size();   
			     System.out.println(d);
			     
			     //Following (from Ancestor)
			     List<WebElement> foll =   driver.findElements(By.xpath("//a[contains(text(),'Neuland Laborat')]/ancestor::tr/following::tr"));
					int b =    foll.size();
				 System.out.println(b); 
				 
				 //Following Siblings	(from Ancestor)
				 List<WebElement> follSib = driver.findElements(By.xpath("//a[contains(text(),'Neuland Laborat')]/ancestor::tr/following-sibling::tr")); 
				 int x =    follSib.size();
				 System.out.println(x); 
				 
				 //preceding (from Ancestor)
				 List<WebElement> preceding =  driver.findElements(By.xpath("//a[contains(text(),'Neuland Laborat')]/ancestor::tr/preceding::tr"));
				 int m =    preceding.size();
				 System.out.println(m);
			     
				 //preceding (from Ancestor)
				 List<WebElement> PreSib	=  driver.findElements(By.xpath("//a[contains(text(),'Neuland Laborat')]/ancestor::tr/preceding-sibling::tr"));
				 int r = PreSib.size();
				 System.out.println(r);
				 
				 */
				
				
				driver.get("https://www.facebook.com/r.php");
				
				//Click on SignUpButton from Registration form
				driver.findElement(By.xpath("//div[@id='reg_form_box']/child::div[11]/button")).click();
				Thread.sleep(2000);
				 driver.navigate().refresh();
				
				
				//Locate the FirstName field from sign-up button in facebook [Parent]
				//driver.findElement(By.xpath("//button[@id='u_0_s_1x']/parent::*/parent::*/child::div[1]/div[1]/child::div[1]/div/div[1]/input[@id='u_0_b_Wd']"));
				
				//--->Identify the password from mobileNumber field in facebook page[FOLLOWING]
				//input[@id='u_0_g_Em']/following::input[2]
				 
				//--->Locate the mobileNumber from password field in facebook page [PRECEDING]
				//input[@id='password_step_input']/preceding::input[2]
				 
				//---> Locate the surname from female radio button [ANCESTOR]
				//input[@id='u_0_4_c/']/ancestor::div[2]//input[@id='u_0_d_4E']
				
				 
				 
				driver.close();
				
			}
		}
		
