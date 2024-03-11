package ecommerce.pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import ecommerce.AbstractComponents.AbstractComponent;

public class ProductPage extends AbstractComponent{

	WebDriver driver;
	public ProductPage(WebDriver driver)//constructor
	{
		//initialization
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	//Page Factory
	//@FindBy(css="div[class='container'] div[class='row']")
	@FindBy(css=".mb-3")
	List<WebElement> products;
	@FindBy(css=".ng-animating")
	WebElement spinner;
	
	//By productText = By.cssSelector("div[class='container'] div[class='row']");
	By productText = By.cssSelector(".mb-3");
	
	By addToCart = By.cssSelector(".card-body button:last-child");
	By toastMessage = By.cssSelector("#toast-container");
	
	//################################################ Action Classes
	public List<WebElement> getProductList(){
		waitForElementToAppear(productText);
		return products;
	}
	
	public WebElement getProductByName(String productName) {
		waitForElementToAppear(productText);
		WebElement prod = products.stream()
				.filter(product->product.findElement(By.cssSelector("b")).getText().equals(productName)).findFirst().orElse(null);
		return prod;
	}
	
	public void addProductToCart(String productName) {
		WebElement prod = getProductByName(productName);
		prod.findElement(addToCart).click();
		waitForElementToAppear(toastMessage);
		waitForElementToDisappear(spinner);
	}
}
