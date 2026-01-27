package CampaignTest;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import static org.testng.Assert.assertTrue;

import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

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
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import genericUtilities.BaseClass;
import genericUtilities.ExcelFileUtility;
import genericUtilities.JavaUtility;
import genericUtilities.WebDriverUtility;
import genericUtilities.readDataFromPropertyFile;
import objectrepository.CampaignsPage;
import objectrepository.CreateCampaignPage;
import objectrepository.HomePage;
import objectrepository.LoginPage;
@Listeners(genericUtilities.ListenerImplementation.class)
public class CreateCampaignTest extends BaseClass{

	@Test(groups = {"smoke","regression"})
	public void createCampaignWithMandatoryFieldsTest() throws IOException  {
		
		String CAMPAIGN_NAME = eLib.readDataFromExcelFile("Campaigns",1,2);
		String TARGET_SIZE = eLib.readDataFromExcelFile("Campaigns", 1, 3);
		
//Create Campaign
CampaignsPage campaignsPage = new CampaignsPage(driver);
//WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
campaignsPage.getAddAcreateCampaignBtn().click();
CreateCampaignPage createCampaignPage = new CreateCampaignPage(driver);
createCampaignPage.getCampaignNameTF().sendKeys(CAMPAIGN_NAME+jLib.getRandomString(3));
createCampaignPage.targetSizeTF().clear();
createCampaignPage.targetSizeTF().sendKeys(TARGET_SIZE);
createCampaignPage.createCampaignBtn().click();		

//verify the toast message
HomePage homepage = new HomePage(driver);
WebElement toast = homepage.getToastMsg();
wLib.waitUntilElementToBeVisible(driver,toast);
String toastmsg = toast.getText();
System.out.println(toastmsg);
homepage.getCloseToastMsg().click();

AssertJUnit.assertTrue(toastmsg.contains("Successfully Added"));
	}
	
	@Test(groups = "regression")
	public void createCampaignWithStatusTest() throws IOException  {
		
String CAMPAIGN_NAME = eLib.readDataFromExcelFile("Campaigns",4,2);
String TARGET_SIZE = eLib.readDataFromExcelFile("Campaigns", 4, 3);
String STATUS = eLib.readDataFromExcelFile("Campaigns", 4, 4);
		
//Create Campaign
CampaignsPage campaignsPage = new CampaignsPage(driver);
campaignsPage.getAddAcreateCampaignBtn().click();
CreateCampaignPage createCampaignPage = new CreateCampaignPage(driver);
createCampaignPage.getCampaignNameTF().sendKeys(CAMPAIGN_NAME+jLib.getRandomString(3));
createCampaignPage.targetSizeTF().clear();
createCampaignPage.targetSizeTF().sendKeys(TARGET_SIZE);
createCampaignPage.campaignStatusTF().sendKeys(STATUS);
createCampaignPage.createCampaignBtn().click();	

//verify the toast message
HomePage homepage = new HomePage(driver);
WebElement toast = homepage.getToastMsg();
wLib.waitUntilElementToBeVisible(driver,toast);
String toastmsg = toast.getText();
System.out.println(toastmsg);
homepage.getCloseToastMsg().click();
AssertJUnit.assertTrue(toastmsg.contains("Successfully Added"));
	}
	
	@Test(groups = "regression")
	public void createCampaignWithExpectedCloseDateTest() throws IOException  {
		
		String CAMPAIGN_NAME = eLib.readDataFromExcelFile("Campaigns",7,2);
		String TARGET_SIZE = eLib.readDataFromExcelFile("Campaigns", 7, 3);
		String EXPECTED_CLOSE_DATE = eLib.readDataFromExcelFile("Campaigns", 7, 4);
		
//Create Campaign
CampaignsPage campaignsPage = new CampaignsPage(driver);
campaignsPage.getAddAcreateCampaignBtn().click();
CreateCampaignPage createCampaignPage = new CreateCampaignPage(driver);
createCampaignPage.getCampaignNameTF().sendKeys(CAMPAIGN_NAME+jLib.getRandomString(3));
createCampaignPage.targetSizeTF().clear();
createCampaignPage.targetSizeTF().sendKeys(TARGET_SIZE);
createCampaignPage.expectedCloseDateTF().sendKeys(EXPECTED_CLOSE_DATE);
createCampaignPage.createCampaignBtn().click();	

//verify the toast message
HomePage homepage = new HomePage(driver);
WebElement toast = homepage.getToastMsg();
wLib.waitUntilElementToBeVisible(driver,toast);
String toastmsg = toast.getText();
System.out.println(toastmsg);
homepage.getCloseToastMsg().click();
AssertJUnit.assertTrue(toastmsg.contains("Successfully Added"));		
}

	}

