package webdriver.examples;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class T5Locators {
	
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
		
		navigateBack();
		
		// locate element by css selector - <tagName[attribute='value'][attribute='value']>
		driver.findElement(By.cssSelector("input[title='Search'][name='q']")).sendKeys("multiple attributes");
		driver.findElement(By.cssSelector("input[title='Search'][name='q']")).sendKeys(Keys.ENTER);
		
		navigateBack();
		
		// locate elements by class name
		List<WebElement> elementsListByClass = driver.findElements(By.className("gsfi"));
		int size = elementsListByClass.size();
		int i = 0;
		for(; i<size; i++)
		{
			System.out.println(elementsListByClass.get(i));
		}
		
		// locate elements by tag name
		List<WebElement> elementsListByTag = driver.findElements(By.tagName("a"));
		size = elementsListByTag.size();
		i = 0;
		for(; i<size; i++)
		{
			System.out.println(elementsListByTag.get(i).getText());
		}
		
		// locate element by xpath - //tagName[@attribute='value']
		driver.findElement(By.xpath("//input[@title='Search']")).sendKeys("xpath attribute value");
		driver.findElement(By.xpath("//input[@title='Search']")).sendKeys(Keys.ENTER);
		
		navigateBack();
		
		// locate element by xpath - //tagName[contains(@attribute, 'value/part_of_value')]
		driver.findElement(By.xpath("//input[contains(@title, 'Search')]")).sendKeys("xpath contains");
		driver.findElement(By.xpath("//input[contains(@title, 'Search')]")).sendKeys(Keys.ENTER);
		
		navigateBack();
		
		// locate element by xpath - //tagName[starts-with(@attribute, 'value/part_of_value')]
		driver.findElement(By.xpath("//input[starts-with(@title, 'Sea')]")).sendKeys("xpath starts-with");
		driver.findElement(By.xpath("//input[starts-with(@title, 'Sea')]")).sendKeys(Keys.ENTER);
		
		navigateBack();
		
		// locate element by xpath - //tagName[ends-with(@attribute, 'value/part_of_value')]
		//driver.findElement(By.xpath("//input[ends-with(@title, 'arch')]")).sendKeys("xpath starts-with");
		//driver.findElement(By.xpath("//input[ends-with(@title, 'arch')]")).sendKeys(Keys.ENTER);
		// The ends-with function is part of XPath 2.0 but browsers generally only support 1.0.
		// https://stackoverflow.com/questions/36053559/how-to-locate-dynamic-element-using-xpathends-with-function-not-working
		
		// work around for ends-with
		driver.findElement(By.xpath("//input[substring(@title, string-length(@title) - string-length('arch') +1) = 'arch']")).sendKeys("xpath ends-with");
		driver.findElement(By.xpath("//input[substring(@title, string-length(@title) - string-length('arch') +1) = 'arch']")).sendKeys(Keys.ENTER);
		
		navigateBack();
		
		// locate element by xpath - //tagName[contains(text(), 'inner_text/part_of_inner_text')]
		driver.findElement(By.xpath("//a[contains(text(), 'mail')]")).click();
		
		navigateBack();
		
		// locate element by xpath using 'parent' and 'preceding-sibling'		
		driver.findElement(By.xpath("//a[contains(text(), 'Images')]/parent::div/preceding-sibling::div/a")).click();
		// click Gmail link
		
		navigateBack();
		
		// locate element by xpath using 'parent' and 'following-sibling'		
		driver.findElement(By.xpath("//a[contains(text(), 'Gmail')]/parent::div/following-sibling::div/a")).click();
		// click Images link
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