package utilityPackage;

import java.io.FileInputStream;

import java.io.IOException;
import java.util.Properties;



public class PropertyClass {

	public static String readDataFromPropertyFile(String Key) throws IOException {
		
		
		Properties prop = new Properties();
		
		
		//FileInputStream myfile = new FileInputStream ("E:\\AUTOMATION\\Eclipse_Workspace_2021_03\\Symworld\\Symworld.properties");
		
		FileInputStream myfile = new FileInputStream (System.getProperty("user.dir") + "\\Symworld.properties");
		
		prop.load(myfile);
		
		String	value =	prop.getProperty(Key);
		
		return value;
		
	}
	
}
