package ecommerce.tests;

import java.io.IOException;
import java.util.List;
import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import ecommerce.TestComponents.BaseTest;
import ecommerce.pageObjects.CartPage;
import ecommerce.pageObjects.ProductPage;
import ecommerce.pageObjects.CheckoutPage;
import ecommerce.pageObjects.ConfirmationPage;

public class SubmitOrderTest extends BaseTest{

	@Test
	public void submitOrder() throws IOException, InterruptedException{
		
		String productName = "ZARA COAT 3";
		String initialCountryText = "hi";
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
	
	
	}

}
