package webdriver.examples;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class T18DynamicGoogleSearch {

	public static void main(String[] args) {
		
		// let the system know about the location of chrome driver
		System.setProperty("webdriver.chrome.driver", "D:\\Lawrence\\Software\\chromedriver_win32\\chromedriver_win32\\chromedriver.exe");
		
		// RemoteWebDriver implements WebDriver
		// ChromeDriver extends RemoteWebDriver
		WebDriver driver = new ChromeDriver();
		
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
		
		driver.get("https://www.google.com");
		
		String searchKeyword = "selenium";
		driver.findElement(By.id("lst-ib")).sendKeys(searchKeyword);
		
		// xpath pattern of suggestions listbox
		//ul[@role='listbox']//li//div//div[2]//b --> This contains the suggested text
		List<WebElement> suggestionsList = driver.findElements(By.xpath("//ul[@role='listbox']//li//div//div[2]//b"));
		
		String suggestedText;
		int size = suggestionsList.size();
		for(int i=0; i<size; i++)
		{
			suggestedText = suggestionsList.get(i).getText();
			
			if((searchKeyword + " " + suggestedText).equals("selenium tutorial"))
			{
				suggestionsList.get(i).click();
				
				break;
			}
		}
	}
}