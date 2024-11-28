package rahulshettyacademy.seleniumframeworkdesign;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.chrome.ChromeDriver;
import java.util.List;

public class StandAloneTest
{

	public static void main(String[] args) 
	
	{
		
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver ();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get("https://rahulshettyacademy.com/client");
		driver.findElement(By.id("userEmail")).sendKeys("joadam32@gmail.com");
		driver.findElement(By.id("userPassword")).sendKeys("Learning123!");
		driver.findElement(By.id("login")).click();
		List <WebElement> items =driver.findElements(By.cssSelector("div.col-lg-4"));
		
		for (WebElement item:items)
			
		{
			if (item.getText().equalsIgnoreCase("ZARA COAT 3"))
			
			driver.findElement(By.className("btn w-10 rounded")).click();
			
			break;
				
		}
		
	}

}
