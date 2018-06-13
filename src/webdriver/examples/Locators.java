package webdriver.examples;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Locators {
	
	static WebDriver driver;

	public static void main(String[] args) throws InterruptedException {
		
		// let the system know about the location of chrome driver
		System.setProperty("webdriver.chrome.driver", "D:\\Lawrence\\Software\\chromedriver_win32\\chromedriver_win32\\chromedriver.exe");
		
		// RemoteWebDriver implements WebDriver
		// ChromeDriver extends RemoteWebDriver
		driver = new ChromeDriver();
		
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		
		driver.get("http://www.google.com");
		
		// locate element by id
		driver.findElement(By.id("lst-ib")).sendKeys("abc");
		driver.findElement(By.id("lst-ib")).sendKeys(Keys.ENTER);
		
		navigateBack();
		
		// locate element by name
		driver.findElement(By.name("q")).sendKeys("def");
		driver.findElement(By.name("q")).sendKeys(Keys.ENTER);
		
		navigateBack();
		
		// locate element by link text
		driver.findElement(By.linkText("Gmail")).click();
		
		navigateBack();
		
		// locate element by partial link text
		driver.findElement(By.partialLinkText("mail")).click();
		
		navigateBack();
		
		// locate element by css selector - <tagName#id>
		driver.findElement(By.cssSelector("input#lst-ib")).sendKeys("ghi");
		driver.findElement(By.cssSelector("input#lst-ib")).sendKeys(Keys.ENTER);
		
		navigateBack();
		
		// locate element by css selector - <tagName.className>
		driver.findElement(By.cssSelector("input.gsfi")).sendKeys("css");
		driver.findElement(By.cssSelector("input.gsfi")).sendKeys(Keys.ENTER);
		
		navigateBack();
		
		// locate element by css selector - <tagName[attribute='value']>
		//driver.findElement(By.cssSelector("input[class='gsfi']")).sendKeys("attribute");
		//driver.findElement(By.cssSelector("input[class='gsfi']")).sendKeys(Keys.ENTER);
		/**** since there are 3 elements with the same cssSelector, this does not work.*****/
		
		// locate element by css selector - <tagName[attribute='value']>
		driver.findElement(By.cssSelector("input[title='Search']")).sendKeys("attribute");
		driver.findElement(By.cssSelector("input[title='Search']")).sendKeys(Keys.ENTER);
	}
	
	private static void navigateBack()
	{
		try
		{
			Thread.sleep(2000);
		}
		catch (InterruptedException e)
		{
			e.printStackTrace();
		}		
		
		driver.navigate().back();
		
		try
		{
			Thread.sleep(2000);
		}
		catch (InterruptedException e)
		{
			e.printStackTrace();
		}
	}
}