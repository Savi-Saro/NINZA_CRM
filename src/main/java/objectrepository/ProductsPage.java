package objectrepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ProductsPage {
	WebDriver driver;

	public ProductsPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(linkText = "Products")
	private WebElement addProductBtn;
	
	@FindBy(xpath = "//span[text()='Add Product']")
	private WebElement addProductBtnclick;
	public WebElement getAddProductBtnclick()
	{
		return addProductBtnclick;
	}
	
	
	public WebElement getAddProductBtn()
	{
		return addProductBtn;
	}
	
}
