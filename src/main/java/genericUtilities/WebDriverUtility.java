package genericUtilities;

import java.time.Duration;
import java.util.NoSuchElementException;
import java.util.Set;


import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WebDriverUtility {
public void implicitWait(WebDriver driver) {
	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
}
public void waitUntilElementToBeVisible(WebDriver driver,WebElement element) {
	WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(20));
	wait.until(ExpectedConditions.visibilityOf(element));
}

private WebDriver driver;

public WebDriverUtility(WebDriver driver) {
    this.driver = driver;
}

/**
 * Safely clicks an element.
 * Handles:
 * - Waiting for element to be clickable
 * - Scrolling into view
 * - JS click if normal click fails
 * - Opens navbar toggle if element is hidden behind it (common in Jenkins)
 */
public void safeClick(By locator) {
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

    try {
        WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(locator));
        wait.until(ExpectedConditions.elementToBeClickable(element));

        try {
            // Try normal click first
            element.click();
        } catch (ElementClickInterceptedException e) {
            // Check if a collapsible navbar is blocking the element
            try {
                WebElement navbarToggle = driver.findElement(By.cssSelector(".navbar-toggler"));
                if (navbarToggle.isDisplayed()) {
                    navbarToggle.click(); // Open the menu
                    Thread.sleep(500);    // Small pause to let animation finish
                }
            } catch (NoSuchElementException | InterruptedException ignored) {
            }

            // Scroll element into view and retry click
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
            try {
                element.click();
            } catch (ElementClickInterceptedException ex) {
                // Final fallback: JS click
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
            }
        }

    } catch (TimeoutException e) {
        throw new RuntimeException("Element not clickable after waiting: " + locator, e);
    }
}


public void select(WebElement element, int index)  {
	Select obj = new Select(element);
	obj.selectByIndex(index);
}
public void select(WebElement element, String Value)  {
	Select obj = new Select(element);
	obj.selectByVisibleText(Value);
}
public void select(String text,WebElement element)  {
	Select obj = new Select(element);
	obj.selectByVisibleText(text);
}
public void mouseHoverOnWebElement(WebDriver driver, WebElement element)  {
	Actions actions = new Actions(driver);
	actions.moveToElement(element).click().perform();;
}
public void clickOnWebElement(WebDriver driver, WebElement element)  {
	Actions actions = new Actions(driver);
	actions.moveToElement(element).click().perform();
}
public void switchDriverControlOnTitle(WebDriver driver, String title)
{
	String parentId = driver.getWindowHandle();
	Set<String> allIds = driver.getWindowHandles();
	allIds.remove(parentId);
	for(String id:allIds)  {
		driver.switchTo().window(id);
		if(driver.getTitle().contains(title))
			break;
	}
}
public void switchDriverControlOnCurrentURL(WebDriver driver, String URL)
{
	String parentId = driver.getWindowHandle();
	Set<String> allIds = driver.getWindowHandles();
	allIds.remove(parentId);
	for(String id:allIds)  {
		driver.switchTo().window(id);
		if(driver.getTitle().contains(URL))
			break;
	}
}
public void switchToFrame(WebDriver driver, int index)  {
	driver.switchTo().frame(index);
}
public void switchToFrame(WebDriver driver, String nameOrId)  {
	driver.switchTo().frame(nameOrId);
}
public void switchToFrame(WebDriver driver, WebElement frameElement)  {
	driver.switchTo().frame(frameElement);
}
public void switchToAlertAndAccept(WebDriver driver)   {
	driver.switchTo().alert().accept();
}
public void switchToAlertAndDismiss(WebDriver driver)   {
	driver.switchTo().alert().dismiss();
}
public String switchToAlertAndGetText(WebDriver driver)   {
	return driver.switchTo().alert().getText();
}
public void switchToAlertAndSendKeys(WebDriver driver, String text)   {
	driver.switchTo().alert().sendKeys(text);
}
public void doubleClick(WebDriver driver, WebElement element)    {
	Actions action = new Actions(driver);
	action.doubleClick(element).perform();
}
public void rightClick(WebDriver driver, WebElement element)    {
	Actions action = new Actions(driver);
	action.contextClick(element).perform();
}
}
