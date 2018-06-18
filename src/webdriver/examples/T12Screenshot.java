package webdriver.examples;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class T12Screenshot {

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
		
		driver.get("http://www.rediff.com/");
		
		// ChromeDriver implements the Interface TakesScreenshot.
		// ((TakesScreenshot)driver) --> Top casting the ChromeDriver instance to TakesScreenshot instance 
		File src = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
				
		try
		{
			// Navigate to http://commons.apache.org/proper/commons-io/
			// Download latest Commons IO library
			// Open the project's properties ---> Java Build Path ---> Libraries tab ---> Add External Jars
			FileUtils.copyFile(src, new File("D:\\Temp\\rediff_homePage.jpg"));
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
}