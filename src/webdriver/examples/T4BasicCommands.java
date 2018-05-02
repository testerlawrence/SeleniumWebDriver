// Make sure that following components are up-to-date.
// Selenium Java driver is up-to-date.
// Chrome is up-to-date.
// Chrome driver is up-to-date.
// Java is not up-to-date.

package webdriver.examples;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class T4BasicCommands {

	public static void main(String[] args) {
		
		// let the system know about the location of chrome driver
		System.setProperty("webdriver.chrome.driver", "D:\\Lawrence\\Software\\chromedriver_win32\\chromedriver_win32\\chromedriver.exe");
		
		// RemoteWebDriver implements WebDriver
		// ChromeDriver extends RemoteWebDriver
		WebDriver driver = new ChromeDriver();
		
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		
		driver.get("http://www.google.com");
		
		System.out.println("Title: " + driver.getTitle());
		System.out.println("Current URL: " + driver.getCurrentUrl());
		
		driver.quit();
	}
}