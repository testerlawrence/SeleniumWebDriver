// Create a .properties file as below.
// package -> right click -> New -> Other -> General -> File

package webdriver.examples;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class T14ObjectRepository {

	public static void main(String[] args) {
		
		Properties prop = new Properties();
		FileInputStream ip = null;
		
		try
		{
			ip = new FileInputStream("./src/webdriver/examples/config.properties");
			
		}
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}
		
		try
		{
			prop.load(ip);
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		
		// let the system know about the location of chrome driver
		//System.setProperty("webdriver.chrome.driver", "D:\\Lawrence\\Software\\chromedriver_win32\\chromedriver_win32\\chromedriver.exe");
		System.setProperty(prop.getProperty("driverType"), prop.getProperty("driverFileLocation"));
		
		// RemoteWebDriver implements WebDriver
		// ChromeDriver extends RemoteWebDriver
		WebDriver driver = new ChromeDriver();
		
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		
		//driver.manage().timeouts().pageLoadTimeout(40, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(Integer.parseInt(prop.getProperty("pageLoadTimeout")), TimeUnit.SECONDS);
		// This is dynamic wait.
		// Once the page is loaded within the specified time, remaining time will be ignored and next statement gets executed.
		// If the page is not loaded within the specified time, exception will be thrown.
		
		//driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(Integer.parseInt(prop.getProperty("implicitWaitTimeout")), TimeUnit.SECONDS);
		// This is dynamic wait.
		// System will wait for the specified time, before throwing ElementNotFoundException.
		// This is global timeout for all elements.
		// Example: Karnataka is selected in State drop box. The values in City drop box will take some time to load.
		// Once the element is found within the specified time, remaining time will be ignored and next statement gets executed.
		
		//driver.get("https://www.wikipedia.org/");
		driver.get(prop.getProperty("url"));
		
		// Enter search text
		//driver.findElement(By.id("searchInput")).sendKeys("selenium");
		driver.findElement(By.id(prop.getProperty("searchInputLocatorId"))).sendKeys(prop.getProperty("searchKeyword"));
		
		// click the search button
		//driver.findElement(By.xpath("//i[@class='sprite svg-search-icon']")).click();
		driver.findElement(By.xpath(prop.getProperty("searchButtonLocatorXpath"))).click();
	}
}