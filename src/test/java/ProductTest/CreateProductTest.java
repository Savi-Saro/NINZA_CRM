package ProductTest;

import static org.testng.Assert.assertTrue;

import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import genericUtilities.BaseClass;
import genericUtilities.ExcelFileUtility;
import genericUtilities.JavaUtility;
import genericUtilities.WebDriverUtility;
import genericUtilities.readDataFromPropertyFile;
import objectrepository.AddProductPage;
import objectrepository.HomePage;
import objectrepository.LoginPage;
import objectrepository.ProductsPage;

public class CreateProductTest extends BaseClass{

	@Test(groups = {"smoke","regression"})
	public void createProductMobileTest() throws IOException {
				
		String PRODUCT_NAME = eLib.readDataFromExcelFile("Product",1,2);
		String SELECT_CATEGORY = eLib.readDataFromExcelFile("Product",1,3);
		String QUANTITY = eLib.readDataFromExcelFile("Product",1,4);
		String PRICE_PER_UNIT = eLib.readDataFromExcelFile("Product",1,5);
		String SELECT_VENDOR = eLib.readDataFromExcelFile("Product",1,6);
		
		//Create Product
		ProductsPage product = new ProductsPage(driver);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.elementToBeClickable(product.getAddProductBtn())).click();
		//product.getAddProductBtn().click();
		AddProductPage addproducts = new AddProductPage(driver);
		product.getAddProductBtnclick().click();
		addproducts.getProductNameTF().sendKeys(PRODUCT_NAME+jLib.generateRandomNumber());
	
		WebElement productCategory = addproducts.getProductCategoryTF();
		wLib.select(productCategory,SELECT_CATEGORY);
	
		WebElement quantity = addproducts.getQuantityTF();
		quantity.clear();
		quantity.sendKeys(QUANTITY);
		WebElement price = addproducts.getPriceTF();
		price.clear();
		price.sendKeys(PRICE_PER_UNIT);
		//select class
		WebElement vendor = addproducts.getVendorIdDD();
		wLib.select(vendor,SELECT_VENDOR);
		addproducts.getAddBtn().click();
		
		//verify the toast message
		HomePage homepage = new HomePage(driver);
		WebElement toast = homepage.getToastMsg();
		wLib.waitUntilElementToBeVisible(driver,toast);
		String toastmsg = toast.getText();
		System.out.println(toastmsg);
		homepage.getCloseToastMsg().click();
		assertTrue(toastmsg.contains("Successfully Added"));
			}
	
	@Test(groups = {"smoke","regression"})
	public void createProductLaptopTest() throws EncryptedDocumentException, IOException {
			
		String PRODUCT_NAME = eLib.readDataFromExcelFile("Product",2,2);
		String SELECT_CATEGORY = eLib.readDataFromExcelFile("Product",2,3);
		String QUANTITY = eLib.readDataFromExcelFile("Product",2,4);
		String PRICE_PER_UNIT = eLib.readDataFromExcelFile("Product",2,5);
		String SELECT_VENDOR = eLib.readDataFromExcelFile("Product",2,6);
		
		//Create Product
		ProductsPage product = new ProductsPage(driver);
		product.getAddProductBtn().click();
		AddProductPage addproducts = new AddProductPage(driver);
		product.getAddProductBtnclick().click();
		addproducts.getProductNameTF().sendKeys(PRODUCT_NAME+jLib.generateRandomNumber());
		//select class
		WebElement productCategory = driver.findElement(By.name("productCategory"));
		wLib.select(productCategory,SELECT_CATEGORY);
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
		//End class
		addproducts.getAddBtn().click();
		
		//verify the toast message
		HomePage homepage = new HomePage(driver);
		WebElement toast = homepage.getToastMsg();
		wLib.waitUntilElementToBeVisible(driver,toast);
		String toastmsg = toast.getText();
		System.out.println(toastmsg);
		homepage.getCloseToastMsg().click();
		assertTrue(toastmsg.contains("Successfully Added"));
			
	}
}
