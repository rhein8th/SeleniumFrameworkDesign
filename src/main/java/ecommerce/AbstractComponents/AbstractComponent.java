package ecommerce.AbstractComponents;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import ecommerce.pageObjects.CartPage;

public class AbstractComponent {
	WebDriver driver;
	public AbstractComponent(WebDriver driver) {
		this.driver = driver;
	}

	//Page Factory Header #############################################
	@FindBy(css="[routerlink*='cart']")
	WebElement cartHeader;
	

	
	// Action Classes ##################################################
	public void waitForElementToAppear(By findBy){

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));
		
	}
	public CartPage goToCartPage(){

		cartHeader.click();
		CartPage cartPage = new CartPage(driver);
		return cartPage;
		
	}
	
	public void waitForElementToDisappear(WebElement ele) {
		//Thread.sleep(5000);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.invisibilityOf(ele));
	}
}
