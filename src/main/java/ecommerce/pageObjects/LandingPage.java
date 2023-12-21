package ecommerce.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
//import org.openqa.selenium.By;

public class LandingPage {

	WebDriver driver;
	public LandingPage(WebDriver driver)//constructor
	{
		//initialization
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	//Page Factory
	
	//WebElement userEmail = driver.findElement(By.xpath("//input[@id='userEmail']"));
	@FindBy(xpath="//input[@id='userEmail']")
	WebElement userEmail;
	
	//driver.findElement(By.xpath("//input[@id='userPassword']"))
	@FindBy(xpath="//input[@id='userPassword']")
	WebElement userPass;
	
	//driver.findElement(By.xpath("//input[@id='login']"))
	@FindBy(xpath="//input[@id='login']")
	WebElement btnLogin;
	
	public void loginApplication(String email, String password)
	{
		userEmail.sendKeys(email);
		userPass.sendKeys(password);
		btnLogin.click();
	}
	
	public void goTo()
	{
		driver.get("https://rahulshettyacademy.com/client");
	}
	
	
	
}
