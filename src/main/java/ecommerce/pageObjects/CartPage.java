package ecommerce.pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import ecommerce.AbstractComponents.AbstractComponent;

public class CartPage extends AbstractComponent{

	WebDriver driver;
	public CartPage(WebDriver driver)//constructor
	{
		//initialization
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	//Page Factory
	@FindBy(css=".cartSection h3")
	private List<WebElement> cartProducts;
	@FindBy(xpath="//button[contains(text(),'Checkout')]")
	WebElement checkOutEle;
	
	By cartProduct = By.cssSelector(".cartSection h3");

	//################################################ Action Classes

	public Boolean VerifyProductDisplay(String productName) {
		waitForElementToAppear(cartProduct);
		Boolean match = 
				cartProducts.stream()
			.anyMatch(cart->cart.getText().equalsIgnoreCase(productName));
		return match;
	}
	
	public CheckoutPage goToCheckoutPage() {
		checkOutEle.click();
		CheckoutPage checkoutPage = new CheckoutPage(driver);
		return checkoutPage;
	}
	
}
