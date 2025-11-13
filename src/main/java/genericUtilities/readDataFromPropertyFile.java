package genericUtilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class readDataFromPropertyFile {

	public String readDataFromPropertyFile(String Key) throws IOException {
		// TODO Auto-generated method stub
		FileInputStream fis = new FileInputStream("C:\\Users\\savisaro\\Documents\\Commondata.properties.txt");
		//CREATE OBEJECT OF PROPERTIES
		Properties prop = new Properties();
		//LOAD THE DATA INTO PROP
		prop.load(fis);
		String Value = prop.getProperty(Key);		
		return Value;
	}

}
