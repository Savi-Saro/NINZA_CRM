package practice;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ReadDataFromPropertiesFile {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
//Create java representation object of physical file
		FileInputStream fis = new FileInputStream("C:\\Users\\savisaro\\Documents\\Commondata.properties.txt");
		//CREATE OBEJECT OF PROPERTIES
		Properties prop = new Properties();
		//LOAD THE DATA INTO PROP
		prop.load(fis);
		//READ THE DATA
		String BROWSER =prop.getProperty("Browser");
		String URL =prop.getProperty("URL");
		String USERNAME =prop.getProperty("Username");
		String PASSWORD =prop.getProperty("Password");
		
		//PRINT
		System.out.println(BROWSER);
		System.out.println(URL);
		System.out.println(USERNAME);
		System.out.println(PASSWORD);
		
	}

}
