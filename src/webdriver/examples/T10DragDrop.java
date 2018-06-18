package webdriver.examples;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class T10DragDrop {

	public static void main(String[] args) {
		
		// let the system know about the location of chrome driver
		System.setProperty("webdriver.chrome.driver", "D:\\Lawrence\\Software\\chromedriver_win32\\chromedriver_win32\\chromedriver.exe");
		
		// RemoteWebDriver implements WebDriver
		// ChromeDriver extends RemoteWebDriver
		WebDriver driver = new ChromeDriver();
		
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		
		driver.get("http://code.makery.ch/library/dart-drag-and-drop/");
		
		driver.switchTo().frame(0);
		
		WebElement elementToBeMoved = driver.findElement(By.xpath("//a[@href='https://github.com/marcojakob/dart-dnd/tree/master/example/basic']/following-sibling::img[1]"));
		WebElement destination = driver.findElement(By.xpath("//a[@href='https://github.com/marcojakob/dart-dnd/tree/master/example/basic']/following-sibling::div"));
		
		Actions action = new Actions(driver);
		action.clickAndHold(elementToBeMoved).moveToElement(destination).release().build().perform();
	}
}