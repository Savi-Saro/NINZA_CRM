package practice;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.decorators.WebDriverDecorator;
import org.testng.Reporter;
import org.testng.annotations.Test;

public class WorkingWithHelperAttributes
{

	@Test(priority = -10,invocationCount=5, threadPoolSize = 2, enabled=false)
	
	public void productCreation() throws InterruptedException {
		Reporter.log("create product",true);
		WebDriver driver = new ChromeDriver();
		Thread.sleep(2000);
		driver.close();
	}	
@Test()
	
	public void updateProduct() {
		Reporter.log("update product",true);
	}	
@Test ()

public void deleteProduct() {	
	Reporter.log("Delete product",true);
}	
}
