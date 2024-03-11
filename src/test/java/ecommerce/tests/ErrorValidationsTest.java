package ecommerce.tests;

import java.io.IOException;
import java.util.List;

import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import ecommerce.TestComponents.BaseTest;
import ecommerce.pageObjects.CartPage;
import ecommerce.pageObjects.CheckoutPage;
import ecommerce.pageObjects.ConfirmationPage;
import ecommerce.pageObjects.ProductPage;

public class ErrorValidationsTest extends BaseTest{

	@Test (groups= {"ErrorHandling"})
	public void LoginErrorValidation() throws IOException, InterruptedException{
		
		landingPage.loginApplication("rhein.bermillo@rahulshettyacademy.com", "1234567");	
		Assert.assertEquals("Incorrect email or password.", landingPage.getErrorMessage());
	
	
	}
	
	@Test (groups= {"ErrorHandling"})
	public void ProductErrorValidation() throws IOException, InterruptedException{
		
		String productName = "ZARA COAT 3";
		String initialCountryText = "hi";
		ProductPage productPage = landingPage.loginApplication("rhein.bermillo1@rahulshettyacademy.com", "Android12345");	
		//PRODUCT PAGE@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
		List<WebElement> products = productPage.getProductList();
		productPage.addProductToCart(productName);
		CartPage cartPage = productPage.goToCartPage();
		//CART@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
		Boolean match = cartPage.VerifyProductDisplay("ZARA COAT 33");
		Assert.assertFalse(match);
	
	
	}

}
