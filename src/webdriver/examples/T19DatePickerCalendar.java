package webdriver.examples;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class T19DatePickerCalendar {
	
	static WebDriver driver;

	public static void main(String[] args) {
		
		// let the system know about the location of chrome driver
		System.setProperty("webdriver.chrome.driver", "D:\\Lawrence\\Software\\chromedriver_win32\\chromedriver_win32\\chromedriver.exe");
		
		// RemoteWebDriver implements WebDriver
		// ChromeDriver extends RemoteWebDriver
		//WebDriver driver = new ChromeDriver();
		driver = new ChromeDriver();
		
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
		
		String date = "01";
		String monthYear = "September'18";
		
		driver.get("https://www.yatra.com/");
		
		// click Depart Date
		driver.findElement(By.name("flight_origin_date")).click();
		
		String activeMonth = driver.findElement(By.xpath("//div[@class='active-month-holder']")).getText();
		//System.out.println(activeMonth);
		
		if(activeMonth.equals(monthYear))
		{
			List<WebElement> currentMonthRowsList = driver.findElements(By.xpath("//div[@id='month-scroll0']//tbody//tr"));
			int currentMonthRows = currentMonthRowsList.size();
			//System.out.println(currentMonthRows);
			
			String xpath_begin = "//div[@id='month-scroll0']//tbody/tr[";
			
			//selectDate(int rows, String xpath_begin, String dateToBeSelected)
			selectDate(currentMonthRows, xpath_begin, date);
		}
		else
		{
			List<WebElement> nonActiveMonthsList = driver.findElements(By.xpath("//div[@id='monthWrapper']//div[@class='js-month-container']//div[@class='month-title']"));
			int nonActiveMonths = nonActiveMonthsList.size();
			//System.out.println(nonActiveMonthsList.size());
			
			for(int i=0; i<nonActiveMonths; i++)
			{
				//System.out.println(nonActiveMonthsList.get(i).getText());
				if(nonActiveMonthsList.get(i).getText().equals(monthYear))
				{
					//driver.findElements(By.xpath("//div[@id='month-scroll1']//tbody//tr"));
					// (i+1) --> non-active months start from scroll1
					List<WebElement> dayRowsList = driver.findElements(By.xpath("//div[@id='month-scroll" + (i+1) + "']//tbody//tr"));
					//System.out.println(dayRowsList.size());
					String xpath_begin = "//div[@id='month-scroll" + (i+1) + "']//tbody/tr[";
					
					//selectDate(int rows, String xpath_begin, String dateToBeSelected)
					selectDate(dayRowsList.size(), xpath_begin, date);
					
					break;
				}
			}
		}
	}
	
	public static void selectDate(int rows, String xpath_begin, String dateToBeSelected)
	{
		String dateInId;
		String xpath_middle = "]/td[";
		String xpath_end = "]";
		int positionOfSlash;
		boolean found = false;
		
		for(int i=1; i<=rows; i++)
		{
			// usually column=7 in calendars
			for(int j=1; j<=7; j++)
			{
				//dateInId = driver.findElement(By.xpath("//div[@id='month-scroll0']//tbody/tr[" + i + "]/td[" + j + "]")).getAttribute("id");
				dateInId = driver.findElement(By.xpath(xpath_begin + i + xpath_middle + j + xpath_end)).getAttribute("id");
				//System.out.println(dateInId);
				
				if(dateInId.length() > 0)
				{
					positionOfSlash = dateInId.indexOf("/");
					//System.out.println(dateInId.indexOf("/"));
					String dateAlone = dateInId.substring(0, positionOfSlash);
					//System.out.println(dateAlone);
					
					if(dateAlone.equals(dateToBeSelected))
					{
						//driver.findElement(By.xpath("//div[@id='month-scroll0']//tbody/tr[" + i + "]/td[" + j + "]")).click();
						driver.findElement(By.xpath(xpath_begin + i + xpath_middle + j + xpath_end)).click();
						found = true;
						break;
					}
				}
			}
			
			if(found)
			{
				break;
			}
		}
		
		if(!found)
		{
			System.out.println("Either date is invalid or the calendar does not display the date");
		}
	}
}