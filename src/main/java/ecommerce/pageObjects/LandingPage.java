package ecommerce.pageObjects;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import ecommerce.AbstractComponents.AbstractComponent;


public class LandingPage extends AbstractComponent{

	WebDriver driver;
	public LandingPage(WebDriver driver)//constructor
	{
		//initialization
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	//Page Factory
	@FindBy(xpath="//input[@id='userEmail']")
	WebElement userEmail;
	@FindBy(xpath="//input[@id='userPassword']")
	WebElement userPass;
	@FindBy(xpath="//input[@id='login']")
	WebElement btnLogin;
	@FindBy(css="[class*='flyInOut'")
	WebElement errorMessage;

	By errorMessage1 = By.cssSelector("[class*='flyInOut'");
	//################################################ Action Classes
	public ProductPage loginApplication(String email, String password)
	{
		userEmail.sendKeys(email);
		userPass.sendKeys(password);
		btnLogin.click();
		ProductPage productPage = new ProductPage(driver);
		return productPage;
	}
	
	public void goTo()
	{
		driver.get("https://rahulshettyacademy.com/client");
	}
	public String getErrorMessage() {
		waitForElementToAppear(errorMessage1);
		return errorMessage.getText();
	}
	
}
