// Make sure that following components are up-to-date.
// Selenium Java driver is up-to-date.
// IE is up-to-date.
// IE driver is up-to-date.
// Java is not up-to-date.

package webdriver.examples;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

public class T3LaunchInternetExplorerBrowser {

	public static void main(String[] args) {
		
		// let the system know about the location of IE driver
		System.setProperty("webdriver.ie.driver", "D:\\Lawrence\\Software\\IEDriver\\IEDriverServer_Win32_3.11.1\\IEDriverServer.exe");
		
		// RemoteWebDriver implements WebDriver
		// IEDriver extends RemoteWebDriver
		WebDriver driver = new InternetExplorerDriver();
		
		driver.get("http://www.google.com");
	}
}