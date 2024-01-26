package utilityPackage;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;

import org.apache.commons.compress.utils.IOUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;

import com.aventstack.extentreports.reporter.configuration.util.IOUtil;

public class ScreenShotPP {

	/*public static String getScreenShotPP(WebDriver driver) {
		
	
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH.mm.ss ");
		Date date = new Date();
		String timeStamp = sdf.format(date);	
		
		File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		String screenShotPath = ".\\src\\test\\resources\\screenshots\\TestScreenShots" + timeStamp + ".jpg";
		File destination = new File(screenShotPath); //file k roop me save krte he
		try {
			FileHandler.copy(src, destination);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return screenShotPath;
	
	//This Approach --->Take screenshot as PNG, JPG file --> Attach to report
	//DrawBack----> 1) While inspecting the screenshot in extent report you will see the link in the form of your current project directory...
	 				so unable to email to someone's Id ...so that you have to send 1 another file i.e index.html along with screenshot.
					2) Increase the number of counts in screenshot folder in the current project directory.
	}*/
	
	
	/*public static String getScreenShotBase64(WebDriver driver) throws FileNotFoundException, IOException {
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH.mm.ss ");
		Date date = new Date();
		String timeStamp = sdf.format(date);	
		
		File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		String screenShotPath = ".\\src\\test\\resources\\screenshots\\TestScreenShots" + timeStamp + ".jpg";
		File destination = new File(screenShotPath);
		try {
			FileHandler.copy(src, destination);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//Base64 -----> Encoded String
		
		byte imageBytes[] = IOUtils.toByteArray(new FileInputStream(screenShotPath));
		String screenShotBase64	= Base64.getEncoder().encodeToString(imageBytes);
		return screenShotBase64;
		
	//This Approach --->Take screenshot as PNG, JPG file -->Convert it in the form of Base64 ---> Attach to report (you can email to somone's Id)
	//DrawBack----> 1) Increase the number of counts in screenshot folder in the current project directory.
		
	}*/
	
	
	public static String getScreenShotAsBase64(WebDriver driver) {
		
		String screenShotAsBase64 = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
		return screenShotAsBase64;
	
		//This Approach --->Take screenshot as Base64---> Attach to report (Most recommended way)
		
		
	}
	
}
