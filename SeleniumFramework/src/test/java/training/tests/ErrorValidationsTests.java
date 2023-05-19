package training.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import training.testComponents.BaseTest;

public class ErrorValidationsTests extends BaseTest {
	
	String pass = "Password";
	String user = "randomemail@random.com";

	@Test(groups= {"Error Handling"})
	public void wrongPassword() {
		Assert.assertTrue(landing.logInSecuence(user, pass).equalsIgnoreCase(loginFailText()));
	}
}
