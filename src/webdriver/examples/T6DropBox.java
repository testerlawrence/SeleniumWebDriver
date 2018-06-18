package webdriver.examples;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class T6DropBox {

	public static void main(String[] args) {
		
		// let the system know about the location of chrome driver
		System.setProperty("webdriver.chrome.driver", "D:\\Lawrence\\Software\\chromedriver_win32\\chromedriver_win32\\chromedriver.exe");
		
		// RemoteWebDriver implements WebDriver
		// ChromeDriver extends RemoteWebDriver
		WebDriver driver = new ChromeDriver();
		
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		
		driver.get("https://www.wikipedia.org/");
		
		Select language = new Select(driver.findElement(By.id("searchLanguage")));
		language.selectByIndex(7);
		System.out.println(language.getFirstSelectedOption().getText());
		//waitPlease();
		language.selectByVisibleText("Simple English");
		System.out.println(language.getFirstSelectedOption().getText());
		//waitPlease();
		language.selectByValue("fr");
		System.out.println(language.getFirstSelectedOption().getText());
	}
}