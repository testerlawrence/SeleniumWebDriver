package webdriver.examples;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class T9IFrames {

	public static void main(String[] args) {
		
		// let the system know about the location of chrome driver
		System.setProperty("webdriver.chrome.driver", "D:\\Lawrence\\Software\\chromedriver_win32\\chromedriver_win32\\chromedriver.exe");
		
		// RemoteWebDriver implements WebDriver
		// ChromeDriver extends RemoteWebDriver
		WebDriver driver = new ChromeDriver();
		
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		
		driver.get("http://allwebco-templates.com/support/S_script_IFrame.htm");
		
		/*
		WebElement element = driver.findElement(By.xpath("//span[contains(text(), 'Sample embedded')]"));
		System.out.println(element.getText());
		*/
		
		driver.switchTo().frame(0); //index of this frame = 0
		//driver.switchTo().frame("frame_name");
		WebElement element = driver.findElement(By.xpath("//span[contains(text(), 'Sample embedded')]"));
		System.out.println(element.getText());
	}
}