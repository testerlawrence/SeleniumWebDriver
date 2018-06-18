package webdriver.examples;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class T17WebTable {

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
		
		driver.get(prop.getProperty("population_wikipedia_url"));
		
		// xpath pattern
		//caption[contains(text(), 'ranked by population')]/parent::table/tbody/tr[1]/td[1]
		//caption[contains(text(), 'ranked by population')]/parent::table/tbody/tr[1]/td[2]
		//caption[contains(text(), 'ranked by population')]/parent::table/tbody/tr[1]/td[3]
		// ...
		//caption[contains(text(), 'ranked by population')]/parent::table/tbody/tr[2]/td[1]
		//caption[contains(text(), 'ranked by population')]/parent::table/tbody/tr[2]/td[2]
		// ...
		
		String xpath_begin = "//caption[contains(text(), 'ranked by population')]/parent::table/tbody/tr[";
		String xpath_middle = "]/td[";
		String xpath_end = "]";
		
		int rows = driver.findElements(By.xpath("//caption[contains(text(), 'ranked by population')]/parent::table/tbody//tr")).size();
		int columns = driver.findElements(By.xpath("//caption[contains(text(), 'ranked by population')]/parent::table/tbody/tr[1]//td")).size();		
		//System.out.println("Rows = " + rows);
		//System.out.println("Columns = " + columns);
		
		// To print all the data from the table
		// to skip the first row which has "World" information, i=2
		for(int i=2; i<=rows; i++)
		{
			for(int j=1; j<=columns; j++)
			{
				System.out.println(driver.findElement(By.xpath(xpath_begin + i + xpath_middle + j + xpath_end)).getText());
			}
			
			// To insert new line in the console between two countries' information
			System.out.println();
		}
		
		// To print a specific country's population
		// Method 1: Iterate through rows and columns and get cell value using for loop
		// Lengthy method
		// to skip the first row which has "World" information, i=2
		for(int i=2; i<=rows; i++)
		{
			// Country column number = 2
			if(driver.findElement(By.xpath(xpath_begin + i + xpath_middle + "2" + xpath_end + "/a")).getText().equals("India"))
			{
				System.out.println("India's population");
				
				// Population column number = 6
				System.out.println(driver.findElement(By.xpath(xpath_begin + i + xpath_middle + "6" + xpath_end)).getText());
				
				// Break the for loop once the required country is found.
				break;
			}
		}
		
		// To print a specific country's population
		// Method 2: Using customized xpath, parent, preceding-sibling, following-sibling tags
		// Efficient and fast
		System.out.println("\nUsing customized xpath");
		System.out.println(driver.findElement(By.xpath("//a[contains(text(), 'India')]/parent::td/following-sibling::td[4]")).getText());
	}
}