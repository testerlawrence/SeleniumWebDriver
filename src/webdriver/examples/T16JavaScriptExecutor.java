package webdriver.examples;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class T16JavaScriptExecutor {

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
		
		// ChromeDriver class implements JavascriptExecutor Interface
		// (JavascriptExecutor)driver --> Top casting instance of ChromeDriver sub-class to instance of JavascriptExecutor interface
		JavascriptExecutor js = (JavascriptExecutor)driver;
		
		// refresh the page
		js.executeScript("history.go(0)");
		
		// display alert message
		js.executeScript("alert('My own message')");
		
		Alert alert = driver.switchTo().alert();
		alert.accept();
		
		// get page title
		String pageTitle = js.executeScript("return document.title;").toString();
		System.out.println("Page Title = " + pageTitle);
		
		// get page inner text
		String pageInnerText = js.executeScript("return document.documentElement.innerText;").toString();
		System.out.println(pageInnerText);
		
		// click element
		WebElement searchButton = driver.findElement(By.xpath(prop.getProperty("searchButtonLocatorXpath")));
		js.executeScript("arguments[0].click();", searchButton);
		
		// scroll down to the bottom of the page
		driver.findElement(By.linkText("Main page")).click();
		js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
		
		// scroll down to "Today's featured picture"
		WebElement todayFeaturedPicture = driver.findElement(By.xpath("//div[@id='mp-tfp']/table/tbody/tr/td/a/img"));
		js.executeScript("arguments[0].scrollIntoView(true);", todayFeaturedPicture);
	}
}