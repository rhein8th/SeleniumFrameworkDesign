package ecommerce.pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import ecommerce.AbstractComponents.AbstractComponent;

public class CheckoutPage extends AbstractComponent{

	WebDriver driver;
	public CheckoutPage(WebDriver driver)//constructor
	{
		//initialization
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	//Page Factory
	@FindBy(xpath="//input[@placeholder='Select Country']")
	WebElement country;
	@FindBy(xpath="//span[@class='ng-star-inserted']")
	List<WebElement> selectCountry;
	@FindBy(xpath="	//a[.='Place Order ']")
	WebElement submit;

	By countryDropdown = By.xpath("//input[@placeholder='Select Country']");
	By results = By.xpath("//i[@class='fa fa-search']");
	
	//################################################ Action Classes
	public String checkCountryDropdown(String initialCountry) {
		waitForElementToAppear(countryDropdown);
		country.sendKeys(initialCountry);
		return initialCountry;
	}
	
	public void selectCountry(String countryName) {
		
	//waitForElementToAppear(results);
	
		
		List<WebElement> countries = selectCountry;
		Actions actions = new Actions(driver);
	
		//iteration to check the desired country
				for (int i=0; i<=countries.size(); i++) {
					if (countries.get(i).getText().equalsIgnoreCase(countryName)){
						actions.moveToElement(countries.get(i)).click().build().perform();
						break;
					}
				}
		
	}
	public ConfirmationPage submitOrder() {
		submit.click();
		ConfirmationPage confirmationPage = new ConfirmationPage(driver);
		return confirmationPage;
	}
}
