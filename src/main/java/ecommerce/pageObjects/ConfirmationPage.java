package ecommerce.pageObjects;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import ecommerce.AbstractComponents.AbstractComponent;

public class ConfirmationPage extends AbstractComponent{

	WebDriver driver;
	public ConfirmationPage(WebDriver driver)//constructor
	{
		//initialization
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	//Page Factory
	@FindBy(css=".hero-primary")
	WebElement confirmationMessage;

	By confirmMessageText = By.cssSelector(".hero-primary");
	
	//################################################ Action Classes
	public String getConfirmationMessage() {
		waitForElementToAppear(confirmMessageText);
		return confirmationMessage.getText();
		
	}
	

}
