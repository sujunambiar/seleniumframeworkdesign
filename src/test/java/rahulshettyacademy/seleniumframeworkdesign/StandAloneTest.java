package rahulshettyacademy.seleniumframeworkdesign;

import java.time.Duration;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class StandAloneTest
{

	public static void main(String[] args) throws InterruptedException 
	
	{
		String productName = "ZARA COAT 3";
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver ();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get("https://rahulshettyacademy.com/client");
		driver.findElement(By.id("userEmail")).sendKeys("joadam32@gmail.com");
		driver.findElement(By.id("userPassword")).sendKeys("Learning123!");
		driver.findElement(By.id("login")).click();
		List <WebElement> products =driver.findElements(By.cssSelector("div.col-lg-4"));
		
		
		/*for (WebElement product:products)
			
		{
			if (product.getText().equalsIgnoreCase("ZARA COAT 3"))
			
			driver.findElement(By.className("btn w-10 rounded")).click();
			
			break;		
		}
		*/
		
		WebElement prod = products.stream().filter(product->product.findElement(By.cssSelector("b"))
		.getText().equals("ZARA COAT 3")).findFirst().orElse(null);
		
		
		prod.findElement(By.cssSelector(".card-body button:last-of-type")).click();
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));
		driver.findElement(By.cssSelector("[routerlink*='cart']")).click();
		List <WebElement> cartProducts= driver.findElements(By.cssSelector(".cartSection h3"));
		Boolean match = cartProducts.stream().anyMatch(cartProduct->cartProduct.getText().equalsIgnoreCase(productName));
		Assert.assertTrue(match);
		//driver.findElement(By.cssSelector("button[type='button']")).click();
		driver.findElement(By.cssSelector(".totalRow button")).click();
		driver.findElement(By.cssSelector("input[placeholder='Select Country']")).sendKeys("Ind");
		Thread.sleep(500);
		List <WebElement> places = driver.findElements(By.xpath("//section/button"));
		
		/*
	
		
		for (WebElement place:places)
			
		{
			if(place.getText().equalsIgnoreCase("India"))
				
			
			
			
			{
				
				place.click();
				
				break;
			}
			
			
		}
		
		driver.findElement(By.cssSelector(".action__submit")).click();
		
		*/
		Actions a = new Actions( driver);
		a.sendKeys(	driver.findElement(By.cssSelector("input[placeholder='Select Country']")), "India").build().perform();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results")));
		driver.findElement(By.xpath("//button[contains(@class, 'ta-item')])[2]")).click();
		driver.findElement(By.cssSelector(".action__submit")).click();
		String confirmMessage = driver.findElement(By.cssSelector(".hero-primary")).getText();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER"));
		driver.close();
		
		
		
	}

}
