package practice;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Random;

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
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import genericUtilities.ExcelFileUtility;
import genericUtilities.JavaUtility;
import genericUtilities.WebDriverUtility;
import genericUtilities.readDataFromPropertyFile;
import objectrepository.AddProductPage;
import objectrepository.HomePage;
import objectrepository.LoginPage;
import objectrepository.ProductsPage;

public class CreateProductLaptop {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		//FileInputStream fis = new FileInputStream("C:\\Users\\savisaro\\Documents\\Commondata.properties.txt");
		//CREATE OBEJECT OF PROPERTIES
		//Properties prop = new Properties();
		//LOAD THE DATA INTO PROP
		//prop.load(fis);
		//READ THE DATA
		readDataFromPropertyFile plib = new readDataFromPropertyFile();
		String BROWSER =plib.readDataFromPropertyFile("Browser");
		String URL =plib.readDataFromPropertyFile("URL");
		String USERNAME =plib.readDataFromPropertyFile("Username");
		String PASSWORD =plib.readDataFromPropertyFile("Password");

//Read TestScript from Excel
		//FileInputStream fis1 = new FileInputStream("C:\\Users\\savisaro\\Documents\\NINZA_CRM.xlsx");
		//Workbook wb = WorkbookFactory.create(fis1);
		//String PRODUCT_NAME = wb.getSheet("Product").getRow(2).getCell(2).getStringCellValue();
		//String SELECT_CATEGORY = wb.getSheet("Product").getRow(2).getCell(3).getStringCellValue();
		//String QUANTITY = wb.getSheet("Product").getRow(2).getCell(4).getStringCellValue();
		//String PRICE_PER_UNIT = wb.getSheet("Product").getRow(2).getCell(5).getStringCellValue();
		//String SELECT_VENDOR = wb.getSheet("Product").getRow(2).getCell(6).getStringCellValue();
		//wb.close();
		
		ExcelFileUtility eLib = new ExcelFileUtility();
		String PRODUCT_NAME = eLib.readDataFromExcelFile("Product",2,2);
		String SELECT_CATEGORY = eLib.readDataFromExcelFile("Product",2,3);
		String QUANTITY = eLib.readDataFromExcelFile("Product",2,4);
		String PRICE_PER_UNIT = eLib.readDataFromExcelFile("Product",2,5);
		String SELECT_VENDOR = eLib.readDataFromExcelFile("Product",2,6);
		
		//Generate random number
		//Random random = new Random();
		//int randnum = random.nextInt(1000);
		
		JavaUtility jLib = new JavaUtility();
		WebDriverUtility wLib = new WebDriverUtility();
		
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
		wLib.implicitWait(driver);
		//driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		
		//LOGIN
		LoginPage loginpage = new LoginPage(driver);
		loginpage.loginToApp(URL,USERNAME,PASSWORD);
		//driver.get(URL);
		//driver.findElement(By.id("username")).sendKeys(USERNAME);
		//driver.findElement(By.id("inputPassword")).sendKeys(PASSWORD);
		//driver.findElement(By.xpath("//button[@type='submit']")).click();
		
		//Create Product
		ProductsPage product = new ProductsPage(driver);
		product.getAddProductBtn().click();
		AddProductPage addproducts = new AddProductPage(driver);
		product.getAddProductBtnclick().click();
		addproducts.getProductNameTF().sendKeys(PRODUCT_NAME+jLib.generateRandomNumber());
		//select class
		WebElement productCategory = driver.findElement(By.name("productCategory"));
		wLib.select(productCategory,SELECT_CATEGORY);
		//Select category = new Select(productCategory);
		//category.selectByValue(SELECT_CATEGORY);//take from excel
		//End class
		WebElement quantity = addproducts.getQuantityTF();
		quantity.clear();
		quantity.sendKeys(QUANTITY);
		WebElement price = addproducts.getPriceTF();
		price.clear();
		price.sendKeys(PRICE_PER_UNIT);
		//select class
		WebElement vendor = addproducts.getVendorIdDD();
		wLib.select(vendor,SELECT_VENDOR);
		//Select vendorID = new Select(vendor);
		//vendorID.selectByValue(SELECT_VENDOR);//take from excel
		//End class
		addproducts.getAddBtn().click();
		
		//verify the toast message
		HomePage homepage = new HomePage(driver);
		WebElement toast = homepage.getToastMsg();
		//WebElement toast = driver.findElement(By.xpath("//div[@role='alert']"));
		//WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		wLib.waitUntilElementToBeVisible(driver,toast);
		String toastmsg = toast.getText();
		System.out.println(toastmsg);
		if(toastmsg.contains("Successfully Added"))
			System.out.println("Product addded");
		else 
			System.out.println("Product not addded");
		homepage.getCloseToastMsg().click();
		//driver.findElement(By.xpath("//button[@aria-label = 'close']")).click();
		
		//logout
		homepage.logout();
		//WebElement UserIcon = driver.findElement(By.className("user-icon"));
		//Actions action = new Actions(driver);
		//action.moveToElement(UserIcon).perform();
		//WebElement logout = driver.findElement(By.xpath("//div[text()='Logout ']"));
		//action.moveToElement(logout).click().perform();		
		driver.quit();
								
	}

}
