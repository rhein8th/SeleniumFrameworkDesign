package ecommerce.pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import ecommerce.AbstractComponents.AbstractComponent;

public class OrderPage extends AbstractComponent{

	WebDriver driver;
	public OrderPage(WebDriver driver)//constructor
	{
		//initialization
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	//Page Factory
	@FindBy(css="tr td:nth-child(3)")
	private List<WebElement> productNames;
	@FindBy(xpath="//button[contains(text(),'Checkout')]")
	WebElement checkOutEle;
	
	By orderProduct = By.cssSelector("tr td:nth-child(3)");

	//################################################ Action Classes

	public Boolean verifyOrderDisplay(String productName) {
		waitForElementToAppear(orderProduct);
		Boolean match = 
				productNames.stream()
			.anyMatch(product->product.getText().equalsIgnoreCase(productName));
		return match;
	}
	
	public CheckoutPage goToCheckoutPage() {
		checkOutEle.click();
		CheckoutPage checkoutPage = new CheckoutPage(driver);
		return checkoutPage;
	}
	
}
