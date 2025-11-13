package genericUtilities;

import org.testng.annotations.Test;

import objectrepository.HomePage;
import objectrepository.LoginPage;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.AfterSuite;

public class BaseClass {
	
	public WebDriver driver = null;
	public readDataFromPropertyFile plib = new readDataFromPropertyFile();
	public ExcelFileUtility eLib = new ExcelFileUtility();
	public WebDriverUtility wLib = new WebDriverUtility();
	public JavaUtility jLib = new JavaUtility();
	public static WebDriver sdriver = null;
	
	 @BeforeSuite(groups = {"smoke","regression"})
	  public void beforeSuite() {
		  System.out.println("Establish DB connection");
	  }

	  @BeforeTest(groups = {"smoke","regression"})
	  public void beforeTest() {
		  System.out.println("Pre-condition for parallel execution");
	  }
	  
  
	// @Parameters("Browser")
	  @BeforeClass(groups = {"smoke","regression"})
	  public void beforeClass() throws IOException {
		  System.out.println("Launch browser");
		  ChromeOptions settings = new ChromeOptions();
		  Map<String, Object> prefs = new HashMap<>();
		  prefs.put("profile.password_manager_leak_detection", false);
		  settings.setExperimentalOption("prefs", prefs); 
		  
		  String BROWSER = plib.readDataFromPropertyFile("Browser");

		  if(BROWSER.equalsIgnoreCase("Edge"))
		  driver = new EdgeDriver();
		  else if(BROWSER.equalsIgnoreCase("Chrome"))
		  driver = new ChromeDriver(settings);
		  else if(BROWSER.equalsIgnoreCase("Firefox"))
		  driver = new FirefoxDriver();
		  else if(BROWSER.equalsIgnoreCase("Safari"))
		  	driver = new SafariDriver();
		  
		  sdriver=driver;


		  driver.manage().window().maximize();
		  wLib.implicitWait(driver);
	  }
	  
	  
    @BeforeMethod(groups = {"smoke","regression"})
  public void beforeMethod() throws IOException {
    	System.out.println("Login");
    	String URL =plib.readDataFromPropertyFile("URL");
		String USERNAME =plib.readDataFromPropertyFile("Username");
		String PASSWORD =plib.readDataFromPropertyFile("Password");
    	LoginPage loginpage = new LoginPage(driver);
    	loginpage.loginToApp(URL,USERNAME,PASSWORD);
  }

  @AfterMethod(groups = {"smoke","regression"})
  public void afterMethod() {
	  System.out.println("LogOut");
	  HomePage hp= new HomePage(driver);
	  hp.logout();
  }

  @AfterClass(groups = {"smoke","regression"})
  public void afterClass() {
	  System.out.println("Close browser");
	  driver.quit();
  }

  @AfterTest(groups = {"smoke","regression"})
  public void afterTest() {
	  System.out.println("Post-condition for parallel execution");
  }

  @AfterSuite(groups = {"smoke","regression"})
  public void afterSuite() {
	  System.out.println("End DB connection"); 
  }

}
