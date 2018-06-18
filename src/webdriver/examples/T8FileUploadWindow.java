package webdriver.examples;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class T8FileUploadWindow {

	public static void main(String[] args) {
		
		// let the system know about the location of chrome driver
		System.setProperty("webdriver.chrome.driver", "D:\\Lawrence\\Software\\chromedriver_win32\\chromedriver_win32\\chromedriver.exe");
		
		// RemoteWebDriver implements WebDriver
		// ChromeDriver extends RemoteWebDriver
		WebDriver driver = new ChromeDriver();
		
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		
		driver.get("https://www.naukri.com/");
		
		// type must be "file"
		driver.findElement(By.id("input_resumeParser")).sendKeys("D:\\Temp\\SampleDoc.doc");
		// mouse hover the element and the file name will be visible
	}
}