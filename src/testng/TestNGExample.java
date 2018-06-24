// TestNG - Unit Test Framework

package testng;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class TestNGExample {
	
	WebDriver driver;
	
	@DataProvider (name="keyword")
	public Object[] getSearchText()
	{
		return new Object[] {"xyz", "apple"};
	}
	
	@BeforeSuite (alwaysRun = true)
	public void beforeSuite()
	{
		// let the system know about the location of chrome driver
		System.setProperty("webdriver.chrome.driver", "D:\\Lawrence\\Software\\chromedriver_win32\\chromedriver_win32\\chromedriver.exe");
	}
	
	/*@BeforeClass (alwaysRun = true)
	public void beforeClass()
	{
		// let the system know about the location of chrome driver
		System.setProperty("webdriver.chrome.driver", "D:\\Lawrence\\Software\\chromedriver_win32\\chromedriver_win32\\chromedriver.exe");
	}*/
	
	// @BeforeMethod is executed before every @Test method
	@BeforeMethod (alwaysRun = true)
	public void beforeMethod()
	{
		// RemoteWebDriver implements WebDriver
		// ChromeDriver extends RemoteWebDriver
		driver = new ChromeDriver();
		
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		
		driver.manage().timeouts().pageLoadTimeout(40, TimeUnit.SECONDS);
		// This is dynamic wait.
		// Once the page is loaded within the specified time, remaining time will be ignored and next statement gets executed.
		// If the page is not loaded within the specified time, exception will be thrown.
		
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		// This is dynamic wait.
		// System will wait for the specified time, before throwing ElementNotFoundException.
		// This is global timeout for all elements.
		// Example: Karnataka is selected in State drop box. The values in City drop box will take some time to load.
		// Once the element is found within the specified time, remaining time will be ignored and next statement gets executed.
		
		driver.get("https://www.google.com/");
	}

	@Test (priority=1)
	public void testPageTitle()
	{
		Assert.assertEquals(driver.getTitle(), "Google");
	}
	
	@Test (priority=2, groups={"links"}, dependsOnMethods="testPageTitle")
	public void testGmailLinkNavigation()
	{
		driver.findElement(By.linkText("Gmail")).click();
		Assert.assertEquals(driver.getTitle(), "Gmail - Free Storage and Email from Google");
	}
	
	@Test (priority=3, groups="links", dependsOnMethods="testPageTitle")
	public void testImagesLinkNavigation()
	{
		driver.findElement(By.linkText("Images")).click();
		Assert.assertEquals(driver.getTitle(), "Google Images");
	}
	
	@Test
	@Parameters("searchText")
	public void testSearchFeature(String searchText)
	{
		//String searchText = "abc";
		driver.findElement(By.id("lst-ib")).sendKeys(searchText);
		driver.findElement(By.id("lst-ib")).sendKeys(Keys.ENTER);
		Assert.assertEquals(driver.getTitle(), searchText + " - Google Search");
	}
	
	@Test (dataProvider="keyword")	
	public void testSearchFeatureUsingDataProvider(String searchKeyword)
	{
		driver.findElement(By.id("lst-ib")).sendKeys(searchKeyword);
		driver.findElement(By.id("lst-ib")).sendKeys(Keys.ENTER);
		Assert.assertEquals(driver.getTitle(), searchKeyword + " - Google Search");
	}
	
	// @AfterMethod is executed after every @Test method
	@AfterMethod (alwaysRun = true)
	public void afterMethod()
	{
		driver.close();
	}
}