package ecommerce.tests;

import java.io.IOException;
import java.util.List;
import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ecommerce.TestComponents.BaseTest;
import ecommerce.pageObjects.CartPage;
import ecommerce.pageObjects.ProductPage;
import ecommerce.pageObjects.CheckoutPage;
import ecommerce.pageObjects.ConfirmationPage;
import ecommerce.pageObjects.OrderPage;

public class SubmitOrderTest extends BaseTest{
	String productName = "ZARA COAT 3";
	@Test (dataProvider = "getData", groups= {"Purchase"})
	public void submitOrder(String email, String password, String productName) throws IOException, InterruptedException{
		
		
		String initialCountryText = "hi";
		ProductPage productPage = landingPage.loginApplication(email, password);	
		//PRODUCT PAGE@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
		List<WebElement> products = productPage.getProductList();
		productPage.addProductToCart(productName);
		CartPage cartPage = productPage.goToCartPage();
		//CART@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
		Boolean match = cartPage.VerifyProductDisplay(productName);
		Assert.assertTrue(match);
		CheckoutPage checkoutPage = cartPage.goToCheckoutPage();
		//CHECKOUT PAGE@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
		checkoutPage.checkCountryDropdown(initialCountryText);
		checkoutPage.selectCountry("Philippines");
		//CONFIRMATION PAGE@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
		ConfirmationPage confirmationPage = checkoutPage.submitOrder();
		String confirmMessage = confirmationPage.getConfirmationMessage();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase("Thankyou for the order."));
	
	
	}
	@Test (dependsOnMethods = {"submitOrder"})
	public void OrderHistoryTest() {
		ProductPage productPage = landingPage.loginApplication("rhein.bermillo@rahulshettyacademy.com", "Android12345");
		OrderPage ordersPage = productPage.goToOrdersPage();
		Assert.assertTrue(ordersPage.verifyOrderDisplay(productName));
	}
	
	@DataProvider
	public Object[][] getData() {
		return new Object [][] {{"rhein.bermillo@rahulshettyacademy.com","Android12345","ZARA COAT 3"},{"rhein.bermillo1@rahulshettyacademy.com","Android12345","IPHONE 13 PRO"}};
	}
}
