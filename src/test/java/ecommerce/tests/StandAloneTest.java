package ecommerce.tests;

import java.time.Duration;
import java.util.List;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import io.github.bonigarcia.wdm.WebDriverManager;

public class StandAloneTest {

	public static void main(String[] args) {
		
		String productName = "ZARA COAT 3";
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		driver.get("https://rahulshettyacademy.com/client");
		//LANDING PAGE@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
		//LandingPage landingPage = new LandingPage(driver);
		driver.findElement(By.xpath("//input[@id='userEmail']")).sendKeys("rhein.bermillo@rahulshettyacademy.com");
		driver.findElement(By.xpath("//input[@id='userPassword']")).sendKeys("Android12345");
		driver.findElement(By.xpath("//input[@id='login']")).click();
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3")));
		List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));

		WebElement prod = products.stream()
				.filter(product->product.findElement(By.cssSelector("b")).getText().equals(productName)).findFirst().orElse(null);
		prod.findElement(By.cssSelector(".card-body button:last-child")).click();
		
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));
		
		driver.findElement(By.xpath("//button[@routerlink='/dashboard/cart']")).click();
		//driver.findElement(By.cssSelector("	[routerlink*='cart']")).click();
	
		//CART@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@class='cartSection']/h3")));
		List<WebElement> carts = driver.findElements(By.xpath("//*[@class='cartSection']/h3"));

		Boolean match = 
		carts.stream()
			.anyMatch(cart->cart.getText().equalsIgnoreCase(productName));

		Assert.assertTrue(match);
		
		driver.findElement(By.xpath("//button[contains(text(),'Checkout')]")).click();
		//AUTO SUGGEST DROPDOWN@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@placeholder='Select Country']")));
		driver.findElement(By.xpath("//input[@placeholder='Select Country']")).sendKeys("hi");
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//i[@class='fa fa-search']")));
		List<WebElement> countries = driver.findElements(By.xpath("//span[@class='ng-star-inserted']"));
		Actions actions = new Actions(driver);
	
		//iteration to check the desired country
				for (int i=0; i<=countries.size(); i++) {
					if (countries.get(i).getText().equalsIgnoreCase("Philippines")){
						actions.moveToElement(countries.get(i)).click().build().perform();
						break;
					}
				}
		driver.findElement(By.xpath("//a[.='Place Order ']")).click();
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".hero-primary")));
		String confirmMessage = driver.findElement(By.cssSelector(".hero-primary")).getText();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase("Thankyou for the order."));
		driver.close();
	}

}
