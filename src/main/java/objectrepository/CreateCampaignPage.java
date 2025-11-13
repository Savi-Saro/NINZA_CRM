package objectrepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CreateCampaignPage {
WebDriver driver;
	
	public CreateCampaignPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(name = "campaignName")
	private WebElement campaignNameTF;
	
	@FindBy(name = "targetSize")
	private WebElement targetSizeTF;
	
	@FindBy(name = "campaignStatus")
	private WebElement campaignStatusTF;
	
	@FindBy(name = "expectedCloseDate")
	private WebElement expectedCloseDateTF;
	
	@FindBy(xpath = "//button[@type='submit']")
	private WebElement createCampaignBtn;
	
	public WebElement getCampaignNameTF()  {
		return campaignNameTF;
	}
		
		public WebElement targetSizeTF()  {
			return targetSizeTF;
	}
		
		public WebElement campaignStatusTF()  {
			return campaignStatusTF;
	}
		
		public WebElement expectedCloseDateTF()  {
			return expectedCloseDateTF;
	}
		
		public WebElement createCampaignBtn()  {
			return createCampaignBtn;
	}
}
