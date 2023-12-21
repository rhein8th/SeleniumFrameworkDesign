package ecommerce.pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
//import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class ProductPage {

	WebDriver driver;
	public ProductPage(WebDriver driver)//constructor
	{
		//initialization
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	//Page Factory
	//List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));
	@FindBy(css="mb-3")
	List<WebElement> products;
	
	
	
	
}
