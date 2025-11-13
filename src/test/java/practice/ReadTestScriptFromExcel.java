package practice;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ReadTestScriptFromExcel {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
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
				
				//Read TestScript from Excel
				FileInputStream fis1 = new FileInputStream("C:\\Users\\savisaro\\Documents\\NINZA_CRM.xlsx");
				Workbook wb = WorkbookFactory.create(fis1);
				String CAMPAIGN_NAME = wb.getSheet("Campaigns").getRow(1).getCell(2).getStringCellValue();
				String TARGET_SIZE = wb.getSheet("Campaigns").getRow(1).getCell(3).getStringCellValue();
				wb.close();
				
				//Launch the browser
				ChromeOptions settings = new ChromeOptions();
				Map<String, Object> prefs = new HashMap<>();
				prefs.put("profile.password_manager_leak_detection", false);
				settings.setExperimentalOption("prefs", prefs); 
				
				WebDriver driver = null;
				if(BROWSER.equalsIgnoreCase("Edge"))
				driver = new EdgeDriver();
				else if(BROWSER.equalsIgnoreCase("Chrome"))
				driver = new ChromeDriver(settings);
				else if(BROWSER.equalsIgnoreCase("Firefox"))
				driver = new FirefoxDriver();
				else if(BROWSER.equalsIgnoreCase("Safari"))
					driver = new SafariDriver();
				
				
				driver.manage().window().maximize();
				driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
				//LOGIN
				driver.get(URL);
				driver.findElement(By.id("username")).sendKeys(USERNAME);
				driver.findElement(By.id("inputPassword")).sendKeys(PASSWORD);
				driver.findElement(By.xpath("//button[@type='submit']")).click();
				//Create Campaign
				driver.findElement(By.xpath("//span[contains(text(),'Create Campaign')]")).click();
				WebElement campaignName = driver.findElement(By.name("campaignName"));
				campaignName.sendKeys(CAMPAIGN_NAME);
				WebElement TargetSize = driver.findElement(By.name("targetSize"));
				TargetSize.clear();
				TargetSize.sendKeys(TARGET_SIZE);
				driver.findElement(By.xpath("//button[@type='submit']")).click();
				//verify the toast message
				WebElement toast = driver.findElement(By.xpath("//div[@role='alert']"));
				WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
				wait.until(ExpectedConditions.visibilityOf(toast));
				String toastmsg = toast.getText();
				System.out.println(toastmsg);
				if(toastmsg.contains("Successfully Added"))
					System.out.println("Campain addded");
				else 
					System.out.println("Campain not addded");
				driver.findElement(By.xpath("//button[@aria-label = 'close']")).click();
				//logout
				WebElement UserIcon = driver.findElement(By.className("user-icon"));
				Actions action = new Actions(driver);
				action.moveToElement(UserIcon).perform();
				WebElement logout = driver.findElement(By.xpath("//div[text()='Logout ']"));
				action.moveToElement(logout).click().perform();		
				driver.quit();
						
	}

}
