package testPackage;

import java.util.List;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;




	

public class Locators_Axes {
	

		    static WebDriver driver;
			
			
			public static void main(String []args) throws InterruptedException {
				
				
				WebDriverManager.chromedriver().setup();
				
				driver = new ChromeDriver();
				
				driver.manage().window().maximize();
				
				driver.get("https://money.rediff.com/gainers/bse/daily/groupa");
				
				driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
				
				//self [Selects the current node]
				WebElement s  = driver.findElement(By.xpath("//a[contains(text(),'Neuland Laborat')]/self::a"));
				String self	=	s.getText();
				System.out.println(self);
				
				//parent [Selects the parent of the current node (always one)]
				String parent =	driver.findElement(By.xpath("//a[contains(text(),'Neuland Laborat')]/parent::td")).getText();
				System.out.println(parent);
				
				//child [Selects the children of current node (one or many)]
				List<WebElement> an = driver.findElements(By.xpath("//a[contains(text(),'Neuland Laborat')]/ancestor::tr/child::td"));
				int ancestors = an.size();
				System.out.println("The number of all children of ancestor tr : " +ancestors);
			
				//ancestor [Selects all (Parents, GrandParents etc )of current node]
				String text = driver.findElement(By.xpath("//a[contains(text(),'Neuland Laborat')]/ancestor::tr")).getText();
				System.out.println(text);
				
				
				//descendant [Selects all (Child, GrandChild etc)of current node)
				List<WebElement> des = driver.findElements(By.xpath("//a[contains(text(),'Neuland Laborat')]/ancestor::tr/descendant::*"));
			    int descendant = des.size();
				System.out.println("The number of all descendant of ancestor tr : "+ descendant);
		
				
				//following [Select everything  in the document after the closing  tag of current node]
				List<WebElement> foll =	driver.findElements(By.xpath("//a[contains(text(),'Neuland Laborat')]/ancestor::tr/following::tr"));
				int following = foll.size();
				System.out.println("The number of all following of ancestor tr : "+ following);
		
	
				//following-sibling[selects all siblings after the current node]
				List<WebElement> follSib = driver.findElements(By.xpath("//a[contains(text(),'Neuland Laborat')]/ancestor::tr/following-sibling::tr"));
				int followingsibling = follSib.size();
				System.out.println("The number of all following-sibling of ancestor tr : "+ followingsibling);
				
				
				//preceding [Selects all node that appear before the current node in the document]
				List<WebElement> pre =	driver.findElements(By.xpath("//a[contains(text(),'Neuland Laborat')]/ancestor::tr/preceding::tr"));
				int preceding = pre.size();
				System.out.println("The number of all preceding of ancestor tr : "+ preceding);
				
				
				//preceding-sibling [Selects all sibling before the current node]
				List<WebElement> presib = driver.findElements(By.xpath("//a[contains(text(),'Neuland Laborat')]/ancestor::tr/preceding-sibling::tr"));
				int precedingsibling =	presib.size();
				System.out.println("The number of all preceding-sibling of ancestor tr : " + precedingsibling);
	
				driver.get("https://www.facebook.com/r.php");
				
				
				//Find the Sign-up button from the registration form present in the facebook page [child]
				driver.findElement(By.xpath("//div[@id='reg_form_box']/child::div[11]/button")).click();
				Thread.sleep(2000);
				
				 driver.navigate().refresh();
				 
				 
				//-->Locate the FirstName field from sign-up button in facebook [Parent]
				//button[@id='u_0_s_52']/parent::*/parent::*/child::div[1]/div/child::div[1]/div/div[1]//input[@name='firstname']
				 
				
				//--->Identify the password from mobileNumber field in facebook page[FOLLOWING]
				//input[@id='u_0_g_gp']/following::input[2]
				
				 
				//--->Locate the mobileNumber from password field in facebook page [PRECEDING]
				//input[@id='password_step_input']/preceding::input[2] 
				 
				 
				//---> Locate the surname from female radio button [ANCESTOR]
				//input[@id='u_0_4_3P']/ancestor::div[2]/div[1]/div/div[2] 
				//input[@id='u_0_4_3P']/ancestor::div[2]//input[@id='u_0_d_Ip']
				
				//---->Identify the search text box from google search button on google page [Parent]
				//div[@class='FPdoLc lJ9FBc']//input[@name='btnK']/parent::*/parent::*/parent::*/div[1]
				
				//Identify the TodaysDeal link from search text box on Amazon page [FOLLOWING]
				//input[@id='twotabsearchtextbox']/following::a[contains(text(), "Today's Deals")]
				//(USE double Inverted Comma --->if write just after double colon)
				
				 //Identify the Hello Sign-In from search text box on Amazon page [FOLLOWING]
				//input[@id='twotabsearchtextbox']/following::span[contains(text(), 'Hello, sign in')]
				 
				 //Identify the mobile Link which is the part of menu bar on Amazon page
				//div[@id='nav-xshop']/descendant::a[contains(text(),'Mobiles')]
				 
			
				//Identify the Sell Link which is the part of menu bar on Amazon page 
				//div[@id='nav-xshop']/descendant::a[contains(text(),'Sell')][1]
				 
				 
		
				 
				driver.close();
				
			}
		}
		
