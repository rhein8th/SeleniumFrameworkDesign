package ecommerce.tests;

import java.io.IOException;
import org.junit.Assert;
import org.testng.annotations.Test;
import ecommerce.TestComponents.BaseTest;

public class ErrorValidation extends BaseTest{

	@Test
	public void submitOrder() throws IOException, InterruptedException{
		
		landingPage.loginApplication("rhein.bermillo@rahulshettyacademy.com", "1234567");	
		Assert.assertEquals("Incorrect email or password.", landingPage.getErrorMessage());
	
	
	}

}
