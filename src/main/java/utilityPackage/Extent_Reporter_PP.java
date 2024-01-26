package utilityPackage;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class Extent_Reporter_PP {

	static String reportPath;

	public static ExtentReports generateExtentReports() {
		
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH.mm.ss ");
		Date date = new Date();
		String timeStamp = sdf.format(date);
		
		System.out.println(timeStamp);
		
		ExtentReports extentReport = new ExtentReports();
		
		//Extract the directory under current project location
		// We have to store the report in the current project location
		reportPath = System.getProperty("user.dir")+"\\EXTENT_REPORTS\\ExtentReport "+timeStamp+".html"; 
		
		ExtentSparkReporter sparkReporter = new ExtentSparkReporter(reportPath); //Purpose used to configure the "properties" in Report
		sparkReporter.config().setTheme(Theme.DARK);
		sparkReporter.config().setReportName("Functional Test Results Report"); //Set Report Name
		sparkReporter.config().setDocumentTitle("Automation Report"); //Set Document Name
		sparkReporter.config().setTimeStampFormat("dd/MM/yyyy hh:mm:ss"); //SetTimeStamp Format
		
		extentReport.attachReporter(sparkReporter); //Purpose used to add additional "System information" in Report
		
		extentReport.setSystemInfo("HostName", "LocalHost");
		extentReport.setSystemInfo("Operating System", "Windows11");
		extentReport.setSystemInfo("QA", "PARAG");
		extentReport.setSystemInfo("Browser", "Chrome");
	
		return extentReport;
		
	}
}
