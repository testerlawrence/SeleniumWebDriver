package webdriver.examples;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class T21BrokenLinks {

	public static void main(String[] args) throws MalformedURLException, IOException {
		
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
		
		driver.get("https://www.wikipedia.org/");
		
		driver.findElement(By.xpath("//strong[contains(text(), 'English')]")).click();
		
		List<WebElement> linksList = driver.findElements(By.tagName("a"));
		
		List<WebElement> activeLinksList = new ArrayList<WebElement>();
		int linksListSize = linksList.size();
		WebElement link;
		
		for(int i=0; i < linksListSize; i++)
		{
			link = linksList.get(i);
			if(link.getAttribute("href") != null)
			{
				activeLinksList.add(link);
			}
		}
		
		int activeLinksListSize = activeLinksList.size();
		String url;
		for(int j=0; j<activeLinksListSize; j++)
		{
			url = activeLinksList.get(j).getAttribute("href");
			
			// new URL().openConnection() returns the instance of implementation of class URLConnection.
			// casting the instance of URLConnection to HttpURLConnection
			HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
			
			connection.connect();
			String response = connection.getResponseMessage();
			connection.disconnect();
			System.out.println(url + "---> " + response);
		}
	}
}