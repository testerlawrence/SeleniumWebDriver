// Make sure that following components are up-to-date.
// Selenium Java driver is up-to-date.
// Firefox is up-to-date.
// Gecko driver is up-to-date.
// Java is not up-to-date.

package webdriver.examples;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

public class T1LaunchFirefoxBrowser {

	public static void main(String[] args) {

		// let the system know about the location of gecko driver
		System.setProperty("webdriver.gecko.driver", "D:\\Lawrence\\Software\\GeckoDriver\\geckodriver-v0.20.1-win32\\geckodriver.exe");

		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities = DesiredCapabilities.firefox();
		capabilities.setBrowserName("firefox");
		// firefox version
		capabilities.setVersion("47.0.2");
		capabilities.setPlatform(Platform.WINDOWS);
		capabilities.setCapability("marionette", false);

		// RemoteWebDriver implements WebDriver
		// FirefoxDriver extends RemoteWebDriver
		WebDriver driver = new FirefoxDriver(capabilities);
		
		driver.get("http://www.google.com");
	}
}