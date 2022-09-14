package Library;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.configuration.Configuration;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.cucumber.java.Scenario;

public class CommonLib {
	
	
	public static Configuration config = null;
	public static WebDriver driver;
	
	//Method to launch the application
	public static void AppLaunch() throws Throwable {
		SelectDriver();
		SelectEnvironment();
//		driver.get(Environment.baseURL);
	    driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(7, TimeUnit.SECONDS);
		
	}
	
	public static void SelectDriver() throws Throwable {
		FileReader reader = new FileReader(System.getProperty("user.dir")+"/config/application.properties"); //Reading configuration file
	    Properties prop = new Properties();
	    prop.load(reader);
	    String browser = prop.getProperty("chromeDriver"); // Assigning String value form configuraion file
	    //String ver = prop.getProperty("version");        // Assigning String value form configuraion file
	    
	    //Select chromedriver
	    if (browser.equalsIgnoreCase("chromeWinx32")) {
	    	System.setProperty("webdriver.chrome.driver",
                    System.getProperty("user.dir")+"/Driver/chromedriver.exe"); // setting path of the ChromeDriver
//	        driver = new ChromeDriver();
	    	
	    //HEADLESS MODE --------------------------------------------------------------------------//
	      
	    	//create object of chrome options
	        ChromeOptions options = new ChromeOptions();
	        //add the headless argument
	        options.addArguments(
//	        		"headless", 	//Comment this line to remove headless mode
	        		"--window-size=1920,1080",
	        		"--no-sandbox",
	        		"--disable-dev-shm-usage"
	        		);
	        //pass the options parameter in the Chrome driver declaration
	        driver = new ChromeDriver(options);
	        
	     //HEADLESS MODE --------------------------------------------------------------------------//
	    
	    }
	}
	
	public static void SelectEnvironment() throws Throwable {
		FileReader reader = new FileReader(System.getProperty("user.dir")+"/config/application.properties"); //Reading configuration file
	    Properties prop = new Properties();
	    prop.load(reader);
	    String env = prop.getProperty("applicationURL"); // Assigning String value form configuraion file
	    driver.get(env);
	}
	
	//Method to check the element is exist
	public static void isElementVerification(String automationID) throws Exception {
		driver.findElement(By.xpath(automationID));
	
	}
	
	public static void isElementVerificationNotDisplayed(String automationID) throws Exception {
		WebDriverWait wait2 = new WebDriverWait(driver, 10);
		wait2.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(automationID)));
	}
	
	//Method to check the text of the element
	public static void textValidation(String automationID, String expectedValue) throws Exception {
		String Element = driver.findElement(By.xpath(automationID)).getText();
		String actualText = Element;
		Assert.assertTrue("\n" + " Text - Actual : " + actualText + "\n" + " Text - Expected : " + expectedValue, Element.contains(expectedValue));
	}
	
	//Method to click the element
	public static void isElementVerifyClick(String automationID) throws Throwable {
		WebElement Element = driver.findElement(By.xpath(automationID));
		Element.click();
		
	}
	
	public static void isElementVerifyClickByClass(String automationID) throws Throwable {
		WebElement Element = driver.findElement(By.className(automationID));
		Element.click();
	}
	
	public static void isElementVerifyClickByCSS(String automationID) throws Throwable {
		WebElement Element = driver.findElement(By.cssSelector(automationID));
		Element.click();
	}
	
	public static void isElementVerifyEnabled(String automationID) throws Throwable {
		WebElement Element = driver.findElement(By.xpath(automationID));
		Assert.assertTrue("Button is disabled !", Element.isEnabled());
	}
	
	public static void isElementVerifyDisabled(String automationID) throws Throwable {
		WebElement Element = driver.findElement(By.xpath(automationID));
		Assert.assertFalse("Button is enabled !", Element.isEnabled());
	}
	
	public static void getAttributeValue(String automationID,String automationID2, String attribute) throws Throwable {
		String Element = driver.findElement(By.xpath(automationID)).getAttribute(attribute);
		CommonLib.enterValue(automationID2, Element);
	}
	
	public static void getTextValue(String automationID, String automationID2) throws Throwable {
		String Element = driver.findElement(By.xpath(automationID)).getText();
		CommonLib.enterValue(automationID2, Element);
	}
	
	public static String getTextFromElement(String automationID) throws Throwable {
		String Element = driver.findElement(By.xpath(automationID)).getText();
		System.out.println("The text of this element is : " + Element);
		return Element;
	}
	
	//Method to enter the text to the element
	public static void enterValue(String automationID, String input) throws Throwable {
		WebElement Element = driver.findElement(By.xpath(automationID));
		Element.clear();
		Element.sendKeys(input);
		Thread.sleep(500);
	}
	
	public static void clearValue(String automationID) throws Throwable {
		WebElement Element = driver.findElement(By.xpath(automationID));
		Element.clear();
	}
	
	//Method to select value from dropdown, checkbox, or radio btn
	public static void selectValueByOption(String automationID, String input) throws Throwable {
		WebElement Element = driver.findElement(By.xpath(automationID));
		Select select = new Select(Element);
		select.selectByVisibleText(input);
	}
	
	//Method to scroll to the element
	public static void scrollToElement(String automationID) throws Throwable {
		WebElement Element = driver.findElement(By.xpath(automationID));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", Element);
		Thread.sleep(500); 
	}
	
	public static void scrollToTopPage() throws Throwable {
		((JavascriptExecutor) driver)
	    .executeScript("window.scrollTo(0, 0)");
		Thread.sleep(3000); 
	}
	
	public static void scrollToBottomPage() throws Throwable {
		((JavascriptExecutor) driver)
	    .executeScript("window.scrollTo(0, document.body.scrollHeight)");
		Thread.sleep(3000); 
	}
	
	//Method to hover to the element
	public static void hoverToElement(String automationID) throws Throwable {
		WebElement Element = driver.findElement(By.xpath(automationID));
		Actions action = new Actions(driver);
		action.moveToElement(Element).perform();
	}
	
	//Method to handle the stale element or the hidden element
	public static void staleElementToClick(String automationID) {
		//way1
		WebDriverWait wait2 = new WebDriverWait(driver, 10);
		wait2.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(automationID)));
	}
	
	//Method to wait element to be displayed
	public static void waitElementToDisplay(String automationID) {
		//way1
		WebDriverWait wait2 = new WebDriverWait(driver, 50);
		wait2.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(automationID)));
	}
	
	//Method to take a screenshot of failed test step
	public static void captureScreenshot(WebDriver driver,Scenario scenario, String screenshotName) {
		if(scenario.isFailed()) {
			try {
				DateFormat dateFormat = new SimpleDateFormat("d-MMM-YY HH-mm-ss");
		        Date date = new Date();
				TakesScreenshot ts=(TakesScreenshot)driver;
				File source=ts.getScreenshotAs(OutputType.FILE);
				FileHandler.copy(source, new File("./Screenshots/"+screenshotName+"-"+dateFormat.format(date)+".png"));
				System.out.println("============== Successfully captured a screenshot of a failed test step ==============");
			}
			catch (Exception e) {
				System.out.println("=============="+"Exception while taking screenshot : "+e.getMessage()+"==============");
			}
		}
	}
	
	public static void captureScreenshotToReport(Scenario scenario) {
		if(scenario.isFailed()) {
			try {
				final byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
				scenario.attach(screenshot, "image/png", "image");
				System.out.println("============== Successfully captured a screenshot of a failed test step ==============");
			}
			catch (Exception e ) {
				System.out.println("=============="+"Exception while taking screenshot : "+e.getMessage()+"==============");
			}
		}
	}
	
	// Method for get value from config.properties
		public static String getValueFromProperty(String key) {
			Properties prop = new Properties();
			InputStream input = null;
			String value = null;
			try {
				input = new FileInputStream(System.getProperty("user.dir")+"/config/config.properties");
				// load a properties file
				prop.load(input);
				// get the property value and print it out
				value = prop.getProperty(key);
				} catch (IOException ex) {
					ex.printStackTrace();
					}				
			return value;
			}
	
	//Method to close the application
	public static void closeApplication() throws InterruptedException {
		driver.quit();
	}
	
	public static void closeCurrentTab() throws InterruptedException {
		driver.close();
	}
	
	//Wait Time
	public static void shortSleep() throws Throwable {
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
	}
	
	public static void mediumSleep() throws Throwable {
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	}
	
	public static void longSleep() throws Throwable {
		driver.manage().timeouts().implicitlyWait(7, TimeUnit.SECONDS);
	}
	
	//Method to refresh the page
	public static void refreshPage() throws Throwable {
		driver.navigate().refresh();
	}

	//Method to navigate to certain URL
	public static void navigateToUrl(String url) throws Throwable {
		driver.navigate().to(url);
	}
	
	public static void currentNewTabUrlValidation(String expectedUrl) throws Throwable {
		//get window handlers as list
		List<String> browserTabs = new ArrayList<String> (driver.getWindowHandles());
		//switch to new tab
		driver.switchTo().window(browserTabs.get(1));
		//check is it correct page opened or not (e.g. check page's title)
		String currentUrl = driver.getCurrentUrl();
		Assert.assertTrue("\n" + " Url - Actual : " + currentUrl + "\n" + " Url - Expected : " + expectedUrl, currentUrl.contains(expectedUrl));
		//then close tab and get back
		driver.close();
		driver.switchTo().window(browserTabs.get(0));
	}
	
	public static void currentTabUrlValidation(String expectedUrl) throws Throwable {
		//check is it correct page opened or not (e.g. check page's title)
		String currentUrl = driver.getCurrentUrl();
		Assert.assertTrue("\n" + " Url - Actual : " + currentUrl + "\n" + " Url - Expected : " + expectedUrl, currentUrl.contains(expectedUrl));
		//then close tab and get back
	}

	public static void switchTab() throws Throwable {
		List<String> browserTabs = new ArrayList<String> (driver.getWindowHandles());
		driver.switchTo().window(browserTabs.get(1));
	}

}
