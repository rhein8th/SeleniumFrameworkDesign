package rheinbermillo;

import java.time.Duration;
import java.util.List;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import ecommerce.pageObjects.CartPage;
import ecommerce.pageObjects.LandingPage;
import ecommerce.pageObjects.ProductPage;
import ecommerce.pageObjects.CheckoutPage;
import ecommerce.pageObjects.ConfirmationPage;
import io.github.bonigarcia.wdm.WebDriverManager;

public class SubmitOrderTest {

	public static void main(String[] args) {
		
		String productName = "ZARA COAT 3";
		String initialCountryText = "hi";
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		//WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		//LANDING PAGE@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
		LandingPage landingPage = new LandingPage(driver);
		landingPage.goTo();
		ProductPage productPage = landingPage.loginApplication("rhein.bermillo@rahulshettyacademy.com", "Android12345");	
		//PRODUCT PAGE@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
		List<WebElement> products = productPage.getProductList();
		productPage.addProductToCart(productName);
		CartPage cartPage = productPage.goToCartPage();
		//CART@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
		Boolean match = cartPage.VerifyProductDisplay(productName);
		Assert.assertTrue(match);
		CheckoutPage checkoutPage = cartPage.goToCheckoutPage();
		checkoutPage.checkCountryDropdown(initialCountryText);
		checkoutPage.selectCountry("Philippines");
		//AUTO SUGGEST DROPDOWN@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
		ConfirmationPage confirmationPage = checkoutPage.submitOrder();
		String confirmMessage = confirmationPage.getConfirmationMessage();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase("Thankyou for the order."));
		driver.close();
	
	}

}
