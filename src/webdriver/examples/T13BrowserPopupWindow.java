package webdriver.examples;

import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class T13BrowserPopupWindow {

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
		
		driver.get("https://www.naukri.com/");
		
		String mainWindowHandle = driver.getWindowHandle();
		System.out.println("Main window handle: " + mainWindowHandle);
		
		// Set is a collection class like List, ArrayList and HashMap
		Set<String> handles = driver.getWindowHandles();
		
		// Set object does not store value on the basis of index.
		// We have to use iterator to get the values from Set object.
		Iterator<String> iterator = handles.iterator();

		String handle;
		// Iterator through all window handles
		while(iterator.hasNext())
		{
			handle = iterator.next();
			System.out.println(handle.toString());
			
			// Leave the Main Window and perform actions on other windows
			if(!handle.equals(mainWindowHandle))
			{
				// switch to window
				driver.switchTo().window(handle);
				if(driver.getTitle().equals("SYKES"))
				{
					System.out.println("SYKES");
					// If the title of the window is SYKES, close that window.
					driver.close();
				}
				else if(driver.getTitle().equals("RBS"))
				{
					System.out.println("RBS");
					driver.findElement(By.xpath("//img[@alt='RBS']")).click();
					
					/*Set<String> handles2 = driver.getWindowHandles();
					Iterator<String> iterator2 = handles2.iterator();
					while(iterator2.hasNext())
					{
						System.out.println(iterator2.next().toString());
					}*/
				}
			}
		}
	}
}